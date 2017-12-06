package com.qisen.mts.beauty.service.rule;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.Pay;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.common.util.FormatUtil;
import com.qisen.mts.common.util.JSONUtil;

/**
 * 员工业绩提成计算工具类
 * 
 * @author Wen
 *
 */
public class EmpAchiCalcKit {

	/**
	 * 保留四舍五入小数
	 * 
	 * @param val
	 * @param kept
	 * @return
	 */
	public static double round(double val) {
		return (double) (Math.round(val * 100) / 100);
	}

	// 通过项目业绩计算一个员工的业绩
	/*
	 * item： 项目 emp： 员工 payment：支付 totalFee：员工总业绩（可选，如果填入，则固定员工总业绩，分业绩按比例缩小）
	 */
	public static void calucateEmpFee(Bill bill, JSONObject item, JSONObject emp, JSONObject payment, List<RuleEmpAchi> empAchiRules) {
		if (bill.getBtype() == BeautyDoConsts.BILL_BTYPE_SERVICE) {// 如果是项目
			JSONObject pay = payment.getJSONObject("pay");
			JSONObject fee = new JSONObject();
			double totalFee = 0, totalGain = 0;

			// 项目业绩和提成需要按支付方式计算
			// 遍历支付方式
			// 业绩
			for (String payTypeNo : pay.keySet()) {

				// 1.获取业绩规则
				JSONObject params = new JSONObject();
				params.put("typeNo", item.getString("typeNo"));
				params.put("itemNo", item.getString("itemNo"));
				params.put("gtype", emp.getString("gtype"));
				params.put("gno", emp.getString("gno"));
				params.put("eno", emp.getString("eno"));
				params.put("ctype", payTypeNo);
				params.put("rtype", 1);
				RuleEmpAchi feeRule = getRule(params, empAchiRules);

				// 2计算业绩中间值、并根据所占比例恢复
				double tempFee = round(execRule1(feeRule.getProp(), JSONUtil.getNotNullDouble(item, "rawPrice"), JSONUtil.getNotNullDouble(item, "fee"), JSONUtil.getNotNullInteger(item, "pointType")) * JSONUtil.getNotNullDouble(pay, payTypeNo) / JSONUtil.getNotNullDouble(payment, "totalPay"));
				// 3记录数据
				fee.put(payTypeNo, tempFee);
				totalFee += tempFee;
			}

			emp.put("totalFee", totalFee);
			emp.put("fee", fee);

			// 提成
			// 1.获取提成规则
			JSONObject params = new JSONObject();
			params.put("typeNo", item.getString("typeNo"));
			params.put("itemNo", item.getString("itemNo"));
			params.put("gtype", emp.getString("gtype"));
			params.put("gno", emp.getString("gno"));
			params.put("eno", emp.getString("eno"));
			params.put("rtype", 2);
			RuleEmpAchi gainRule = getRule(params, empAchiRules);

			// 2.根据原价或者业绩计算业提成，
			totalGain = round(execRule2(gainRule.getProp(), totalFee, totalFee, JSONUtil.getNotNullInteger(item, "pointType")));
			// 3记录数据
			emp.put("totalGain", totalGain);
			emp.put("gain", splitNumberByRatio(totalGain, pay));
		} else { // 如果不是项目（卖品，开充卡，套餐销售）
			// TODO 根据手动输入业绩强制缩减
			// 1项目消费2卖品消费3开卡4充值5销售套餐
			// 根据btype转换rtype
			String rtypeArr[] = new String[] { null, "2", "3", "4", "5", "6" };
			JSONObject pay = payment.getJSONObject("pay");
			pay.remove("PACK");
			// 业绩就是项目业绩，业绩按人数平分
			emp.put("totalFee", JSONUtil.getNotNullDouble(item, "fee") / item.getJSONArray("emps").size());
			emp.put("fee", splitNumberByRatio(JSONUtil.getNotNullDouble(emp, "totalFee"), pay));

			// 1.获取提成规则
			JSONObject params = new JSONObject();
			params.put("gno", emp.getString("gno"));
			params.put("eno", emp.getString("eno"));
			params.put("rtype", rtypeArr[bill.getBtype()]);
			if (bill.getBtype() == BeautyDoConsts.BILL_BTYPE_NEWCARD || bill.getBtype() == BeautyDoConsts.BILL_BTYPE_CHARGE) {
				// 开充卡的时候，需要根据cardType来获取规则
				params.put("itemNo", bill.getCardTypeNo());
			} else {
				// 其他时候直接用item上的
				params.put("itemNo", item.getString("itemNo"));
				params.put("typeNo", item.getString("typeNo"));
			}
			RuleEmpAchi gainRule = getRule(params, empAchiRules);
			// 2计算提成
			double totalGain = round(execRule3(gainRule.getProp(), JSONUtil.getNotNullDouble(item, "fee"), JSONUtil.getNotNullDouble(emp, "totalFee")));
			// 3记录数据
			emp.put("totalGain", totalGain);
			emp.put("gain", splitNumberByRatio(totalGain, pay));
		}
	}

