package com.qisen.mts.beauty.service.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.report.ReportDao;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.beauty.service.rule.EmpAchiCalcKit;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.CardRecord;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.DateUtil;
import com.qisen.mts.common.util.FormatUtil;
import com.qisen.mts.common.util.JSONUtil;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private JSONUtil jsonUtil;
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private EmpDao empDao;
	@Autowired
	private CardDao cardDao;

	/**
	 *
	 */
	@Override
	public CommObjResponse<JSONObject> daySummary(BeautyRequest<JSONObject> req) {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		JSONArray payTypes = sessionUser.getBasicType("payTypes");
		Integer eid = req.getEid();
		String date = req.getBody().getString("date");
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = new JSONObject();
		JSONObject customerSummary = new JSONObject();
		JSONObject paramBody = new JSONObject();
		paramBody.put("sids", new Integer[] { req.getSid() });
		paramBody.put("date", date);
		paramBody.put("payTypes", payTypes);
		JSONArray customerJArr = reportDao.customerSummary(tbuser, eid, paramBody);
		Integer maleCount = 0, // 男客数
				femaleCount = 0, // 女客数
				pointCount = 0, // 指定客数
				unpointCount = 0, // 非指定客数
				pointMaleCount = 0, // 指定男客数
				unpointMaleCount = 0, // 非指定男客数
				pointFemaleCount = 0, // 指定女客数
				unpointFemaleCount = 0; // 非指定女客数
		Long tempArr[] = null;
		for (int i = 0; i < customerJArr.size(); i++) {
			JSONObject customerJ = customerJArr.getJSONObject(i);
			Long billId = customerJ.getLong("billId");
			Integer count = customerJ.getInteger("count");
			Integer pointType = customerJ.getInteger("pointType");
			String sex = customerJ.getString("sex");

			if (ArrayUtils.contains(tempArr, billId))
				count = 0;
			else
				tempArr = ArrayUtils.add(tempArr, billId);

			if (sex != null) {
				if (sex.equals(BeautyDoConsts.SEX_MALE))
					maleCount += count;
				else if (sex.equals(BeautyDoConsts.SEX_FEMALE))
					femaleCount += count;
			}

			if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
				pointCount += count;
				if (sex != null) {
					if (sex.equals(BeautyDoConsts.SEX_MALE))
						pointMaleCount += count;
					else if (sex.equals(BeautyDoConsts.SEX_FEMALE))
						pointFemaleCount += count;
				}
			} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
				unpointCount += count;
				if (sex.equals(BeautyDoConsts.SEX_MALE))
					unpointMaleCount += count;
				else if (sex.equals(BeautyDoConsts.SEX_FEMALE))
					unpointFemaleCount += count;
			}
		}
		customerSummary.put("maleCount", maleCount);
		customerSummary.put("femaleCount", femaleCount);
		customerSummary.put("pointCount", pointCount);
		customerSummary.put("unpointCount", unpointCount);
		customerSummary.put("pointMaleCount", pointMaleCount);
		customerSummary.put("unpointMaleCount", unpointMaleCount);
		customerSummary.put("pointFemaleCount", pointFemaleCount);
		customerSummary.put("unpointFemaleCount", unpointFemaleCount);
		respBody.put("customerSummary", customerSummary);

		JSONObject incomeSummary = new JSONObject();
		JSONObject serviceSummary = new JSONObject();
		JSONObject productSummary = new JSONObject();
		JSONObject newcardSummary = new JSONObject();
		JSONObject chargeSummary = new JSONObject();
		JSONObject packSummary = new JSONObject();
		JSONObject sumSummary = new JSONObject();

		JSONArray incomeJArr = reportDao.incomeSummary(tbuser, eid, paramBody);
		for (int i = 0; i < payTypes.size(); i++) {
			JSONObject payType = payTypes.getJSONObject(i);
			String payTypeNo = payType.getString("no");

			sumSummary.put(payTypeNo, 0.0);
			serviceSummary.put(payTypeNo, 0.0);
			productSummary.put(payTypeNo, 0.0);
			newcardSummary.put(payTypeNo, 0.0);
			chargeSummary.put(payTypeNo, 0.0);
			packSummary.put(payTypeNo, 0.0);

			for (int j = 0; j < incomeJArr.size(); j++) {
				JSONObject incomeJ = incomeJArr.getJSONObject(j);
				Integer btype = incomeJ.getInteger("btype");
				if (incomeJ.containsKey(payTypeNo)) {
					Double fee = incomeJ.getDouble(payTypeNo);
					sumSummary.put(payTypeNo, sumSummary.getDouble(payTypeNo) + fee);

					switch (btype) {
					case BeautyDoConsts.BILL_BTYPE_SERVICE:
						serviceSummary.put(payTypeNo, serviceSummary.getDouble(payTypeNo) + fee);
						break;
					case BeautyDoConsts.BILL_BTYPE_PRODUCT:
						productSummary.put(payTypeNo, productSummary.getDouble(payTypeNo) + fee);
						break;
					case BeautyDoConsts.BILL_BTYPE_NEWCARD:
						newcardSummary.put(payTypeNo, newcardSummary.getDouble(payTypeNo) + fee);
						break;
					case BeautyDoConsts.BILL_BTYPE_CHARGE:
						chargeSummary.put(payTypeNo, chargeSummary.getDouble(payTypeNo) + fee);
						break;
					case BeautyDoConsts.BILL_BTYPE_PACK:
						packSummary.put(payTypeNo, packSummary.getDouble(payTypeNo) + fee);
						break;
					default:
						break;
					}
				}
			}
		}
		incomeSummary.put("serviceSummary", serviceSummary);
		incomeSummary.put("productSummary", productSummary);
		incomeSummary.put("newcardSummary", newcardSummary);
		incomeSummary.put("chargeSummary", chargeSummary);
		incomeSummary.put("packSummary", packSummary);
		incomeSummary.put("sumSummary", sumSummary);
		respBody.put("incomeSummary", incomeSummary);

		JSONObject sumPerfSummary = new JSONObject();
		JSONArray sumPerfJArr = reportDao.sumPerfSummary(tbuser, eid, paramBody);
		double servicePerf = 0;
		double productPerf = 0;
		double cardPerf = 0;
		double chargePerf = 0;
		double packPerf = 0;
		for (int i = 0; i < sumPerfJArr.size(); i++) {
			JSONObject sumPerfJ = sumPerfJArr.getJSONObject(i);
			Double fee = sumPerfJ.getDouble("fee");
			Integer btype = sumPerfJ.getInteger("btype");
			switch (btype) {
			case BeautyDoConsts.BILL_BTYPE_SERVICE:
				servicePerf += fee;
				break;
			case BeautyDoConsts.BILL_BTYPE_PRODUCT:
				productPerf += fee;
				break;
			case BeautyDoConsts.BILL_BTYPE_NEWCARD:
				cardPerf += fee;
				break;
			case BeautyDoConsts.BILL_BTYPE_CHARGE:
				chargePerf += fee;
				break;
			case BeautyDoConsts.BILL_BTYPE_PACK:
				packPerf += fee;
				break;
			default:
				break;
			}
		}
		sumPerfSummary.put("servicePerf", servicePerf);
		sumPerfSummary.put("productPerf", productPerf);
		sumPerfSummary.put("cardPerf", cardPerf);
		sumPerfSummary.put("chargePerf", chargePerf);
		sumPerfSummary.put("packPerf", packPerf);
		respBody.put("sumPerfSummary", sumPerfSummary);

		JSONArray servicePerfSummary = reportDao.servicePerfSummary(tbuser, eid, paramBody);
		respBody.put("servicePerfSummary", servicePerfSummary);
		resp.setBody(respBody);
		return resp;
	}

	/**
	 * 员工业绩汇总表
	 */
	@Override
	public CommObjResponse<JSONObject> empFeeSheet(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		JSONObject body = req.getBody();
		body.put("payTypes", sessionUser.getBasicType("payTypes"));
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = jsonUtil.readJSON("empFeeSheet.json", req.getLang());
		JSONArray columns = respBody.getJSONArray("columns");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		for (int k = 0; k < respBody.getJSONArray("head").size(); k++) {
			JSONObject column = columns.getJSONObject(k);
			if (JSONUtil.getNotNullDouble(column, "decimal") > 0)
				column.put("decimal", decimal);
		}
		respBody.put("columns", columns);
		JSONArray data = new JSONArray();

		List<Emp> emps = empDao.list4Shop(eid, body);
		JSONArray empFeeDetails = reportDao.empFeeDetails(tbuser, eid, body);

		for (Emp emp : emps) {
			if (StringUtils.isNotBlank(body.getString("gtype")) && !emp.getgType().equals(body.getString("gtype")))
				continue;
			JSONArray rowData = new JSONArray();
			rowData.add(emp.getName());
			Double sumFee = 0.0, sumCashFee = 0.0, // 总现金业绩
					sumNonCashFee = 0.0, // 总非现金业绩
					serviceCashFee = 0.0, // 服务现金业绩
					serviceNonCashFee = 0.0, // 服务其他业绩
					serviceSumFee = 0.0, // 总服务业绩
					servicePointFee = 0.0, // 服务指定业绩
					serviceUnpointFee = 0.0, // 服务非指定业绩
					serviceF0PointFee = 0.0, // 服务小项指定业绩
					serviceF1PointFee = 0.0, // 服务大项指定业绩
					serviceF0UnpointFee = 0.0, // 服务小项非指定业绩
					serviceF1UnpointFee = 0.0, // 服务大项非指定业绩

					productCashFee = 0.0, // 卖品现金业绩
					productNonCashFee = 0.0, // 卖品其他业绩
					productSumFee = 0.0, // 总卖品业绩

					packCashFee = 0.0, // 套餐现金业绩
					packNonCashFee = 0.0, // 套餐其他业绩
					packSumFee = 0.0, // 总套餐业绩

					newCardSumFee = 0.0, // 总开卡业绩
					chargeSumFee = 0.0; // 总充值业绩

			Integer servicePointNum = 0, // 总指定客数
					serviceUnpointNum = 0, // 总非指定客数
					serviceF0Num = 0, // 总小项个数
					serviceF1Num = 0, // 总大项个数
					serviceSumNum = 0; // 总客数

			for (int i = 0; i < empFeeDetails.size(); i++) {
				JSONObject empFeeDetailJ = empFeeDetails.getJSONObject(i);
				if (emp.getId().equals(empFeeDetailJ.getInteger("empId"))) {
					Integer count = empFeeDetailJ.getInteger("count");
					Integer gainType = empFeeDetailJ.getInteger("gainType");
					// String sex = empFeeDetailJ.getString("sex");
					Integer pointType = empFeeDetailJ.getInteger("pointType");
					Integer itemType = empFeeDetailJ.getInteger("itemType");
					Double totalFee = empFeeDetailJ.getDouble("totalFee");
					Double cashFee = empFeeDetailJ.getDouble("cashFee");
					Double nonCashFee = empFeeDetailJ.getDouble("nonCashFee");

					sumFee += totalFee;
					sumCashFee += cashFee;
					sumNonCashFee += nonCashFee;

					switch (gainType) {
					case BeautyDoConsts.BILL_BTYPE_SERVICE: // 劳动业绩
						serviceSumFee += totalFee;
						serviceSumNum += count;
						serviceCashFee += cashFee;
						serviceNonCashFee += nonCashFee;
						if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
							servicePointFee += totalFee;
							servicePointNum += count;
							if (itemType.equals(1)) {
								serviceF1PointFee += totalFee;
								serviceF1Num += count;
							} else {
								serviceF0PointFee += totalFee;
								serviceF0Num += count;
							}
						} else {
							serviceUnpointFee += totalFee;
							serviceUnpointNum += count;
							if (itemType.equals(1)) {
								serviceF1UnpointFee += totalFee;
								serviceF1Num += count;
							} else {
								serviceF0UnpointFee += totalFee;
								serviceF0Num += count;
							}
						}
						break;
					case BeautyDoConsts.BILL_BTYPE_PRODUCT: // 卖品业绩
						productSumFee += totalFee;
						productCashFee += cashFee;
						productNonCashFee += nonCashFee;
						break;
					case BeautyDoConsts.BILL_BTYPE_PACK: // 套餐销售业绩
						packSumFee += totalFee;
						packCashFee += cashFee;
						packNonCashFee += nonCashFee;
						break;
					case BeautyDoConsts.BILL_BTYPE_NEWCARD: // 开卡业绩
						newCardSumFee += totalFee;
						break;
					case BeautyDoConsts.BILL_BTYPE_CHARGE: // 充值业绩
						chargeSumFee += totalFee;
						break;
					default:
						break;
					}
				}
			}
			rowData.add(sumFee);
			rowData.add(sumCashFee);
			rowData.add(sumNonCashFee);

			rowData.add(serviceSumFee);
			rowData.add(serviceCashFee);
			rowData.add(serviceNonCashFee);

			rowData.add(productSumFee);
			rowData.add(productCashFee);
			rowData.add(productNonCashFee);

			rowData.add(packSumFee);
			rowData.add(packCashFee);
			rowData.add(packNonCashFee);

			rowData.add(newCardSumFee);
			rowData.add(chargeSumFee);

			rowData.add(serviceF0PointFee);
			rowData.add(serviceF0UnpointFee);
			rowData.add(serviceF0Num);

			rowData.add(serviceF1PointFee);
			rowData.add(serviceF1UnpointFee);
			rowData.add(serviceF1Num);

			rowData.add(servicePointNum);
			rowData.add(servicePointFee);
			rowData.add(serviceUnpointNum);
			rowData.add(serviceUnpointFee);
			rowData.add(serviceSumNum > 0
					? FormatUtil.formatNum(((double) servicePointNum / (double) serviceSumNum) * 100, "0") + "%" : 0);
			rowData.add(serviceSumNum);

			data.add(rowData);
		}

		respBody.put("data", data);
		resp.setBody(respBody);
		return resp;
	}

	/**
	 * 员工工资提成
	 */
	@Override
	public CommObjResponse<JSONObject> empGainSheet(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		String gainRuleType = sessionUser.getConfig("GLO_gainRuleType");
		List<RuleEmpAchi> empAchiRules = sessionUser.getEmpAchiRules();
		List<EmpGrade> empGrades = sessionUser.getEmpGrades();
		JSONObject body = req.getBody();
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = jsonUtil.readJSON("empGainSheet.json", req.getLang());
		JSONArray status = respBody.getJSONObject("basicData").getJSONArray("status");

		JSONArray columns = respBody.getJSONArray("columns");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		for (int k = 0; k < respBody.getJSONArray("head").size(); k++) {
			JSONObject column = columns.getJSONObject(k);
			if (JSONUtil.getNotNullDouble(column, "decimal") > 0)
				column.put("decimal", decimal);
		}
		respBody.put("columns", columns);
		JSONArray data = new JSONArray();

		List<Emp> emps = empDao.list4Shop(eid, body);
		JSONArray empGainSumArr = reportDao.empGainSum(tbuser, eid, body);

		for (Emp emp : emps) {
			if (StringUtils.isNotBlank(body.getString("gtype")) && !emp.getgType().equals(body.getString("gtype")))
				continue;

			JSONArray rowData = new JSONArray();
			rowData.add(emp.getName());

			for (EmpGrade empGrade : empGrades) {
				if (empGrade.getNo().equals(emp.getgNo())) {
					rowData.add(empGrade.getName());
					break;
				}
			}

			if (rowData.size() < 2)
				rowData.add("");

			Double sumServiceFee = 0.0, // 服务业绩
					sumServiceGain = 0.0, // 服务提成
					sumProductFee = 0.0, // 卖品业绩
					sumProductGain = 0.0, // 卖品提成
					sumNewcardFee = 0.0, // 开卡业绩
					sumNewcardGain = 0.0, // 开卡提成
					sumChargeFee = 0.0, // 充值业绩
					sumChargeGain = 0.0, // 充值提成
					sumPackFee = 0.0, // 套餐业绩
					sumPackGain = 0.0; // 套餐提成

			for (int i = 0; i < empGainSumArr.size(); i++) {
				JSONObject empGainSumJ = empGainSumArr.getJSONObject(i);
				if (emp.getId().equals(empGainSumJ.getInteger("empId"))) {
					Integer gainType = empGainSumJ.getInteger("gainType");
					Double totalFee = empGainSumJ.getDouble("totalFee");
					Double totalGain = FormatUtil.formatNum(empGainSumJ.getDouble("totalGain"), decimal);
					switch (gainType) {
					case BeautyDoConsts.BILL_BTYPE_SERVICE:
						sumServiceFee += totalFee;
						sumServiceGain += totalGain;
						break;
					case BeautyDoConsts.BILL_BTYPE_PRODUCT:
						sumProductFee += totalFee;
						sumProductGain += totalGain;
						break;
					case BeautyDoConsts.BILL_BTYPE_NEWCARD:
						sumNewcardFee += totalFee;
						sumNewcardGain += totalGain;
						break;
					case BeautyDoConsts.BILL_BTYPE_CHARGE:
						sumChargeFee += totalFee;
						sumChargeGain += totalGain;
						break;
					case BeautyDoConsts.BILL_BTYPE_PACK:
						sumPackFee += totalFee;
						sumPackGain += totalGain;
						break;
					}
				}
			}

			rowData.add(sumServiceFee);
			// 总劳动业绩提成方式
			if (!gainRuleType.equals(BeautyDoConsts.GAIN_RULE_TYPE_SINGLE)) {
				respBody.getJSONObject("config").remove("custmizedBtn"); // 总劳动业绩
																			// 提成隐藏重算提成按钮
				Double pointFee = 0.0, // 指定业绩
						unpointFee = 0.0, // 非指定业绩

						f0PointFee = 0.0, // 小项指定业绩
						f0UnpointFee = 0.0, // 小项指定业绩
						f1PointFee = 0.0, // 大项非指定业绩
						f1UnpointFee = 0.0, // 大项非指定业绩

						f0CashFee = 0.0, // 小项现金业绩
						f0NonCashFee = 0.0, // 小项非现业绩
						f1CashFee = 0.0, // 大项现金业绩
						f1NonCashFee = 0.0, // 大项非现业绩

						pointCashFee = 0.0, // 指定现金业绩
						pointNonCashFee = 0.0, // 指定非现业绩
						unpointCashFee = 0.0, // 非指定现金业绩
						unpointNonCashFee = 0.0; // 非指定非现业绩

				JSONArray payTypes = sessionUser.getBasicType("payTypes");
				body.put("payTypes", payTypes);
				body.put("empId", emp.getId());
				JSONArray empServiceGainDetails = reportDao.empServiceGainDetails(tbuser, eid, body);
				for (int i = 0; i < empServiceGainDetails.size(); i++) {
					JSONObject empServiceGainDetailJ = empServiceGainDetails.getJSONObject(i);
					Integer pointType = empServiceGainDetailJ.getInteger("pointType");
					Integer itemType = empServiceGainDetailJ.getInteger("itemType");
					Double totalFee = empServiceGainDetailJ.getDouble("totalFee");
					Double cashFee = empServiceGainDetailJ.getDouble("cashFee");
					Double nonCashFee = empServiceGainDetailJ.getDouble("nonCashFee");

					if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
						pointFee += totalFee;
						pointCashFee += cashFee;
						pointNonCashFee += nonCashFee;
						if (itemType == 0)
							f0PointFee += totalFee;
						else if (itemType == 1)
							f1PointFee += totalFee;
					} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
						unpointFee += totalFee;
						unpointCashFee += cashFee;
						unpointNonCashFee += nonCashFee;
						if (itemType == 0)
							f0UnpointFee += totalFee;
						else if (itemType == 1)
							f1UnpointFee += totalFee;
					}

					if (itemType == 0) {
						f0CashFee += cashFee;
						f0NonCashFee += nonCashFee;
					} else if (itemType == 1) {
						f1CashFee += cashFee;
						f1NonCashFee += nonCashFee;
					}
				}

				JSONObject param = new JSONObject();
				param.put("rtype", 7);
				param.put("gtype", emp.getgType());
				param.put("gno", emp.getgNo());
				param.put("eno", emp.getNo());

				RuleEmpAchi ruleEmpAchi = EmpAchiCalcKit.getRule(param, empAchiRules);

				sumServiceGain = 0.0;
				// 指定、非指定
				double pointGain = 0, // 指定提成 d%比例
						unpointGain = 0, // 非指定提成 d%比例
						// 小项指定、小项非指定、大项指定、大项非指定
						f0PointGain = 0, // 小项指定提成 d%比例
						f0UnpointGain = 0, // 小项指定提成 d%比例
						f1PointGain = 0, // 大项非指定提成 d%比例
						f1UnpointGain = 0, // 大项非指定提成 d%比例
						// 小项现金、小项非现、大项现金、大项非现
						f0CashGain = 0, // 小项现金提成 d%比例
						f0CardGain = 0, // 小项非现提成 d%比例
						f1CashGain = 0, // 大项现金提成 d%比例
						f1CardGain = 0, // 大项非现提成 d%比例
						// 指定现金、指定非现、不指定现金、不指定非现
						pointCashGain = 0, // 指定现金提成 d%比例
						pointCardGain = 0, // 指定非现提成 d%比例
						unpointCashGain = 0, // 不指定现金提成 d%比例
						unpointCardGain = 0; // 不指定非现提成 d%比例

				if (ruleEmpAchi != null) {
					JSONObject ruleProp = ruleEmpAchi.getProp();

					pointGain = JSONUtil.getNotNullDouble(ruleProp, "pointGain"); // 指定提成
																					// d%比例
					unpointGain = JSONUtil.getNotNullDouble(ruleProp, "unpointGain"); // 非指定提成
																						// d%比例
					f0PointGain = JSONUtil.getNotNullDouble(ruleProp, "f0PointGain"); // 小项指定提成
																						// d%比例
					f0UnpointGain = JSONUtil.getNotNullDouble(ruleProp, "f0UnpointGain"); // 小项指定提成
																							// d%比例
					f1PointGain = JSONUtil.getNotNullDouble(ruleProp, "f1PointGain"); // 大项非指定提成
																						// d%比例
					f1UnpointGain = JSONUtil.getNotNullDouble(ruleProp, "f1UnpointGain"); // 大项非指定提成
																							// d%比例
					f0CashGain = JSONUtil.getNotNullDouble(ruleProp, "f0CashGain"); // 小项现金提成
																					// d%比例
					f0CardGain = JSONUtil.getNotNullDouble(ruleProp, "f0CardGain"); // 小项非现提成
																					// d%比例
					f1CashGain = JSONUtil.getNotNullDouble(ruleProp, "f1CashGain"); // 大项现金提成
																					// d%比例
					f1CardGain = JSONUtil.getNotNullDouble(ruleProp, "f1CardGain"); // 大项非现提成
																					// d%比例
					pointCashGain = JSONUtil.getNotNullDouble(ruleProp, "pointCashGain"); // 指定现金提成
																							// d%比例
					pointCardGain = JSONUtil.getNotNullDouble(ruleProp, "pointCardGain"); // 指定非现提成
																							// d%比例
					unpointCashGain = JSONUtil.getNotNullDouble(ruleProp, "unpointCashGain"); // 不指定现金提成
																								// d%比例
					unpointCardGain = JSONUtil.getNotNullDouble(ruleProp, "unpointCardGain"); // 不指定非现提成
																								// d%比例
				}

				switch (gainRuleType) {
				case BeautyDoConsts.GAIN_RULE_TYPE_FP: // 小项现金、小项非现、大项现金、大项非现
					sumServiceGain += f0CashGain > 0 ? f0CashFee * (f0CashGain / (double) 100) : 0;
					sumServiceGain += f0CardGain > 0 ? f0NonCashFee * (f0CardGain / (double) 100) : 0;
					sumServiceGain += f1CashGain > 0 ? f1CashFee * (f1CashGain / (double) 100) : 0;
					sumServiceGain += f1CardGain > 0 ? f1NonCashFee * (f1CardGain / (double) 100) : 0;
					break;
				case BeautyDoConsts.GAIN_RULE_TYPE_FPU: // 小项指定、小项非指定、大项指定、大项非指定
					sumServiceGain += f0PointGain > 0 ? f0PointFee * (f0PointGain / (double) 100) : 0;
					sumServiceGain += f0UnpointGain > 0 ? f0UnpointFee * (f0UnpointGain / (double) 100) : 0;
					sumServiceGain += f1PointGain > 0 ? f1PointFee * (f1PointGain / (double) 100) : 0;
					sumServiceGain += f1UnpointGain > 0 ? f1UnpointFee * (f1UnpointGain / (double) 100) : 0;
					break;
				case BeautyDoConsts.GAIN_RULE_TYPE_PPU: // 指定现金、指定非现、不指定现金、不指定非现
					sumServiceGain += pointCashGain > 0 ? pointCashFee * (pointCashGain / (double) 100) : 0;
					sumServiceGain += pointCardGain > 0 ? pointNonCashFee * (pointCardGain / (double) 100) : 0;
					sumServiceGain += unpointCashGain > 0 ? unpointCashFee * (unpointCashGain / (double) 100) : 0;
					sumServiceGain += unpointCardGain > 0 ? unpointNonCashFee * (unpointCardGain / (double) 100) : 0;
					break;
				case BeautyDoConsts.GAIN_RULE_TYPE_PU: // 指定、非指定
					sumServiceGain += pointGain > 0 ? pointFee * (pointGain / (double) 100) : 0;
					sumServiceGain += unpointGain > 0 ? unpointFee * (unpointGain / (double) 100) : 0;
					break;
				}
			}

			JSONObject serviceGain = new JSONObject();

			// 劳动提成
			sumServiceGain = FormatUtil.formatNum(sumServiceGain, decimal);
			serviceGain.put("sumServiceGain", sumServiceGain);
			serviceGain.put("empId", emp.getId());
			rowData.add(serviceGain);
			rowData.add(sumProductFee);
			rowData.add(sumProductGain);
			rowData.add(sumNewcardFee);
			rowData.add(sumNewcardGain);
			rowData.add(sumChargeFee);
			rowData.add(sumChargeGain);
			rowData.add(sumPackFee);
			rowData.add(sumPackGain);
			rowData.add(sumServiceGain + sumProductGain + sumNewcardGain + sumChargeGain + sumPackGain);
			rowData.add(emp.getBank());
			if (status != null && status.size() == 3)
				rowData.add(status.getString(emp.getStatus()));
			else
				rowData.add("");
			data.add(rowData);
		}

		respBody.put("data", data);
		resp.setBody(respBody);
		return resp;
	}

	/**
	 * 员工工资提成
	 */
	@Override
	public CommObjResponse<JSONObject> empGainDetailSheet(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		String gainRuleType = sessionUser.getConfig("GLO_gainRuleType");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		Integer eid = req.getEid();
		JSONObject body = req.getBody();
		JSONArray payTypes = sessionUser.getBasicType("payTypes");
		body.put("payTypes", payTypes);

		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = jsonUtil.readJSON("empGainDetailSheet_SINGLE.json", req.getLang());

		JSONObject basicData = respBody.getJSONObject("basicData");
		JSONArray data = new JSONArray();

		// 默认提成规则 单个项目提成
		if (StringUtils.isBlank(gainRuleType))
			gainRuleType = BeautyDoConsts.GAIN_RULE_TYPE_SINGLE;

		Double pointFee = 0.0, // 指定业绩
				unpointFee = 0.0, // 非指定业绩

				f0PointFee = 0.0, // 小项指定业绩
				f0UnpointFee = 0.0, // 小项指定业绩
				f1PointFee = 0.0, // 大项非指定业绩
				f1UnpointFee = 0.0, // 大项非指定业绩

				f0CashFee = 0.0, // 小项现金业绩
				f0NonCashFee = 0.0, // 小项非现业绩
				f1CashFee = 0.0, // 大项现金业绩
				f1NonCashFee = 0.0, // 大项非现业绩

				pointCashFee = 0.0, // 指定现金业绩
				pointNonCashFee = 0.0, // 指定非现业绩
				unpointCashFee = 0.0, // 非指定现金业绩
				unpointNonCashFee = 0.0; // 非指定非现业绩

		JSONArray empServiceGainDetails = reportDao.empServiceGainDetails(tbuser, eid, body);
		for (int i = 0; i < empServiceGainDetails.size(); i++) {
			JSONObject empServiceGainDetailJ = empServiceGainDetails.getJSONObject(i);
			String itemName = empServiceGainDetailJ.getString("itemName");
			Integer pointType = empServiceGainDetailJ.getInteger("pointType");
			Integer itemType = empServiceGainDetailJ.getInteger("itemType");
			Integer ctype = empServiceGainDetailJ.getInteger("ctype");
			Integer count = empServiceGainDetailJ.getInteger("count");
			Double totalFee = empServiceGainDetailJ.getDouble("totalFee");
			Double cashFee = empServiceGainDetailJ.getDouble("cashFee");
			Double nonCashFee = empServiceGainDetailJ.getDouble("nonCashFee");
			Double totalGain = empServiceGainDetailJ.getDouble("totalGain");

			// 单个项目提成方式
			if (gainRuleType.equals(BeautyDoConsts.GAIN_RULE_TYPE_SINGLE)) {
				JSONArray rowData = new JSONArray();
				rowData.add(itemName);
				rowData.add(basicData.getJSONArray("itemType").getString(itemType));
				rowData.add(basicData.getJSONArray("pointType").getString(pointType));
				rowData.add(basicData.getJSONArray("ctype").getString(ctype - 1));
				rowData.add(count);
				rowData.add(FormatUtil.formatNum(totalGain, decimal));

				// 业绩明细
				for (int j = 0; j < payTypes.size(); j++) {
					JSONObject payTypeJ = payTypes.getJSONObject(j);
					rowData.add(empServiceGainDetailJ.getDouble("fee_" + payTypeJ.getString("no")));
					if ((j + 1) == payTypes.size())
						rowData.add(FormatUtil.formatNum(totalFee, decimal));
				}

				data.add(rowData);
			} else { // 总劳动业绩提成方式
				if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
					pointFee += FormatUtil.formatNum(totalFee, decimal);
					pointCashFee += FormatUtil.formatNum(cashFee, decimal);
					pointNonCashFee += FormatUtil.formatNum(nonCashFee, decimal);
					if (itemType == 0)
						f0PointFee += FormatUtil.formatNum(totalFee, decimal);
					else if (itemType == 1)
						f1PointFee += FormatUtil.formatNum(totalFee, decimal);
				} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
					unpointFee += FormatUtil.formatNum(totalFee, decimal);
					unpointCashFee += FormatUtil.formatNum(cashFee, decimal);
					unpointNonCashFee += FormatUtil.formatNum(nonCashFee, decimal);
					if (itemType == 0)
						f0UnpointFee += FormatUtil.formatNum(totalFee, decimal);
					else if (itemType == 1)
						f1UnpointFee += FormatUtil.formatNum(totalFee, decimal);
				}

				if (itemType == 0) {
					f0CashFee += FormatUtil.formatNum(cashFee, decimal);
					f0NonCashFee += FormatUtil.formatNum(nonCashFee, decimal);
				} else if (itemType == 1) {
					f1CashFee += FormatUtil.formatNum(cashFee, decimal);
					f1NonCashFee += FormatUtil.formatNum(nonCashFee, decimal);
				}
			}
		}

		// 单个项目提成方式 设置表头
		if (gainRuleType.equals(BeautyDoConsts.GAIN_RULE_TYPE_SINGLE)) {
			JSONArray head = respBody.getJSONArray("head");
			JSONArray headTop = respBody.getJSONArray("headTop");

			JSONArray columns = respBody.getJSONArray("columns");
			columns.getJSONObject(columns.size() - 1).put("decimal", decimal);
			JSONObject column = new JSONObject();
			column.put("defaultVisible", 1);
			column.put("width", 100);
			column.put("sortable", 0);
			column.put("decimal", decimal);

			JSONArray tempPayHead = new JSONArray();
			JSONArray tempPayColumns = new JSONArray();
			for (int i = 0; i < payTypes.size(); i++) {
				JSONObject payTypeJ = payTypes.getJSONObject(i);
				tempPayHead.add(payTypeJ.getString("name"));
				tempPayColumns.add(column);
				if ((i + 1) == payTypes.size()) {
					tempPayColumns.add(column);
					tempPayHead.add(basicData.getString("SUM"));
				}
			}
			head.addAll(tempPayHead);
			columns.addAll(tempPayColumns);

			JSONObject feeTop = headTop.getJSONObject(headTop.size() - 1);
			feeTop.put("colspan", payTypes.size() + 1);
		} else { // 总劳动业绩提成方式
			Emp emp = empDao.find(body.getInteger("empId"));
			List<RuleEmpAchi> empAchiRules = sessionUser.getEmpAchiRules();
			JSONObject param = new JSONObject();
			param.put("rtype", 7);
			param.put("gtype", emp.getgType());
			param.put("gno", emp.getgNo());
			param.put("eno", emp.getNo());

			RuleEmpAchi ruleEmpAchi = EmpAchiCalcKit.getRule(param, empAchiRules);

			// 指定、非指定
			double pointGain = 0, // 指定提成 d%比例
					unpointGain = 0, // 非指定提成 d%比例
					// 小项指定、小项非指定、大项指定、大项非指定
					f0PointGain = 0, // 小项指定提成 d%比例
					f0UnpointGain = 0, // 小项指定提成 d%比例
					f1PointGain = 0, // 大项非指定提成 d%比例
					f1UnpointGain = 0, // 大项非指定提成 d%比例
					// 小项现金、小项非现、大项现金、大项非现
					f0CashGain = 0, // 小项现金提成 d%比例
					f0NonCashGain = 0, // 小项非现提成 d%比例
					f1CashGain = 0, // 大项现金提成 d%比例
					f1NonCashGain = 0, // 大项非现提成 d%比例
					// 指定现金、指定非现、不指定现金、不指定非现
					pointCashGain = 0, // 指定现金提成 d%比例
					pointNonCashGain = 0, // 指定非现提成 d%比例
					unpointCashGain = 0, // 不指定现金提成 d%比例
					unpointNonCashGain = 0; // 不指定非现提成 d%比例

			if (ruleEmpAchi != null) {
				JSONObject ruleProp = ruleEmpAchi.getProp();

				pointGain = JSONUtil.getNotNullDouble(ruleProp, "pointGain"); // 指定提成
																				// d%比例
				unpointGain = JSONUtil.getNotNullDouble(ruleProp, "unpointGain"); // 非指定提成
																					// d%比例
				f0PointGain = JSONUtil.getNotNullDouble(ruleProp, "f0PointGain"); // 小项指定提成
																					// d%比例
				f0UnpointGain = JSONUtil.getNotNullDouble(ruleProp, "f0UnpointGain"); // 小项指定提成
																						// d%比例
				f1PointGain = JSONUtil.getNotNullDouble(ruleProp, "f1PointGain"); // 大项非指定提成
																					// d%比例
				f1UnpointGain = JSONUtil.getNotNullDouble(ruleProp, "f1UnpointGain"); // 大项非指定提成
																						// d%比例
				f0CashGain = JSONUtil.getNotNullDouble(ruleProp, "f0CashGain"); // 小项现金提成
																				// d%比例
				f0NonCashGain = JSONUtil.getNotNullDouble(ruleProp, "f0CardGain"); // 小项非现提成
																					// d%比例
				f1CashGain = JSONUtil.getNotNullDouble(ruleProp, "f1CashGain"); // 大项现金提成
																				// d%比例
				f1NonCashGain = JSONUtil.getNotNullDouble(ruleProp, "f1CardGain"); // 大项非现提成
																					// d%比例
				pointCashGain = JSONUtil.getNotNullDouble(ruleProp, "pointCashGain"); // 指定现金提成
																						// d%比例
				pointNonCashGain = JSONUtil.getNotNullDouble(ruleProp, "pointCardGain"); // 指定非现提成
																							// d%比例
				unpointCashGain = JSONUtil.getNotNullDouble(ruleProp, "unpointCashGain"); // 不指定现金提成
																							// d%比例
				unpointNonCashGain = JSONUtil.getNotNullDouble(ruleProp, "unpointCardGain"); // 不指定非现提成
																								// d%比例
			}

			respBody = jsonUtil.readJSON("empGainDetailSheet_SCALE.json", req.getLang());
			JSONArray columns = respBody.getJSONArray("columns");
			for (int k = 0; k < columns.size(); k++) {
				JSONObject column = columns.getJSONObject(k);
				if (JSONUtil.getNotNullDouble(column, "decimal") > 0)
					column.put("decimal", decimal);
			}
			respBody.put("columns", columns);
			basicData = respBody.getJSONObject("basicData");

			switch (gainRuleType) {
			case BeautyDoConsts.GAIN_RULE_TYPE_FP: // 小项现金、小项非现、大项现金、大项非现
				JSONArray f0CashFeeRowData = new JSONArray();
				f0CashFeeRowData.add(basicData.getString("f0CashFee"));
				f0CashFeeRowData.add(f0CashFee);
				f0CashFeeRowData.add(f0CashGain + "%");
				f0CashFeeRowData.add(f0CashGain > 0 ? f0CashFee * (f0CashGain / (double) 100) : 0);

				JSONArray f0NonCashFeeRowData = new JSONArray();
				f0NonCashFeeRowData.add(basicData.getString("f0NonCashFee"));
				f0NonCashFeeRowData.add(f0NonCashFee);
				f0NonCashFeeRowData.add(f0NonCashGain + "%");
				f0NonCashFeeRowData.add(f0NonCashGain > 0 ? f0NonCashFee * (f0NonCashGain / (double) 100) : 0);

				JSONArray f1CashFeeRowData = new JSONArray();
				f1CashFeeRowData.add(basicData.getString("f1CashFee"));
				f1CashFeeRowData.add(f1CashFee);
				f1CashFeeRowData.add(f1CashGain + "%");
				f1CashFeeRowData.add(f1CashGain > 0 ? f1CashFee * (f1CashGain / (double) 100) : 0);

				JSONArray f1NonCashFeeRowData = new JSONArray();
				f1NonCashFeeRowData.add(basicData.getString("f1NonCashFee"));
				f1NonCashFeeRowData.add(f1NonCashFee);
				f1NonCashFeeRowData.add(f1NonCashGain + "%");
				f1NonCashFeeRowData.add(f1NonCashGain > 0 ? f1NonCashFee * (f1NonCashGain / (double) 100) : 0);

				data.add(f0CashFeeRowData);
				data.add(f0NonCashFeeRowData);
				data.add(f1CashFeeRowData);
				data.add(f1NonCashFeeRowData);
				break;
			case BeautyDoConsts.GAIN_RULE_TYPE_FPU: // 小项指定、小项非指定、大项指定、大项非指定
				JSONArray f0PointFeeRowData = new JSONArray();
				f0PointFeeRowData.add(basicData.getString("f0PointFee"));
				f0PointFeeRowData.add(f0PointFee);
				f0PointFeeRowData.add(f0PointGain + "%");
				f0PointFeeRowData.add(f0PointGain > 0 ? f0PointFee * (f0PointGain / (double) 100) : 0);

				JSONArray f0UnpointFeeRowData = new JSONArray();
				f0UnpointFeeRowData.add(basicData.getString("f0UnpointFee"));
				f0UnpointFeeRowData.add(f0UnpointFee);
				f0UnpointFeeRowData.add(f0UnpointGain + "%");
				f0UnpointFeeRowData.add(f0UnpointGain > 0 ? f0UnpointFee * (f0UnpointGain / (double) 100) : 0);

				JSONArray f1PointFeeRowData = new JSONArray();
				f1PointFeeRowData.add(basicData.getString("f1PointFee"));
				f1PointFeeRowData.add(f1PointFee);
				f1PointFeeRowData.add(f1PointGain + "%");
				f1PointFeeRowData.add(f1PointGain > 0 ? f1PointFee * (f1PointGain / (double) 100) : 0);

				JSONArray f1UnpointFeeRowData = new JSONArray();
				f1UnpointFeeRowData.add(basicData.getString("f1UnpointFee"));
				f1UnpointFeeRowData.add(f1UnpointFee);
				f1UnpointFeeRowData.add(f1UnpointGain + "%");
				f1UnpointFeeRowData.add(f1UnpointGain > 0 ? f1UnpointFee * (f1UnpointGain / (double) 100) : 0);

				data.add(f0PointFeeRowData);
				data.add(f0UnpointFeeRowData);
				data.add(f1PointFeeRowData);
				data.add(f1UnpointFeeRowData);
				break;
			case BeautyDoConsts.GAIN_RULE_TYPE_PPU: // 指定现金、指定非现、不指定现金、不指定非现
				JSONArray pointCashFeeRowData = new JSONArray();
				pointCashFeeRowData.add(basicData.getString("pointCashFee"));
				pointCashFeeRowData.add(pointCashFee);
				pointCashFeeRowData.add(pointCashGain + "%");
				pointCashFeeRowData.add(pointCashGain > 0 ? pointCashFee * (pointCashGain / (double) 100) : 0);

				JSONArray pointNonCashFeeRowData = new JSONArray();
				pointNonCashFeeRowData.add(basicData.getString("pointNonCashFee"));
				pointNonCashFeeRowData.add(pointNonCashFee);
				pointNonCashFeeRowData.add(pointNonCashGain + "%");
				pointNonCashFeeRowData
						.add(pointNonCashGain > 0 ? pointNonCashFee * (pointNonCashGain / (double) 100) : 0);

				JSONArray unpointCashFeeRowData = new JSONArray();
				unpointCashFeeRowData.add(basicData.getString("unpointCashFee"));
				unpointCashFeeRowData.add(unpointCashFee);
				unpointCashFeeRowData.add(unpointCashGain + "%");
				unpointCashFeeRowData.add(unpointCashGain > 0 ? unpointCashFee * (unpointCashGain / (double) 100) : 0);

				JSONArray unpointNonCashFeeRowData = new JSONArray();
				unpointNonCashFeeRowData.add(basicData.getString("unpointNonCashFee"));
				unpointNonCashFeeRowData.add(unpointNonCashFee);
				unpointNonCashFeeRowData.add(unpointNonCashGain + "%");
				unpointNonCashFeeRowData
						.add(unpointNonCashGain > 0 ? unpointNonCashFee * (unpointNonCashGain / (double) 100) : 0);

				data.add(pointCashFeeRowData);
				data.add(pointNonCashFeeRowData);
				data.add(unpointCashFeeRowData);
				data.add(unpointNonCashFeeRowData);
				break;
			case BeautyDoConsts.GAIN_RULE_TYPE_PU: // 指定、非指定
				JSONArray pointFeeRowData = new JSONArray();
				pointFeeRowData.add(basicData.getString("pointFee"));
				pointFeeRowData.add(pointFee);
				pointFeeRowData.add(pointGain + "%");
				pointFeeRowData.add(pointGain > 0 ? pointFee * (pointGain / (double) 100) : 0);

				JSONArray unpointFeeRowData = new JSONArray();
				unpointFeeRowData.add(basicData.getString("unpointFee"));
				unpointFeeRowData.add(unpointFee);
				unpointFeeRowData.add(unpointGain + "%");
				unpointFeeRowData.add(unpointGain > 0 ? unpointFee * (unpointGain / (double) 100) : 0);

				data.add(pointFeeRowData);
				data.add(unpointFeeRowData);
				break;
			}
		}
		respBody.put("data", data);
		resp.setBody(respBody);
		return resp;
	}

	/**
	 * 项目分类业绩汇总表
	 */
	@Override
	public CommObjResponse<JSONObject> itemTypeFeeSheet(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		JSONArray serviceTypes = sessionUser.getBasicType("serviceTypes");
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = jsonUtil.readJSON("itemTypeFeeSheet.json", req.getLang());
		JSONArray columns = respBody.getJSONArray("columns");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		for (int k = 0; k < columns.size(); k++) {
			JSONObject column = columns.getJSONObject(k);
			if (JSONUtil.getNotNullDouble(column, "decimal") > 0)
				column.put("decimal", decimal);
		}
		respBody.put("columns", columns);
		JSONArray data = new JSONArray();

		JSONArray itemTypeFeeDetails = reportDao.itemTypeFeeDetails(tbuser, eid, req.getBody());
		for (int i = 0; i < serviceTypes.size(); i++) {
			JSONObject serviceTypeJ = serviceTypes.getJSONObject(i);
			JSONArray rowData = new JSONArray();
			rowData.add(serviceTypeJ.getString("name"));

			Integer sumNum = 0, pointNum = 0;
			Double sumFee = 0.0, pointFee = 0.0;

			for (int j = 0; j < itemTypeFeeDetails.size(); j++) {
				JSONObject itemTypeFeeDetailJ = itemTypeFeeDetails.getJSONObject(j);
				if (serviceTypeJ.getString("no").equals(itemTypeFeeDetailJ.getString("typeNo"))) {
					Integer count = itemTypeFeeDetailJ.getInteger("count");
					Integer pointType = itemTypeFeeDetailJ.getInteger("pointType");
					Double fee = itemTypeFeeDetailJ.getDouble("fee");

					sumNum += count;
					sumFee += fee;
					if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
						pointNum += count;
						pointFee += fee;
					}
				}
			}

			rowData.add(sumNum);
			rowData.add(sumFee);
			rowData.add(sumNum > 0 ? (sumFee / sumNum) : 0);
			rowData.add(pointNum);
			rowData.add(pointFee);
			rowData.add(sumNum > 0 ? FormatUtil.formatNum(((double) pointNum / (double) sumNum) * 100, "0") + "%" : 0);
			rowData.add(serviceTypeJ.getString("no"));

			data.add(rowData);
		}
		respBody.put("data", data);
		resp.setBody(respBody);
		return resp;
	}

	/**
	 * 项目分类业绩汇总表
	 */
	@Override
	public CommObjResponse<JSONObject> itemFeeDetailSheet(BeautyRequest<JSONObject> req) throws Exception {
		String tbuser = req.getSessionUser().getTbuser();
		Integer eid = req.getEid();
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = jsonUtil.readJSON("itemFeeDetailSheet.json", req.getLang());

		JSONArray columns = respBody.getJSONArray("columns");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		for (int k = 0; k < columns.size(); k++) {
			JSONObject column = columns.getJSONObject(k);
			if (JSONUtil.getNotNullDouble(column, "decimal") > 0)
				column.put("decimal", decimal);
		}
		respBody.put("columns", columns);

		JSONArray data = new JSONArray();

		Map<String, JSONObject> tempMap = new HashMap<>();
		JSONArray itemFeeDetails = reportDao.itemFeeDetails(tbuser, eid, req.getBody());

		for (int i = 0; i < itemFeeDetails.size(); i++) {
			JSONObject itemFeeDetailJ = itemFeeDetails.getJSONObject(i);
			String itemName = itemFeeDetailJ.getString("itemName");
			Integer count = itemFeeDetailJ.getInteger("count");
			Integer pointType = itemFeeDetailJ.getInteger("pointType");
			Double fee = itemFeeDetailJ.getDouble("fee");
			String sex = itemFeeDetailJ.getString("sex");

			JSONObject rowData = null;
			Integer sumNum = 0, pointNum = 0, unpointNum = 0, femaleNum = 0, maleNum = 0;
			Double sumFee = 0.0, pointFee = 0.0, unpointFee = 0.0, femaleFee = 0.0, maleFee = 0.0;

			if (tempMap.containsKey(itemName)) {
				rowData = tempMap.get(itemName);
				sumNum = rowData.getInteger("sumNum");
				pointNum = rowData.getInteger("pointNum");
				unpointNum = rowData.getInteger("unpointNum");
				femaleNum = rowData.getInteger("femaleNum");
				maleNum = rowData.getInteger("maleNum");
				sumFee = rowData.getDouble("sumFee");
				pointFee = rowData.getDouble("pointFee");
				unpointFee = rowData.getDouble("unpointFee");
				femaleFee = rowData.getDouble("femaleFee");
				maleFee = rowData.getDouble("maleFee");
			} else {
				rowData = new JSONObject();
				rowData.put("itemName", itemName);
			}

			sumNum += count;
			sumFee += fee;
			if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
				pointNum += count;
				pointFee += fee;
			} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
				unpointNum += count;
				unpointFee += fee;
			}

			if (sex != null) {
				if (sex.equals(BeautyDoConsts.SEX_MALE)) {
					maleNum += count;
					maleFee += fee;
				} else if (sex != null && sex.equals(BeautyDoConsts.SEX_FEMALE)) {
					femaleNum += count;
					femaleFee += fee;
				}
			}

			rowData.put("sumNum", sumNum);
			rowData.put("pointNum", pointNum);
			rowData.put("unpointNum", unpointNum);
			rowData.put("femaleNum", femaleNum);
			rowData.put("maleNum", maleNum);
			rowData.put("sumFee", sumFee);
			rowData.put("pointFee", pointFee);
			rowData.put("unpointFee", unpointFee);
			rowData.put("femaleFee", femaleFee);
			rowData.put("maleFee", maleFee);

			tempMap.put(itemName, rowData);
		}

		if (!tempMap.isEmpty()) {
			Iterator<JSONObject> rowDatas = tempMap.values().iterator();
			while (rowDatas.hasNext()) {
				JSONObject rowDataJ = rowDatas.next();
				JSONArray rowData = new JSONArray();
				rowData.add(rowDataJ.getString("itemName"));
				rowData.add(rowDataJ.getInteger("sumNum"));
				rowData.add(rowDataJ.getDouble("sumFee"));
				rowData.add(rowDataJ.getInteger("sumNum") > 0
						? (rowDataJ.getDouble("sumFee") / rowDataJ.getInteger("sumNum")) : 0);
				rowData.add(rowDataJ.getInteger("pointNum"));
				rowData.add(rowDataJ.getDouble("pointFee"));
				rowData.add(rowDataJ.getInteger("unpointNum"));
				rowData.add(rowDataJ.getDouble("unpointFee"));
				rowData.add(rowDataJ.getInteger("maleNum"));
				rowData.add(rowDataJ.getDouble("maleFee"));
				rowData.add(rowDataJ.getInteger("femaleNum"));
				rowData.add(rowDataJ.getDouble("femaleFee"));
				data.add(rowData);
			}
		}

		respBody.put("data", data);
		resp.setBody(respBody);
		return resp;
	}

	@Override
	/**
	 * 现金流水报表
	 */
	public CommObjResponse<JSONObject> sumCashReport(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		CommObjResponse<JSONObject> resp = new CommObjResponse<JSONObject>();
		JSONObject table = jsonUtil.readJSON("sumCashReport.json", req.getLang());
		List<Object> data = new ArrayList<>();// 报表数据
		List<String> noList = new ArrayList<String>();// 支付编号集合
		List<String> payNameList = new ArrayList<String>();// 支付名称集合

		JSONArray payTypes = sessionUser.getBasicType("payTypes");
		for (int i = 0; i < payTypes.size(); i++) {
			if (payTypes.getJSONObject(i).getString("type").equals("6")) {
				payNameList.add(payTypes.getJSONObject(i).getString("name"));// 获取支付名称集合
				noList.add(payTypes.getJSONObject(i).getString("no"));// 获取支付编号集合
			}
		}
		/*
		 * 设置1级表头
		 */
		JSONArray headTop = table.getJSONArray("headTop");
		String size = String.valueOf(noList.size());
		for (int i = 1; i < headTop.size(); i++) {
			JSONObject headItem = headTop.getJSONObject(i);
			headItem.put("colspan", size);
		}
		table.put("headTop", headTop);

		// 设置二级表头
		JSONArray head = table.getJSONArray("head");
		for (int k = 0; k < headTop.size() - 1; k++) {
			for (String name : payNameList) {
				head.add(name);
			}
		}
		table.put("head", head);

		// 添加行配置
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		JSONArray columns = table.getJSONArray("columns");
		for (int k = 0; k < headTop.size() - 1; k++) {
			for (int j = 0; j < noList.size(); j++) {
				JSONObject column = new JSONObject();
				column.put("width", 150);
				column.put("sortable", "0");
				column.put("decimal", decimal);
				columns.add(column);
			}
		}
		table.put("columns", columns);

		JSONArray bdayList = reportDao.sumBday(tbuser, req.getEid(), req.getSid(), req.getBody());// 查询日期集合
		JSONArray pays = reportDao.sumCashReport(tbuser, req.getEid(), req.getSid(), noList, req.getBody());// 查询单据集合
		for (int i = 0; i < bdayList.size(); i++) {// 按日期循环
			String bday = bdayList.getJSONObject(i).getString("BDAY");
			JSONArray rowData = new JSONArray();// 每一行的数据
			if(req.getLang() != null && req.getLang().equals("vi-VN")){
				rowData.add(DateUtil.dateToLangString(bdayList.getJSONObject(i).getDate("BDAY")));
			}
			JSONArray workList = new JSONArray();// 消费收取现金集合
			JSONArray saleList = new JSONArray();// 卖品现金集合
			JSONArray cardList = new JSONArray();// 开卡现金集合
			JSONArray rechargeList = new JSONArray();// 充值现金集合
			JSONArray packageList = new JSONArray();// 套餐现金集合
			for (int t = 0; t < pays.size(); t++) {// 按支付单据循环
				JSONObject pay = pays.getJSONObject(t);
				if (bday.equals(pay.getString("BDAY"))) {
					for (String no : noList) {
						if (pay.getString("BTYPE").equals("1")) {
							if (pay.get(no) == null) {
								workList.add(0);
							} else {
								workList.add(pay.getString(no));
							}
						} else if (pay.getString("BTYPE").equals("2")) {
							if (pay.get(no) == null) {
								saleList.add(0);
							} else {
								saleList.add(pay.getString(no));
							}
						} else if (pay.getString("BTYPE").equals("3")) {
							if (pay.get(no) == null) {
								cardList.add(0);
							} else {
								cardList.add(pay.getString(no));
							}
						} else if (pay.getString("BTYPE").equals("4")) {
							if (pay.get(no) == null) {
								rechargeList.add(0);
							} else {
								rechargeList.add(pay.getString(no));
							}
						} else if (pay.getString("BTYPE").equals("5")) {
							if (pay.get(no) == null) {
								packageList.add(0);
							} else {
								packageList.add(pay.getString(no));
							}
						}

					}
				}
			}
			if (workList.size() == 0) {
				for (int u = 0; u < noList.size(); u++) {
					workList.add(0);
				}
			}
			if (saleList.size() == 0) {
				for (int u = 0; u < noList.size(); u++) {
					saleList.add(0);
				}
			}
			if (cardList.size() == 0) {
				for (int u = 0; u < noList.size(); u++) {
					cardList.add(0);
				}
			}
			if (rechargeList.size() == 0) {
				for (int u = 0; u < noList.size(); u++) {
					rechargeList.add(0);
				}
			}
			if (packageList.size() == 0) {
				for (int u = 0; u < noList.size(); u++) {
					packageList.add(0);
				}
			}
			for (int j = 0; j < workList.size(); j++) {
				rowData.add(workList.get(j));
			}
			for (int j = 0; j < saleList.size(); j++) {
				rowData.add(saleList.get(j));
			}
			for (int j = 0; j < cardList.size(); j++) {
				rowData.add(cardList.get(j));
			}
			for (int j = 0; j < rechargeList.size(); j++) {
				rowData.add(rechargeList.get(j));
			}
			for (int j = 0; j < packageList.size(); j++) {
				rowData.add(packageList.get(j));
			}
			data.add(rowData);
		}
		table.put("data", data);
		resp.setBody(table);
		return resp;
	}

	/**
	 * 卡金变动流水
	 */
	@Override
	public CommObjResponse<JSONObject> shopCardRecord(BeautyRequest<JSONObject> req) throws Exception {
		CommObjResponse<JSONObject> resp = new CommObjResponse<JSONObject>();
		String tbuser = req.getSessionUser().getTbuser();
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		Date startDate = req.getBody().getDate("startDate");
		Date endDate = req.getBody().getDate("endDate");
		String lang = req.getLang();
		JSONArray cardRecordList = reportDao.cardFeeWater(tbuser, eid, sid, startDate, endDate);// 查询月份的卡金变动流水
		CardRecord cardRecord = reportDao.cardFeeMap(tbuser, eid, sid, endDate);// 获取结束日期之后的变动（增加的卡金和赠送金）
		Card memCard = cardDao.getSumCardFeeBySid(sid);// 获取门店当前的总卡金和赠送金
		double cardfee = 0;
		double presentfee = 0;
		if (memCard != null) {
			cardfee = memCard.getSumCardFee();
			presentfee = memCard.getSumPresentFee();
		}
		if (cardRecord != null) {
			cardfee = cardfee - cardRecord.getPayCard();// 查询月份最后一天的卡余额
			presentfee = presentfee - cardRecord.getPayPresent();//// 查询月份最后一天的赠送金
		}

		List<JSONObject> list = new ArrayList<JSONObject>();

		Map<String, JSONObject> tempMap = new LinkedHashMap<String, JSONObject>();

		for (int i = 0; i < cardRecordList.size(); i++) {
			JSONObject data = null;
			JSONObject obj = cardRecordList.getJSONObject(i);
			String workDate = obj.getString("WORKDATE");
			if (lang != null && lang.equals("vi-VN")) {
				workDate = DateUtil.dateToLangString(obj.getDate("WORKDATE"));
			}
			double payCardFee = obj.getDoubleValue("PAYCARDFEE");
			double payPresentFee = obj.getDoubleValue("PAYPRESENTFEE");
			int type = obj.getInteger("TYPE");
			if (!tempMap.containsKey(workDate)) {
				data = new JSONObject();
				tempMap.put(workDate, data);
				data.put("workdate", workDate);
				data.put("payCardFee", payCardFee);
				data.put("payPresentFee", payPresentFee);
			} else {
				data = tempMap.get(workDate);
				double cardFee = data.getDoubleValue("payCardFee") + payCardFee;
				data.put("payCardFee", cardFee);
				double presentFee = data.getDoubleValue("payPresentFee") + payPresentFee;
				data.put("payPresentFee", presentFee);
			}
			if (obj.getIntValue("SID") == sid) {// 本店流水变动
				if (type == BeautyDoConsts.CARDRECORD_TYPE_NEWCARD || type == BeautyDoConsts.CARDRECORD_TYPE_CHARGE) {// 开卡或充值
					double bchargeCardFee = data.getDoubleValue("bChargeCardFee") + payCardFee;// 本店充值卡金
					data.put("bChargeCardFee", bchargeCardFee);
					double bChargePresentFee = data.getDoubleValue("bChargePresentFee") + payPresentFee;// 本店充值赠送金
					data.put("bChargePresentFee", bChargePresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_SERVICE || type == BeautyDoConsts.CARDRECORD_TYPE_PRODUCT) {// 消费
					double bServiceCardFee = data.getDoubleValue("bServiceCardFee") + payCardFee;// 本店消费卡金
					data.put("bServiceCardFee", bServiceCardFee);
					double bServicePresentFee = data.getDoubleValue("bServicePresentFee") + payPresentFee;// 本店消费赠送金
					data.put("bServicePresentFee", bServicePresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_PACK) {// 购买套餐
					double bPackCardFee = data.getDoubleValue("bPackCardFee") + payCardFee;// 本店购买套餐卡金
					data.put("bPackCardFee", bPackCardFee);
					double bPackPresentFee = data.getDoubleValue("bPackPresentFee") + payPresentFee;// 本店购买套餐赠送金
					data.put("bPackPresentFee", bPackPresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_RECOVER) {// 恢复会员卡
					double bRecoverCardFee = data.getDoubleValue("bRecoverCardFee") + payCardFee;// 恢复卡金
					data.put("bRecoverCardFee", bRecoverCardFee);
					double bRecoverPresentFee = data.getDoubleValue("bRecoverPresentFee") + payPresentFee;// 恢复赠送金
					data.put("bRecoverPresentFee", bRecoverPresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_DEL) {// 删除会员卡
					double bDelCardFee = data.getDoubleValue("bDelCardFee") + payCardFee;// 删除卡金
					data.put("bDelCardFee", bDelCardFee);
					double bDelPresentFee = data.getDoubleValue("bDelPresentFee") + payPresentFee;// 删除赠送金
					data.put("bDelPresentFee", bDelPresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_IMPROT) {// 导入会员卡
					double bImprotCardFee = data.getDoubleValue("bImprotCardFee") + payCardFee;// 导入卡金
					data.put("bImprotCardFee", bImprotCardFee);
					double bImprotPresentFee = data.getDoubleValue("bImprotPresentFee") + payPresentFee;// 导入赠送金
					data.put("bImprotPresentFee", bImprotPresentFee);

				}
			} else {// 跨店流水变动
				if (type == BeautyDoConsts.CARDRECORD_TYPE_NEWCARD || type == BeautyDoConsts.CARDRECORD_TYPE_CHARGE) {// 开卡或充值
					double kchargeCardFee = data.getDoubleValue("kchargeCardFee") + payCardFee;// 跨店充值卡金
					data.put("kchargeCardFee", kchargeCardFee);
					double kChargePresentFee = data.getDoubleValue("kChargePresentFee") + payPresentFee;// 跨店充值赠送金
					data.put("kChargePresentFee", kChargePresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_SERVICE || type == BeautyDoConsts.CARDRECORD_TYPE_PRODUCT) {// 消费
					double kServiceCardFee = data.getDoubleValue("kServiceCardFee") + payCardFee;// 跨店消费卡金
					data.put("kServiceCardFee", kServiceCardFee);
					double kServicePresentFee = data.getDoubleValue("kServicePresentFee") + payPresentFee;// 跨店消费赠送金
					data.put("kServicePresentFee", kServicePresentFee);
				}
				if (type == BeautyDoConsts.CARDRECORD_TYPE_PACK) {// 购买套餐
					double kPackCardFee = data.getDoubleValue("kPackCardFee") + payCardFee;// 跨店购买套餐卡金
					data.put("kPackCardFee", kPackCardFee);
					double kPackPresentFee = data.getDoubleValue("kPackPresentFee") + payPresentFee;// 跨店购买套餐赠送金
					data.put("kPackPresentFee", kPackPresentFee);
				}
			}

		}
		list.addAll(tempMap.values());
		Collections.sort(list, new Comparator<JSONObject>() {
			public int compare(JSONObject arg0, JSONObject arg1) {
				return arg1.getString("workdate").compareTo(arg0.getString("workdate"));
			}
		});
		JSONArray data = new JSONArray();
		double workDateCardfee = cardfee;
		double workDatePresentfee = presentfee;
		double payCardFee = 0;
		double payPresentFee = 0;
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = list.get(i);
			workDateCardfee -= payCardFee;
			workDatePresentfee -= payPresentFee;
			payCardFee = obj.getDoubleValue("payCardFee");
			payPresentFee = obj.getDoubleValue("payPresentFee");
			JSONArray rowdata = new JSONArray();
			rowdata.add(obj.getString("workdate"));
			rowdata.add(obj.getDoubleValue("bChargeCardFee"));
			rowdata.add(obj.getDoubleValue("bChargePresentFee"));
			rowdata.add(obj.getDoubleValue("kchargeCardFee"));
			rowdata.add(obj.getDoubleValue("kChargePresentFee"));
			rowdata.add(obj.getDoubleValue("bServiceCardFee"));
			rowdata.add(obj.getDoubleValue("bServicePresentFee"));
			rowdata.add(obj.getDoubleValue("kServiceCardFee"));
			rowdata.add(obj.getDoubleValue("kServicePresentFee"));
			rowdata.add(obj.getDoubleValue("bPackCardFee"));
			rowdata.add(obj.getDoubleValue("bPackPresentFee"));
			rowdata.add(obj.getDoubleValue("kPackCardFee"));
			rowdata.add(obj.getDoubleValue("kPackPresentFee"));
			rowdata.add(obj.getDoubleValue("bDelCardFee"));
			rowdata.add(obj.getDoubleValue("bDelPresentFee"));
			rowdata.add(obj.getDoubleValue("bRecoverCardFee"));
			rowdata.add(obj.getDoubleValue("bRecoverPresentFee"));
			rowdata.add(obj.getDoubleValue("bImprotCardFee"));
			rowdata.add(obj.getDoubleValue("bImprotPresentFee"));
			rowdata.add(workDateCardfee);
			rowdata.add(workDatePresentfee);
			data.add(rowdata);
		}
		JSONObject body = jsonUtil.readJSON("cardWaterSheet.json", req.getLang());
		JSONArray columns = body.getJSONArray("columns");
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		for (int k = 0; k < columns.size(); k++) {
			columns.getJSONObject(k).put("decimal", decimal);
		}
		body.put("columns", columns);
		body.put("data", data);
		resp.setBody(body);
		return resp;
	}

	/**
	 * 店内业绩统计表
	 */
	@Override
	public CommObjResponse<JSONObject> shopSummarySheet(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		JSONObject body = req.getBody();
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject respBody = new JSONObject();
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		// 客数业绩统计
		JSONObject customerSummary = new JSONObject();
		JSONArray customerJArr = reportDao.customerSummary(tbuser, eid, body);
		Integer sumCount = 0, // 总客数
				maleCount = 0, // 男客数
				femaleCount = 0, // 女客数
				pointCount = 0, // 指定客数
				unpointCount = 0, // 非指定客数
				memberCount = 0, // 会员客数
				fitCount = 0; // 散客数
		Double sumFee = 0.0, // 总业绩
				maleFee = 0.0, // 男客业绩
				femaleFee = 0.0, // 女客业绩
				pointFee = 0.0, // 指定业绩
				unpointFee = 0.0, // 非指定业绩
				memberFee = 0.0, // 会员业绩
				fitFee = 0.0; // 散客业绩

		Long tempArr[] = null;
		for (int i = 0; i < customerJArr.size(); i++) {
			JSONObject customerJ = customerJArr.getJSONObject(i);
			Long billId = customerJ.getLong("billId");
			Integer count = customerJ.getInteger("count");
			Integer pointType = customerJ.getInteger("pointType");
			Integer ctype = customerJ.getInteger("ctype");
			String sex = customerJ.getString("sex");
			Double fee = customerJ.getDouble("fee");

			if (ArrayUtils.contains(tempArr, billId))
				count = 0;
			else
				tempArr = ArrayUtils.add(tempArr, billId);

			sumCount += count;
			sumFee += fee;
			if (sex != null) {
				if (sex.equals(BeautyDoConsts.SEX_MALE)) {
					maleCount += count;
					maleFee += fee;
				} else if (sex.equals(BeautyDoConsts.SEX_FEMALE)) {
					femaleCount += count;
					femaleFee += fee;
				}
			}

			if (pointType != null) {
				if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
					pointCount += count;
					pointFee += fee;
				} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
					unpointCount += count;
					unpointFee += fee;
				}
			}

			if (ctype != null) {
				if (ctype.equals(BeautyDoConsts.BILL_CTYPE_CARD) || ctype.equals(BeautyDoConsts.BILL_CTYPE_PACK)) {
					memberFee += fee;
					memberCount += count;
				} else if (ctype.equals(BeautyDoConsts.BILL_CTYPE_FIT)) {
					fitFee += fee;
					fitCount += count;
				}
			}
		}
		customerSummary.put("sumCount", sumCount);
		customerSummary.put("maleCount", maleCount);
		customerSummary.put("femaleCount", femaleCount);
		customerSummary.put("pointCount", pointCount);
		customerSummary.put("unpointCount", unpointCount);
		customerSummary.put("memberCount", memberCount);
		customerSummary.put("fitCount", fitCount);
		customerSummary.put("sumFee", FormatUtil.formatNum(sumFee, decimal));
		customerSummary.put("maleFee", FormatUtil.formatNum(maleFee, decimal));
		customerSummary.put("femaleFee", FormatUtil.formatNum(femaleFee, decimal));
		customerSummary.put("pointFee", FormatUtil.formatNum(pointFee, decimal));
		customerSummary.put("unpointFee", FormatUtil.formatNum(unpointFee, decimal));
		customerSummary.put("memberFee", FormatUtil.formatNum(memberFee, decimal));
		customerSummary.put("fitFee", FormatUtil.formatNum(fitFee, decimal));
		respBody.put("customerSummary", customerSummary);

		// 支付方式统计
		JSONArray payTypes = sessionUser.getBasicType("payTypes");
		body.put("payTypes", payTypes);
		JSONArray incomeSummary = new JSONArray();

		JSONArray incomeJArr = reportDao.incomeSummary(tbuser, eid, body);
		for (int i = 0; i < payTypes.size(); i++) {
			JSONObject payType = payTypes.getJSONObject(i);
			String payTypeNo = payType.getString("no");
			JSONObject rowData = new JSONObject();
			rowData.put("no", payTypeNo);
			rowData.put("name", payType.getString("name"));
			rowData.put("fee", FormatUtil.formatNum(0, decimal));
			rowData.put("serviceFee", FormatUtil.formatNum(0, decimal));
			rowData.put("productFee", FormatUtil.formatNum(0, decimal));
			rowData.put("newcardFee", FormatUtil.formatNum(0, decimal));
			rowData.put("chargeFee", FormatUtil.formatNum(0, decimal));
			rowData.put("packFee", FormatUtil.formatNum(0, decimal));
			for (int j = 0; j < incomeJArr.size(); j++) {
				JSONObject incomeJ = incomeJArr.getJSONObject(j);
				Integer btype = incomeJ.getInteger("btype");
				if (incomeJ.containsKey(payTypeNo)) {
					Double fee = incomeJ.getDouble(payTypeNo);
					rowData.put("fee", FormatUtil.formatNum(rowData.getDouble("fee") + fee, decimal));

					switch (btype) {
					case BeautyDoConsts.BILL_BTYPE_SERVICE:
						rowData.put("serviceFee", FormatUtil.formatNum(rowData.getDouble("serviceFee") + fee, decimal));
						break;
					case BeautyDoConsts.BILL_BTYPE_PRODUCT:
						rowData.put("productFee", FormatUtil.formatNum(rowData.getDouble("productFee") + fee, decimal));
						break;
					case BeautyDoConsts.BILL_BTYPE_NEWCARD:
						rowData.put("newcardFee", FormatUtil.formatNum(rowData.getDouble("newcardFee") + fee, decimal));
						break;
					case BeautyDoConsts.BILL_BTYPE_CHARGE:
						rowData.put("chargeFee", FormatUtil.formatNum(rowData.getDouble("chargeFee") + fee, decimal));
						break;
					case BeautyDoConsts.BILL_BTYPE_PACK:
						rowData.put("packFee", FormatUtil.formatNum(rowData.getDouble("packFee") + fee, decimal));
						break;
					}
				}
			}
			incomeSummary.add(rowData);
		}
		respBody.put("incomeSummary", incomeSummary);

		// 项目/卖品/会员卡开卡、充卡/项目套餐统计
		JSONArray serviceSummary = new JSONArray();
		JSONArray productSummary = new JSONArray();
		JSONArray newcardSummary = new JSONArray();
		JSONArray chargeSummary = new JSONArray();
		JSONArray packSummary = new JSONArray();

		Map<String, JSONObject> tempServiceMap = new HashMap<>();
		Map<String, JSONObject> tempProductMap = new HashMap<>();
		Map<String, JSONObject> tempNewcardMap = new HashMap<>();
		Map<String, JSONObject> tempChargeMap = new HashMap<>();
		Map<String, JSONObject> tempPackMap = new HashMap<>();

		JSONArray itemJArr = reportDao.itemSummary(tbuser, eid, body);
		for (int i = 0; i < itemJArr.size(); i++) {
			JSONObject itemJ = itemJArr.getJSONObject(i);
			itemJ.put("fee", FormatUtil.formatNum(itemJ.getDouble("fee"), decimal));
			Integer btype = itemJ.getInteger("btype");
			String itemName = itemJ.getString("itemName");
			Double fee = itemJ.getDouble("fee");
			Integer count = itemJ.getInteger("count");
			Integer pointType = itemJ.getInteger("pointType");

			JSONObject tempJ = null;
			if (btype.equals(BeautyDoConsts.BILL_BTYPE_SERVICE)) {
				Integer pointNum = 0, unpointNum = 0;
				if (tempServiceMap.containsKey(itemName)) {
					tempJ = tempServiceMap.get(itemName);
					pointNum = tempJ.getInteger("pointNum");
					unpointNum = tempJ.getInteger("unpointNum");
					if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
						pointNum += count;
					} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
						unpointNum += count;
					}
				} else {
					tempJ = new JSONObject();
					if (pointType.equals(BeautyDoConsts.POINT_TYPE_POINT)) {
						pointNum = count;
					} else if (pointType.equals(BeautyDoConsts.POINT_TYPE_UNPOINT)) {
						unpointNum = count;
					}
				}

				fee += JSONUtil.getNotNullDouble(tempJ, "fee");
				count += JSONUtil.getNotNullInteger(tempJ, "count");
				tempJ.put("itemName", itemName);
				tempJ.put("fee", FormatUtil.formatNum(fee, decimal));
				tempJ.put("count", count);
				tempJ.put("pointNum", pointNum);
				tempJ.put("unpointNum", unpointNum);
				tempServiceMap.put(itemName, tempJ);
			} else {
				switch (btype) {
				case BeautyDoConsts.BILL_BTYPE_PRODUCT:
					if (tempProductMap.containsKey(itemName)) {
						tempJ = tempProductMap.get(itemName);
					} else {
						tempJ = new JSONObject();
					}

					fee += JSONUtil.getNotNullDouble(tempJ, "fee");
					count += JSONUtil.getNotNullInteger(tempJ, "count");
					tempJ.put("itemName", itemName);
					tempJ.put("fee", FormatUtil.formatNum(fee, decimal));
					tempJ.put("count", count);
					tempProductMap.put(itemName, tempJ);
					break;
				case BeautyDoConsts.BILL_BTYPE_NEWCARD:
					if (tempNewcardMap.containsKey(itemName)) {
						tempJ = tempNewcardMap.get(itemName);
					} else {
						tempJ = new JSONObject();
					}

					fee += JSONUtil.getNotNullDouble(tempJ, "fee");
					count += JSONUtil.getNotNullInteger(tempJ, "count");
					tempJ.put("itemName", itemName);
					tempJ.put("fee", FormatUtil.formatNum(fee, decimal));
					tempJ.put("count", count);
					tempNewcardMap.put(itemName, tempJ);
					break;
				case BeautyDoConsts.BILL_BTYPE_CHARGE:
					if (tempChargeMap.containsKey(itemName)) {
						tempJ = tempChargeMap.get(itemName);
					} else {
						tempJ = new JSONObject();
					}

					fee += JSONUtil.getNotNullDouble(tempJ, "fee");
					count += JSONUtil.getNotNullInteger(tempJ, "count");
					tempJ.put("itemName", itemName);
					tempJ.put("fee", FormatUtil.formatNum(fee, decimal));
					tempJ.put("count", count);
					tempChargeMap.put(itemName, tempJ);
					break;
				case BeautyDoConsts.BILL_BTYPE_PACK:
					if (tempPackMap.containsKey(itemName)) {
						tempJ = tempPackMap.get(itemName);
					} else {
						tempJ = new JSONObject();
					}

					fee += JSONUtil.getNotNullDouble(tempJ, "fee");
					count += JSONUtil.getNotNullInteger(tempJ, "count");
					tempJ.put("itemName", itemName);
					tempJ.put("fee", FormatUtil.formatNum(fee, decimal));
					tempJ.put("count", count);
					tempPackMap.put(itemName, tempJ);
					break;
				}
			}
		}

		serviceSummary.addAll(tempServiceMap.values());
		productSummary.addAll(tempProductMap.values());
		newcardSummary.addAll(tempNewcardMap.values());
		chargeSummary.addAll(tempChargeMap.values());
		packSummary.addAll(tempPackMap.values());
		respBody.put("serviceSummary", serviceSummary);
		respBody.put("productSummary", productSummary);
		respBody.put("newcardSummary", newcardSummary);
		respBody.put("chargeSummary", chargeSummary);
		respBody.put("packSummary", packSummary);

		resp.setBody(respBody);
		return resp;
	}

}
