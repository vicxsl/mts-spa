package com.qisen.mts.beauty.service.busi;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.busi.BillDao;
import com.qisen.mts.beauty.dao.busi.BillDetailDao;
import com.qisen.mts.beauty.dao.busi.DailyDao;
import com.qisen.mts.beauty.dao.busi.EmpFeeDao;
import com.qisen.mts.beauty.dao.busi.PayDao;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.beauty.model.entity.busi.Bill;
import com.qisen.mts.beauty.model.entity.busi.BillDetail;
import com.qisen.mts.beauty.model.entity.busi.EmpFee;
import com.qisen.mts.beauty.model.entity.busi.Pay;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.dao.mem.CardRecordDao;
import com.qisen.mts.common.dao.mem.MemItemDao;
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.dao.sto.StoBillDao;
import com.qisen.mts.common.dao.sto.StoBillDetailDao;
import com.qisen.mts.common.dao.sto.StoItemDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.CommonDoConsts;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.CardRecord;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.gateway.ExceptionWithCode;

@Service
public class CashServiceImpl implements CashService {

	@Autowired
	private BillDao billDao;
	@Autowired
	private BillDetailDao billDetailDao;
	@Autowired
	private EmpFeeDao empFeeDao;
	@Autowired
	private PayDao payDao;
	@SuppressWarnings("unused")
	@Autowired
	private DailyDao dailyDao;
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
	@Autowired
	private EmpDao empDao;