	/**
	 * 重新计算员工提成
	 * 
	 * @param bill
	 * @param emp
	 * @param payment
	 * @param empAchiRules
	 * @param decimal
	 */
	public static void calucateEmpGain(Bill bill, JSONObject emp, Pay payment, List<RuleEmpAchi> empAchiRules, String decimal) {
		double totalGain = 0;
		if (bill.getBtype() == BeautyDoConsts.BILL_BTYPE_SERVICE) {// 如果是项目
			JSONObject pay = payment.getPay();
			// 提成
			// 1.获取提成规则
			JSONObject params = new JSONObject();
			params.put("typeNo", emp.getString("typeNo"));
			params.put("itemNo", emp.getString("itemNo"));
			params.put("gtype", emp.getString("gtype"));
			params.put("gno", emp.getString("gno"));
			params.put("eno", emp.getString("eno"));
			params.put("rtype", 2);
			RuleEmpAchi gainRule = getRule(params, empAchiRules);

			// 2.根据原价或者业绩计算业提成，
			if (gainRule != null)
				totalGain = FormatUtil.formatNum(execRule2(gainRule.getProp(), JSONUtil.getNotNullDouble(emp, "totalFee"), JSONUtil.getNotNullDouble(emp, "totalFee"), JSONUtil.getNotNullInteger(emp, "pointType")), decimal);
			// 3记录数据
			emp.put("totalGain", totalGain);
			emp.put("gain", splitNumberByRatio(totalGain, pay));
		} else { // 如果不是项目（卖品，开充卡，套餐销售）
			// 根据btype转换rtype
			String rtypeArr[] = new String[] { null, "2", "3", "4", "5", "6" };
			JSONObject pay = payment.getPay();
			pay.remove("PACK");

			// 1.获取提成规则
			JSONObject params = new JSONObject();
			params.put("gno", emp.getString("gno"));
			params.put("eno", emp.getString("eno"));
			params.put("rtype", rtypeArr[bill.getBtype()]);
			if (bill.getBtype() == BeautyDoConsts.BILL_BTYPE_NEWCARD || bill.getBtype() == BeautyDoConsts.BILL_BTYPE_CHARGE) {
				// 开充卡的时候，需要根据cardType来获取规则
				params.put("itemNo", emp.getString("itemNo"));
			} else {
				// 其他时候直接用item上的
				params.put("itemNo", emp.getString("itemNo"));
				params.put("typeNo", emp.getString("typeNo"));
			}
			RuleEmpAchi gainRule = getRule(params, empAchiRules);
			// 2计算提成
			if (gainRule != null)
				totalGain = FormatUtil.formatNum(execRule3(gainRule.getProp(), JSONUtil.getNotNullDouble(emp, "price"), JSONUtil.getNotNullDouble(emp, "totalFee")), decimal);
			// 3记录数据
			emp.put("totalGain", totalGain);
			emp.put("gain", splitNumberByRatio(totalGain, pay));
		}
	}

