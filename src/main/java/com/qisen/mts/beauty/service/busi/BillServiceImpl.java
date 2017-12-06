package com.qisen.mts.beauty.service.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.busi.BillDao;
import com.qisen.mts.beauty.dao.busi.BillDetailDao;
import com.qisen.mts.beauty.dao.busi.EmpFeeDao;
import com.qisen.mts.beauty.dao.busi.PayDao;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.BillDetail;
import com.qisen.mts.beauty.model.entity.busi.EmpFee;
import com.qisen.mts.beauty.model.entity.busi.Pay;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.beauty.service.rule.EmpAchiCalcKit;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.dao.mem.CardRecordDao;
import com.qisen.mts.common.dao.mem.MemItemDao;
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.dao.sto.StoBillDao;
import com.qisen.mts.common.dao.sto.StoBillDetailDao;
import com.qisen.mts.common.dao.sto.StoItemDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.CardRecord;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.util.CollectionUtil;
import com.qisen.mts.gateway.ExceptionWithCode;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	@Autowired
	private BillDetailDao billDetailDao;
	@Autowired
	private EmpFeeDao empFeeDao;
	@Autowired
	private PayDao payDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CardDao cardDao;
	@Autowired
	private CardRecordDao cardRecordDao;
	@Autowired
	private MemItemDao memItemDao;
	@Autowired
	private StoBillDao stoBillDao;
	@Autowired
	private StoBillDetailDao stoBillDetailDao;
	@Autowired
	private StoItemDao stoItemDao;

	@Override
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req) {
		String tbuser = req.getSessionAccount().getMetaString("tbuser");
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		JSONObject body = req.getBody();
		JSONArray summary = billDao.summary(tbuser, eid, sid, body);
		Integer count = 0;
		JSONObject feeMap = new JSONObject();
		body.put("sids", new Integer[] { sid });
		double servicePerf = 0;
		double productPerf = 0;
		double cardPerf = 0;
		double chargePerf = 0;
		double packPerf = 0;
		for (int i = 0; i < summary.size(); i++) {
			JSONObject sumPerfJ = summary.getJSONObject(i);
			Double fee = sumPerfJ.getDouble("fee");
			Integer btype = sumPerfJ.getInteger("btype");
			count += sumPerfJ.getInteger("count");
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
		feeMap.put("servicePerf", servicePerf);
		feeMap.put("productPerf", productPerf);
		feeMap.put("cardPerf", cardPerf);
		feeMap.put("chargePerf", chargePerf);
		feeMap.put("packPerf", packPerf);

		PageResponse<JSONObject> resp = new PageResponse<>(req.getPageNum(), req.getPageSize(), count);
		JSONObject respBody = new JSONObject();
		if (!NumberUtils.isNumber(body.getString("billId")))
			respBody.put("feeMap", feeMap);
		if (count != null && count > 0) {
			List<Bill> billList = billDao.list(tbuser, req.getEid(), req.getSid(), req.getBody(), req.getStartIndex(),
					req.getEndIndex());
			List<BillDetail> billDetailList = billDetailDao.list4Bill(tbuser, eid, sid, billList);
			List<EmpFee> empFeeList = empFeeDao.list4Bill(tbuser, eid, sid, billList);
			List<Pay> payList = payDao.list4Bill(tbuser, eid, sid, billList);

			List<JSONObject> bills = new ArrayList<>();
			for (Bill bill : billList) {
				JSONObject billJ = bill.toJSON();

				List<JSONObject> details = new ArrayList<>();
				for (BillDetail billDetail : billDetailList) {
					if (billDetail.getBillId().equals(bill.getId())) {
						JSONObject billDetailJ = billDetail.toJSON();
						List<EmpFee> emps = new ArrayList<>();
						for (EmpFee empFee : empFeeList) {
							if (empFee.getBillId().equals(bill.getId())
									&& empFee.getDetailId().equals(billDetail.getId()))
								emps.add(empFee);
						}
						billDetailJ.put("emps", emps);
						details.add(billDetailJ);
					}
				}
				billJ.put("details", details);
				for (Pay pay : payList) {
					if (pay.getBillId().equals(bill.getId()) && pay.getDepCode().equals("ALL"))
						billJ.put("pay", pay);
				}
				bills.add(billJ);
			}

			respBody.put("bills", bills);
		}

		resp.setBody(respBody);
		return resp;
	}

	@Override
	public BaseResponse status(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		BaseResponse resp = new BaseResponse();
		JSONObject body = req.getBody();
		Integer status = body.getInteger("status");
		Long billId = body.getLong("id");
		// 基础验证

		Integer result = billDao.status(tbuser, eid, sid, billId, status);
		if (result == null || !result.equals(1)) {
			resp.setResult(ResultCode.FAILED);
			return resp;
		}

		// 非撤单操作不执行回滚
		if (!status.equals(0)) {
			return resp;
		}
		// 单据明细
		billDetailDao.cancel(tbuser, eid, sid, billId);
		// 服务/提成员工
		empFeeDao.cancel(tbuser, eid, sid, billId);
		// 支付流水
		payDao.cancel(tbuser, eid, sid, billId);

		Bill bill = billDao.find(tbuser, eid, sid, billId);

		double totalPay = 0;
		// 会员数据回滚
		if (bill.getMemId() != null && bill.getMemId() > 0) {
			// 查询总消费金额只包含卡金和现金类支付方式
			Pay pay = payDao.findByDepCode(tbuser, eid, sid, billId, "ALL");
			totalPay = pay.getTotalPay();
			JSONArray payTypes = sessionUser.getBasicType("payTypes");
			for (int j = 0; j < payTypes.size(); j++) {
				JSONObject payType = payTypes.getJSONObject(j);
				if (payType.getInteger("type").equals(7) && !"CARD".equals(payType.getString("no"))
						&& !"PACK".equals(payType.getString("no"))) {
					Double typeFee = pay.getPay().getDoubleValue(payType.getString("no"));
					totalPay -= typeFee == null ? 0 : typeFee;
				}
			}

			// 消费次数/最后消费时间
			if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE)
					|| bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PRODUCT)) {
				// 首次消费时间
				result = memberDao.rollback4FirstConsume(eid, bill.getMemId());
				Date bday = billDao.findLastBday4Member(tbuser, eid, bill.getMemId());
				String lastEmp = billDao.findLastEmp4Member(tbuser, eid, bill.getMemId());
				if (StringUtils.isBlank(lastEmp)) {
					lastEmp = "";
				}
				result = memberDao.updateConsumeInfo(eid, bill.getMemId(), bday, -totalPay, 1, lastEmp);
				if (result == null || !result.equals(1)) {
					throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_MEMBER_FAILED);
				}
			}

		}

		// 会员卡数据回滚
		if (bill.getMemCardId() != null && bill.getMemCardId() > 0) {
			Double cardFee = 0.0;
			Double presentFee = 0.0;
			CardRecord historyCardRecord = cardRecordDao.findByBillId(tbuser, eid, billId);
			if (historyCardRecord != null) {
				if (historyCardRecord.getPayCard() != null) {
					cardFee = historyCardRecord.getPayCard();
				}
				if (historyCardRecord.getPayPresent() != null) {
					presentFee = historyCardRecord.getPayPresent();
				}
			}
			// 开卡充值
			if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD)
					|| bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE)) {
				result = cardDao.rollback4Charge(eid, bill.getMemCardId(), cardFee, presentFee);
				// 撤销开卡,删除会员卡
				if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD)) {
					result = cardDao.deleteStatus(eid, sid, req.getBody());
				}
				if (result == null || !result.equals(1)) {
					throw new ExceptionWithCode(MsgCode.BILL_CANCEL_NOT_ENOUGH_CARDFEE);
				}
			} else {
				result = cardDao.rollback4Pay(eid, bill.getMemCardId(), -cardFee, -presentFee);
				// 非套餐消费，回滚消费金额
				if (result != null && result.equals(1) && !bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PACK)) {
					result = cardDao.updateConsumeInfo(eid, bill.getMemCardId(), -totalPay, 1);
				}
				if (result == null || !result.equals(1)) {
					throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_CARD_FAILED);
				}
			}

			// 卡金变动流水
			if (historyCardRecord != null) {
				result = cardRecordDao.rollback4Bill(tbuser, eid, billId);
				if (result == null || !result.equals(1)) {
					throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_CARD_FAILED);
				}
				Card card = cardDao.find(eid, bill.getMemCardId());
				CardRecord cardRecord = new CardRecord();
				cardRecord.setSid(sid);
				cardRecord.setBillId(billId);
				if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD)
						|| bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE)) {
					cardRecord.setPayCard(-cardFee);
					cardRecord.setPayPresent(-presentFee);
				} else {
					cardRecord.setPayCard(-cardFee);
					cardRecord.setPayPresent(-presentFee);
				}
				cardRecord.setCardNo(bill.getCardNo());
				cardRecord.setCardName(bill.getCardName());
				cardRecord.setCardTypeNo(bill.getCardTypeNo());
				cardRecord.setMemId(bill.getMemId());
				cardRecord.setMemCardId(bill.getMemCardId());
				cardRecord.setMemSid(bill.getMemSid());
				cardRecord.setType(bill.getBtype());
				cardRecord.setStype(2);
				cardRecord.setCardFee(card.getCardFee());
				cardRecord.setPresentFee(card.getPresentFee());
				result = cardRecordDao.create(tbuser, eid, cardRecord);
				if (result == null || !result.equals(1)) {
					throw new ExceptionWithCode(MsgCode.CASH_PAY_CREATE_FAILED);
				}
			}
		}

		if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE)) { // 消费
			// 项目套餐回滚套餐账户
			List<BillDetail> billDetails = billDetailDao.findByCtype(tbuser, eid, sid, billId,
					BeautyDoConsts.BILL_CTYPE_PACK);
			if (!billDetails.isEmpty()) {
				JSONArray list = CollectionUtil.toJSONArray(billDetails, true, "price", "itemId");
				memItemDao.rollback4Pay(eid, list);
			}
		} else if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PRODUCT)) { // 卖品
			// 商品库存单据
			result = stoBillDao.cancel4Sale(eid, billId);
			if (result == null || !result.equals(1)) {
				throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_PRODUCT_INVENTORY_FAILED);
			}
			result = stoBillDetailDao.cancel4Sale(eid, billId);
			if (result == null || result < 1) {
				throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_PRODUCT_INVENTORY_FAILED);
			}
			// 商品库存回滚
			List<com.qisen.mts.common.model.entity.sto.BillDetail> stoBillDetails = stoBillDetailDao.findByBillId(eid,
					billId);
			JSONArray list = CollectionUtil.toJSONArray(stoBillDetails, true, "money", "num", "itemId");
			stoItemDao.rollback4Sale(sid, list);
		} else if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PACK)) { // 购买项目套餐
			result = memItemDao.rollback4Bill(eid, billId);
			if (result == null || result < 1) {
				throw new ExceptionWithCode(MsgCode.BILL_CANCEL_UPDATE_MEMITEM_FAILED);
			}
		}
		return resp;
	}

	@Override
	public BaseResponse recalc(BeautyRequest<JSONObject> req) {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		BaseResponse resp = new BaseResponse();
		JSONObject body = req.getBody();
		String decimal = req.getSessionUser().getConfig("GLO_decimal");
		List<RuleEmpAchi> empAchiRules = sessionUser.getEmpAchiRules();
		body.put("status", new int[] { 0, 1 });
		List<Bill> bills = billDao.list(tbuser, eid, sid, body, null, null);
		JSONArray resultArr = new JSONArray();
		for (Bill bill : bills) {
			Long billId = bill.getId();
			Pay payment = payDao.findByDepCode(tbuser, eid, sid, billId, "ALL");
			JSONArray emps = empFeeDao.find4Recalc(tbuser, eid, sid, billId);
			for (int i = 0; i < emps.size(); i++) {
				JSONObject emp = emps.getJSONObject(i);
				EmpAchiCalcKit.calucateEmpGain(bill, emp, payment, empAchiRules, decimal);
				resultArr.add(emp);
			}
		}
		if (!resultArr.isEmpty())
			empFeeDao.update4Recalc(tbuser, eid, resultArr);
		return resp;
	}

}