	@Override
	public CommObjResponse<Long> doCash(BeautyRequest<JSONObject> req) throws Exception {
		CommObjResponse<Long> resp = new CommObjResponse<>();
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		JSONObject billBody = req.getBody();

		// 计算员工业绩、提成
		// EmpAchiCalcKit.calucateBillFee(billBody, null);

		Bill bill = JSON.parseObject(billBody.toJSONString(), Bill.class);
		bill.setSid(sid);
		bill.setOptId(sessionUser.getId());
		bill.setOptName(sessionUser.getName());

		JSONArray details = billBody.getJSONArray("details");
		JSONObject payJ = billBody.getJSONObject("pay");
		// 基础校验
		if (details == null || details.isEmpty() || payJ == null || payJ.isEmpty()) {
			resp.setResult(ResultCode.INVALID_PARAMETERS);
			return resp;
		}

		JSONArray consumePackItems = new JSONArray(); // 消费的套餐项目
		JSONArray buyPackItems = new JSONArray(); // 购买的套餐项目
		JSONArray consumeProducts = new JSONArray(); // 消费的商品
		Double sumConsumeProductMoney = 0.0; // 总卖品消费金额
		Integer sumConsumeProductNum = 0; // 总卖品消费数量
		JSONObject payCardProp = new JSONObject();
		// Double sumPayCard = 0.0; // 总支付卡金
		// Double sumPayPresent = 0.0; // 总支付赠送金
		payCardProp.put("sumPayCard", 0.0);
		payCardProp.put("sumPayPresent", 0.0);

		if (bill.getMemId() != null && bill.getMemId() > 0) {
			Member member = memberDao.find(eid, bill.getMemId());
			bill.setMemName(member.getName());
			bill.setMemMobile(member.getMobile());
			bill.setMemSid(member.getSid());
			if (StringUtils.isBlank(bill.getSex()))
				bill.setSex(member.getSex());
		}

		// 参数判断，设置默认值
		if (StringUtils.isBlank(bill.getSex()))
			bill.setSex(BeautyDoConsts.SEX_FEMALE);
		if (StringUtils.isBlank(bill.getNo()))
			bill.setNo(DateFormatUtils.format(new Date(), "yyMMddHHmmss"));
		// if (StringUtils.isBlank(bill.getBday()))
		bill.setBday(new Date());
		Date bday = bill.getBday();
		payJ.put("bday", bday);

		if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD)) // 开卡，先生成卡账户
			this.createCard(bill, details.getJSONObject(0), eid, sid, payCardProp);
		else if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE))
			this.buildChargeData(bill, details.getJSONObject(0), payCardProp); // 组装充值数据

		// 查询消费卡账户
		Card card = null;
		if (bill.getMemCardId() != null && bill.getMemCardId() > 0)
			card = cardDao.find(eid, bill.getMemCardId());

		// 如果是充值或者开卡操作，卡账户不能为空
		if (card == null && (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD) || bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE)))
			throw new ExceptionWithCode(MsgCode.CASH_INVALID_CARD);

		// 项目服务水单检查单号重复
		if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE)) {
			JSONObject params = new JSONObject();
			params.put("billNo", bill.getNo());
			params.put("bday", DateFormatUtils.format(bday, "yyyy-MM-dd"));
			Integer count = billDao.check(tbuser, eid, sid, params);
			if (count != null && count > 0)
				throw new ExceptionWithCode(MsgCode.COMMON_NO_EXIST);
		}

		// 生成单据
		Integer result = billDao.create(tbuser, eid, bill);
		if (result == null || !result.equals(1)){
			throw new ExceptionWithCode(MsgCode.CASH_BILL_CREATE_FAILED);
		}
		if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE) ) {
			
		}
		Long billId = bill.getId();
		String lastEmp = null;
		// 单据明细
		for (int i = 0; i < details.size(); i++) {
			JSONObject detailJ = details.getJSONObject(i);
			BillDetail detail = detailJ.toJavaObject(BillDetail.class);
			detail.setBday(bday);
			result = billDetailDao.create(tbuser, eid, sid, billId, detail);
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.CASH_BILLDETAIL_CREATE_FAILED);

			// 消费的套餐项目
			if (detail.getCtype().equals(BeautyDoConsts.BILL_CTYPE_PACK) && detail.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE)) {
				JSONObject consumePackItem = new JSONObject();
				consumePackItem.put("price", detail.getPrice());
				consumePackItem.put("itemId", detail.getItemId());
				consumePackItems.add(consumePackItem);
			} else if (detail.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PACK)) {
				// 构建项目套餐账户数据
				JSONArray packItems = detailJ.getJSONArray("items");
				if (packItems != null && !packItems.isEmpty()) {
					for (int j = 0; j < packItems.size(); j++) {
						JSONObject packItemJ = packItems.getJSONObject(j);
						JSONObject buyPackItem = new JSONObject();
						buyPackItem.put("itemNo", packItemJ.getString("itemNo"));
						buyPackItem.put("sumTimes", packItemJ.getInteger("sumTimes"));
						buyPackItem.put("leaveTimes", packItemJ.getInteger("leaveTimes"));
						buyPackItem.put("sumMoney", packItemJ.getDouble("sumMoney"));
						buyPackItem.put("onceMoney", packItemJ.getDouble("onceMoney"));
						buyPackItem.put("leaveMoney", packItemJ.getString("leaveMoney"));
						buyPackItem.put("validDate", packItemJ.getDate("validDate") == null ? null : packItemJ.getDate("validDate").getTime());
						buyPackItem.put("inputDate", bday.getTime());
						buyPackItems.add(buyPackItem);
					}
				}
			}
			// 消费的商品
			if (detail.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PRODUCT)) {
				// 计算总产品金额、总产品数量
				sumConsumeProductMoney += detail.getNum() * detail.getPrice();
				sumConsumeProductNum += detail.getNum();
				// 构建消费商品数据
				JSONObject consumeProduct = new JSONObject();
				consumeProduct.put("money", detail.getNum() * detail.getPrice());
				consumeProduct.put("num", detail.getNum());
				consumeProduct.put("itemId", detail.getItemId());
				consumeProduct.put("itemNo", detail.getItemNo());
				consumeProduct.put("bday", bday);
				consumeProduct.put("btype", CommonDoConsts.STO_BILL_BTYPE_OUT);
				consumeProduct.put("stype", CommonDoConsts.STO_BILL_STYPE_SALE_OUT);
				consumeProducts.add(consumeProduct);
			}

			// 服务员工
			JSONArray emps = detailJ.getJSONArray("emps");
			if (emps != null && !emps.isEmpty()) {
				for (int j = 0; j < emps.size(); j++) {
					JSONObject empJ = emps.getJSONObject(j);
					empJ.put("bday", bday);
					if (!empJ.containsKey("pointType"))
						empJ.put("pointType", null);
					if (!empJ.containsKey("gainType"))
						empJ.put("gainType", detail.getBtype());
					if (!empJ.containsKey("ctype"))
						empJ.put("ctype", detail.getCtype());
					if (!empJ.containsKey("itemType"))
						empJ.put("itemType", null);
					if (StringUtils.isBlank(empJ.getString("totalFee")))
						empJ.put("totalFee", 0);
					if (StringUtils.isBlank(empJ.getString("totalGain")))
						empJ.put("totalGain", 0);

					if (i == 0 && j == 0) {
						Emp emp = empDao.find(empJ.getInteger("empId"));
						if(emp != null)
							lastEmp = emp.getName();
					}
				}
				result = empFeeDao.createBatch(tbuser, eid, sid, billId, detail.getId(), emps);
				if (result == null || result < 1)
					throw new ExceptionWithCode(MsgCode.CASH_EMP_CREATE_FAILED);
			}

		}

		// 支付流水
		this.createPay(bill, payJ, card, tbuser, eid, sid, payCardProp, sessionUser.getBasicType("payTypes"), lastEmp);

		// 套餐账户更新
		if (!consumePackItems.isEmpty())
			memItemDao.update4Pay(eid, consumePackItems);
		// 生成库存单据
		if (!consumeProducts.isEmpty())
			this.createStoBill(bill, consumeProducts, eid, sid, sumConsumeProductMoney, sumConsumeProductNum);

		// 生成套餐账户
		if (!buyPackItems.isEmpty()) {
			result = memItemDao.createBatch(eid, sid, billId, bill.getMemId(), bill.getMemCardId(), buyPackItems);
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.CASH_MEMITEM_CREATE_FAILED);
		}

		resp.setBody(billId);
		return resp;
	}

	/**
	 * 生成卡账户
	 * 
	 * @param bill
	 * @param newCardDetailJ
	 * @param eid
	 * @param sid
	 * @param payCardProp
	 * @throws Exception
	 */
	private void createCard(Bill bill, JSONObject newCardDetailJ, Integer eid, Integer sid, JSONObject payCardProp) throws Exception {

		// 基础校验
		if (newCardDetailJ == null || newCardDetailJ.isEmpty())
			throw new ExceptionWithCode(MsgCode.CASH_INVALID_CARD);

		JSONObject cardTypeJ = newCardDetailJ.getJSONObject("cardType");
		JSONObject propJ = cardTypeJ.getJSONObject("prop");

		if (cardTypeJ == null || cardTypeJ.isEmpty() || propJ == null || propJ.isEmpty())
			throw new ExceptionWithCode(MsgCode.CASH_INVALID_CARD);

		// 新开储值卡
		if ("2".equals(cardTypeJ.getString("typeNo"))) {
			// 获取总充值金额、总赠送金额
			if (NumberUtils.isNumber(newCardDetailJ.getString("rawPrice")))
				payCardProp.put("sumPayCard", newCardDetailJ.getDouble("rawPrice"));
			if (NumberUtils.isNumber(newCardDetailJ.getString("price")))
				payCardProp.put("sumPayPresent", newCardDetailJ.getDouble("price"));
			// sumPayCard = newCardDetailJ.getDouble("rawPrice") == null ? 0.0 : newCardDetailJ.getDouble("rawPrice");
			// sumPayPresent = newCardDetailJ.getDouble("price") == null ? 0.0 : newCardDetailJ.getDouble("price");
		}

		// 初始化卡账户数据
		Card card = new Card();
		card.setEid(eid);
		card.setSid(sid);
		card.setMemId(bill.getMemId());
		card.setCrossFlag(1);
		card.setCardTypeNo(cardTypeJ.getString("no"));
		card.setDiscount(propJ.getDouble("serviceDisc"));
		card.setCardFee(0.0);
		card.setPresentFee(0.0);
		card.setInitCardFee(0.0);
		card.setInitPresentFee(0.0);
		card.setSumCardFee(0.0);
		card.setSumPresentFee(0.0);
		card.setConsumeFee(0.0);

		// 卡号生成逻辑
		if (propJ.getInteger("cardNoFlag") != null && propJ.getInteger("cardNoFlag").equals(1))
			card.setCardNo(DateFormatUtils.format(new Date(), "yyMMddHHmms"));
		else
			card.setCardNo(newCardDetailJ.getString("itemNo"));

		if (StringUtils.isBlank(card.getCardNo()))
			card.setCardNo(DateFormatUtils.format(new Date(), "yyMMddHHmms"));

		// 检查卡类型卡号重复
		JSONObject params = new JSONObject();
		params.put("cardNo", card.getCardNo());
		params.put("cardTypeNo", card.getCardTypeNo());
		Integer count = cardDao.checkCardNo(eid, sid, params);
		if (count != null && count > 0)
			throw new ExceptionWithCode(MsgCode.CASH_CARDNO_EXIST);

		// 计算到期日期
		if (NumberUtils.isNumber(propJ.getString("validMod"))) {
			String validDate = null;
			switch (propJ.getInteger("validMod")) {
			case 1:
				validDate = propJ.getString("valid");
				break;
			case 2:
				Date validD = DateUtils.addDays(bill.getBday(), propJ.getInteger("valid"));
				validDate = DateFormatUtils.format(validD, "yyyy-MM-dd HH:mm:ss");
				break;
			}
			card.setValidDate(validDate);
		}

		// 生成卡账户
		Integer result = cardDao.create(card);
		if (result == null || !result.equals(1))
			throw new ExceptionWithCode(MsgCode.CASH_CARD_CREATE_FAILED);

		// 构建单据数据
		bill.setMemCardId(card.getId());
		bill.setCardName(cardTypeJ.getString("name"));
		bill.setCardNo(card.getCardNo());
		bill.setCardTypeNo(card.getCardTypeNo());
		// 开卡明细
		newCardDetailJ.put("itemId", card.getId());
		newCardDetailJ.put("itemNo", card.getCardNo());
		newCardDetailJ.put("itemName", cardTypeJ.getString("name"));
	}

	/**
	 * 充值类卡信息放在了明细里面，需要提取组装参数
	 * 
	 * @param bill
	 * @param chargeCardDetailJ
	 * @param payCardProp
	 * @throws Exception
	 */
	private void buildChargeData(Bill bill, JSONObject chargeCardDetailJ, JSONObject payCardProp) throws Exception {

		// 基础校验
		if (chargeCardDetailJ == null || chargeCardDetailJ.isEmpty())
			throw new ExceptionWithCode(MsgCode.CASH_INVALID_CARD);

		// 获取总充值金额、总赠送金额
		if (NumberUtils.isNumber(chargeCardDetailJ.getString("rawPrice")))
			payCardProp.put("sumPayCard", chargeCardDetailJ.getDouble("rawPrice"));
		if (NumberUtils.isNumber(chargeCardDetailJ.getString("price")))
			payCardProp.put("sumPayPresent", chargeCardDetailJ.getDouble("price"));
		// sumPayCard = chargeCardDetailJ.getDouble("rawPrice") == null ? 0.0 : chargeCardDetailJ.getDouble("rawPrice");
		// sumPayPresent = chargeCardDetailJ.getDouble("price") == null ? 0.0 : chargeCardDetailJ.getDouble("price");

		// 组装单据数据
		bill.setMemCardId(chargeCardDetailJ.getInteger("itemId"));
		bill.setCardName(chargeCardDetailJ.getString("itemName"));
		bill.setCardNo(chargeCardDetailJ.getString("itemNo"));
		bill.setCardTypeNo(chargeCardDetailJ.getString("cardTypeNo"));
	}

	/**
	 * 生成支付流水，跨店同时生成跨店流水
	 * 
	 * @param bill
	 * @param payJ
	 * @param card
	 * @param tbuser
	 * @param eid
	 * @param sid
	 * @param payCardProp
	 * @param payTypes
	 * @param lastEmp 
	 * @throws Exception
	 */
	private void createPay(Bill bill, JSONObject payJ, Card card, String tbuser, Integer eid, Integer sid, JSONObject payCardProp, JSONArray payTypes, String lastEmp) throws Exception {

		// 基础校验
		JSONObject payDetailJ = payJ.getJSONObject("pay");
		if (payDetailJ == null)
			throw new ExceptionWithCode(MsgCode.CASH_PAY_CREATE_FAILED);

		// 初始化支付流水
		Pay pay = payJ.toJavaObject(Pay.class);
		pay.setBday(bill.getBday());
		// 生成支付流水
		Integer result = payDao.create(tbuser, eid, sid, bill.getId(), pay);
		if (result == null || !result.equals(1))
			throw new ExceptionWithCode(MsgCode.CASH_PAY_CREATE_FAILED);

		// 更新消费金额，消费金额只包含卡金和现金类支付方式
		if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_SERVICE) || bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_PRODUCT)) {
			double totalPay = pay.getTotalPay();
			for (int i = 0; i < payTypes.size(); i++) {
				JSONObject payType = payTypes.getJSONObject(i);
				if (payType.getInteger("type").equals(7) && !"CARD".equals(payType.getString("no")) && !"PACK".equals(payType.getString("no"))) {
					Double typeFee = payDetailJ.getDoubleValue(payType.getString("no"));
					totalPay -= typeFee == null ? 0 : typeFee;
				}
			}

			// 会员数据更新
			if (bill.getMemId() != null && bill.getMemId() > 0) {
				memberDao.update4FirstConsume(eid, bill.getMemId(), bill.getBday());
				result = memberDao.updateConsumeInfo(eid, bill.getMemId(), bill.getBday(), totalPay, 0, lastEmp);
				if (result == null || !result.equals(1))
					throw new ExceptionWithCode(MsgCode.CASH_MEMBER_UPDATE_FAILED);
			}

			// 卡消费金额更新
			if (card != null) {
				result = cardDao.updateConsumeInfo(eid, card.getId(), totalPay, 0);
				if (result == null || result < 1)
					throw new ExceptionWithCode(MsgCode.CASH_CARD_UPDATE_FAILED);
			}
		}

		// 非开充卡时获取用卡支付金额
		if (!bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD) && !bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE)) {
			if (NumberUtils.isNumber(payDetailJ.getString("CARD")))
				payCardProp.put("sumPayCard", payDetailJ.getDouble("CARD"));
			if (NumberUtils.isNumber(payDetailJ.getString("PRESENT")))
				payCardProp.put("sumPayPresent", payDetailJ.getDouble("PRESENT"));
		}

		Double sumPayCard = payCardProp.getDouble("sumPayCard");
		Double sumPayPresent = payCardProp.getDouble("sumPayPresent");

		// 判断是否需要生成卡金变动流水
		if (sumPayCard > 0 || sumPayPresent > 0) {
			// 基础验证
			if (card == null)
				throw new ExceptionWithCode(MsgCode.CASH_INVALID_CARD);

			// 初始化卡金变动流水数据
			CardRecord cardRecord = new CardRecord();
			cardRecord.setSid(sid);
			cardRecord.setBillId(bill.getId());
			cardRecord.setCardNo(bill.getCardNo());
			cardRecord.setCardName(bill.getCardName());
			cardRecord.setCardTypeNo(bill.getCardTypeNo());
			cardRecord.setMemId(bill.getMemId());
			cardRecord.setMemCardId(bill.getMemCardId());
			cardRecord.setMemSid(bill.getMemSid());
			cardRecord.setType(bill.getBtype());
			cardRecord.setStype(1);
			cardRecord.setCardFee(card.getCardFee());
			cardRecord.setPresentFee(card.getPresentFee());

			// 卡账户账户更新
			if (bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_NEWCARD) || bill.getBtype().equals(BeautyDoConsts.BILL_BTYPE_CHARGE)) { // 开充卡
				// 开充卡卡金变动流水金额为正
				cardRecord.setPayCard(sumPayCard);
				cardRecord.setPayPresent(sumPayPresent);
				result = cardDao.update4Charge(eid, bill.getMemCardId(), sumPayCard, sumPayPresent);
			} else { // 消费
				// 消费卡金变动流水金额为为负
				cardRecord.setPayCard(-sumPayCard);
				cardRecord.setPayPresent(-sumPayPresent);
				result = cardDao.update4Pay(eid, bill.getMemCardId(), sumPayCard, sumPayPresent);
			}

			if (result == null || !result.equals(1))
				throw new ExceptionWithCode(MsgCode.CASH_CARD_UPDATE_FAILED);

			// 跨店流水
			if (bill.getMemSid() != null && bill.getMemSid() > 0 && !bill.getSid().equals(bill.getMemSid())) {
				pay.setDepCode("CROSS");
				payDetailJ = new JSONObject();
				payDetailJ.put("CARD", sumPayCard);
				payDetailJ.put("PRESENT", sumPayPresent);
				pay.setPay(payDetailJ);
				result = payDao.create(tbuser, eid, sid, bill.getId(), pay);
				if (result == null || result < 1)
					throw new ExceptionWithCode(MsgCode.CASH_PAY_CREATE_FAILED);
			}

			// 生成卡金变动流水
			result = cardRecordDao.create(tbuser, eid, cardRecord);
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.CASH_PAY_CREATE_FAILED);

		}

	}

	/**
	 * 创建卖品销售库存单据
	 * 
	 * @param bill
	 * @param consumeProducts
	 * @param eid
	 * @param sid
	 * @param sumConsumeProductMoney
	 * @param sumConsumeProductNum
	 * @throws Exception
	 */
	private void createStoBill(Bill bill, JSONArray consumeProducts, Integer eid, Integer sid, Double sumConsumeProductMoney, Integer sumConsumeProductNum) throws Exception {
		// 初始化库存单据数据
		com.qisen.mts.common.model.entity.sto.Bill stoBill = new com.qisen.mts.common.model.entity.sto.Bill();
		stoBill.setBillId(bill.getId());
		stoBill.setEid(eid);
		stoBill.setSid(sid);
		stoBill.setOutSid(sid);
		stoBill.setNo(bill.getNo());
		stoBill.setbType(CommonDoConsts.STO_BILL_BTYPE_OUT);
		stoBill.setsType(CommonDoConsts.STO_BILL_STYPE_SALE_OUT);
		stoBill.setDay(bill.getBday());
		stoBill.setMoney(sumConsumeProductMoney);
		stoBill.setNum(sumConsumeProductNum);
		stoBill.setOptId(bill.getOptId());
		stoBill.setOptName(bill.getOptName());
		stoBill.setHandler(bill.getOptName());
		// 生成库存单据
		Integer result = stoBillDao.create(stoBill);
		if (result == null || !result.equals(1))
			throw new ExceptionWithCode(MsgCode.CASH_STOBILL_CREATE_FAILED);

		// 生成库存单据明细
		result = stoBillDetailDao.createBatch4Sale(eid, sid, stoBill.getId(), consumeProducts);
		if (result == null || result < 1)
			throw new ExceptionWithCode(MsgCode.CASH_STOBILLDETAIL_CREATE_FAILED);

		// 扣除商品库存
		for (int i = 0; i < consumeProducts.size(); i++) {
			JSONObject item = consumeProducts.getJSONObject(i);
			result = stoItemDao.update4Sale(sid, item);
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.NOT_ENOUGH_INVENTORY);
		}

	}

	@Override
	public void calcEmpFeeAndGain(Pay pay, EmpFee emp, Item item, JSONArray empAchiRules) {

	}

	@Override
	public CommObjResponse<Boolean> checkCardNo(BeautyRequest<JSONObject> req) throws Exception {
		CommObjResponse<Boolean> resp = new CommObjResponse<>();
		Integer count = cardDao.checkCardNo(req.getEid(), req.getSid(), req.getBody());
		if (count > 0)
			resp.setBody(true);
		else
			resp.setBody(false);
		return resp;
	}

	@Override
	public CommObjResponse<Boolean> checkBillNo(BeautyRequest<JSONObject> req) throws Exception {
		SessionUser sessionUser = req.getSessionUser();
		String tbuser = sessionUser.getTbuser();
		CommObjResponse<Boolean> resp = new CommObjResponse<>();
		Integer count = billDao.check(tbuser, req.getEid(), req.getSid(), req.getBody());
		if (count > 0)
			resp.setBody(true);
		else
			resp.setBody(false);
		return resp;
	}
}