	/**
	 * 规则计算方式1，接受规则如下 { pointType:"0", point:"0", unpointType:"0", unpoint:"0", costType:0, cost:0 }
	 * 
	 * @param ruleProp 规则明细
	 * @param rawPrice 原价
	 * @param price 售价
	 * @param pointType 是否指定
	 * @return
	 */
	public static double execRule1(JSONObject ruleProp, double rawPrice, double price, int pointType) {
		int type;
		double value = 0.0, temp = 0.0, cost = 0.0;
		// 指定非指定
		if (BeautyDoConsts.POINT_TYPE_POINT == pointType) {
			type = JSONUtil.getNotNullInteger(ruleProp, "pointType");
			value = JSONUtil.getNotNullDouble(ruleProp, "point");
		} else {
			type = JSONUtil.getNotNullInteger(ruleProp, "unpointType");
			value = JSONUtil.getNotNullDouble(ruleProp, "unpoint");
		}

		// 计算业绩
		switch (type) {
		case 1: // 固定
			temp = value;
			break;
		case 2: // 原价比例
			temp = rawPrice * value / 100;
			break;
		case 3: // 业绩比例
			temp = price * value / 100;
			break;
		default: // 不设定或参数有误
			temp = price;
			break;
		}
		// 计算成本
		double ruleCost = JSONUtil.getNotNullDouble(ruleProp, "cost");
		int costType = JSONUtil.getNotNullInteger(ruleProp, "costType");
		switch (costType) {
		case 1: // 固定
			cost = ruleCost;
			break;
		case 2: // 原价比例
			cost = rawPrice * ruleCost / 100;
			break;
		case 3: // 业绩比例
			cost = price * ruleCost / 100;
			break;
		default: // 不设定或参数有误
			cost = 0;
			break;
		}

		return temp - cost;
	};

	/**
	 * 规则计算方式2，接受规则如下 { pointType:"0", point:"0", unpointType:"0", unpoint:"0" }
	 * 
	 * @param ruleProp 规则明细
	 * @param rawPrice 原价
	 * @param price 售价
	 * @param pointType 是否指定
	 * @return
	 */
	public static double execRule2(JSONObject ruleProp, double rawPrice, double price, int pointType) {
		int type;
		double value, temp;// , cost = 0.0;

		// 指定非指定
		if (BeautyDoConsts.POINT_TYPE_POINT == pointType) {
			type = JSONUtil.getNotNullInteger(ruleProp, "pointType");
			value = JSONUtil.getNotNullDouble(ruleProp, "point");
		} else {
			type = JSONUtil.getNotNullInteger(ruleProp, "unpointType");
			value = JSONUtil.getNotNullDouble(ruleProp, "unpoint");
		}

		// 计算业绩
		switch (type) {
		case 1: // 固定
			temp = value;
			break;
		case 2: // 原价比例
			temp = rawPrice * value / 100;
			break;
		case 3: // 业绩比例
			temp = price * value / 100;
			break;
		default: // 不设定或参数有误
			temp = 0;
		}

		return temp;
	};

	/**
	 * 规则计算方式3，接受规则如下 { gainType:"0", gain:"0", }
	 * 
	 * @param ruleProp 规则明细
	 * @param price 售价
	 * @param fee 业绩
	 * @return
	 */
	public static double execRule3(JSONObject ruleProp, double price, double fee) {
		double temp, ruleGain = JSONUtil.getNotNullDouble(ruleProp, "gain");
		int type = JSONUtil.getNotNullInteger(ruleProp, "gainType");
		// 计算业绩
		ruleGain = ruleGain != 0 ? ruleGain : 1;
		switch (type) {
		case 1: // 固定
			temp = ruleGain;
			break;
		case 2: // 售价比例
			temp = price * ruleGain / 100;
			break;
		case 3: // 业绩比例
			temp = fee * ruleGain / 100;
			break;
		default: // 不设定或参数有误
			temp = 0;
			break;
		}

		return temp;
	};

	/**
	 * 将数字按照比例分配
	 * 
	 * @param number
	 * @param pay
	 * @return
	 */
	public static JSONObject splitNumberByRatio(double number, JSONObject pay) {
		double total = 0, totalNumber = 0;
		int max, i = 0;
		JSONObject ret = new JSONObject();
		for (String payTypeNo : pay.keySet()) {
			total += pay.getDouble(payTypeNo);
			i++;
		}
		max = i;
		i = 0;
		for (String payTypeNo : pay.keySet()) {
			if (i == max - 1) {
				ret.put(payTypeNo, round(number - totalNumber));
			} else {
				ret.put(payTypeNo, round(number * pay.getDouble(payTypeNo) / total));
				totalNumber = round(totalNumber + pay.getDouble(payTypeNo));
			}
			i++;
		}
		return ret;
	};

	/**
	 * 根据参数获取匹配的业绩/提成规则
	 * 
	 * @param param 规则获取参数
	 * @param empAchiRules
	 * @return
	 */
	public static RuleEmpAchi getRule(JSONObject param, JSONArray empAchiRules) {
		RuleEmpAchi matchRule = null;
		for (int i = 0; i < empAchiRules.size(); i++) {
			JSONObject empAchiRuleJ = empAchiRules.getJSONObject(i);
			// 遍历每条规则是否匹配
			boolean match = true;
			for (String key : param.keySet()) {
				if (StringUtils.isNotBlank(empAchiRuleJ.getString(key)) && !param.getString(key).equals(empAchiRuleJ.getString(key))) {
					match = false;
					break;
				}
			}

			if (match)
				matchRule = empAchiRuleJ.toJavaObject(RuleEmpAchi.class);
		}
		return matchRule;
	}

	/**
	 * 根据参数获取匹配的业绩/提成规则
	 * 
	 * @param param 规则获取参数
	 * @param empAchiRules
	 * @return
	 */
	public static RuleEmpAchi getRule(JSONObject param, List<RuleEmpAchi> empAchiRules) {
		RuleEmpAchi matchRule = null;
		for (RuleEmpAchi empAchiRule : empAchiRules) {
			JSONObject empAchiRuleJ = empAchiRule.toJSON();
			// 遍历每条规则是否匹配
			boolean match = true;
			for (String key : param.keySet()) {
				if (StringUtils.isNotBlank(empAchiRuleJ.getString(key)) && !param.getString(key).equals(empAchiRuleJ.getString(key))) {
					match = false;
					break;
				}
			}

			if (match)
				matchRule = empAchiRule;
		}
		return matchRule;
	}

	// 计算业绩
	public static void calucateBill(Bill bill, JSONArray details, JSONObject payment, List<RuleEmpAchi> empAchiRules) {
		if (details != null && !details.isEmpty()) {
			for (int i = 0; i < details.size(); i++) {
				calucateItem(bill, details.getJSONObject(i), payment, empAchiRules);
			}
		}
	}

	// 计算一条明晰业
	public static void calucateItem(Bill bill, JSONObject item, JSONObject payment, List<RuleEmpAchi> empAchiRules) {
		// 3.计算员工总业绩
		JSONArray emps = item.getJSONArray("emps");
		if (emps != null && !emps.isEmpty()) {
			for (int i = 0; i < emps.size(); i++) {
				calucateEmp(bill, item, emps.getJSONObject(i), payment, empAchiRules);
			}
		}
	}

	// 计算一个员工业绩
	public static void calucateEmp(Bill bill, JSONObject item, JSONObject emp, JSONObject payment, List<RuleEmpAchi> empAchiRules) {
		// 根据btype执行不同的规则
		calucateEmpFee(bill, item, emp, payment, empAchiRules);
	}
}
