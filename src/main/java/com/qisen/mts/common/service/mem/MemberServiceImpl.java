package com.qisen.mts.common.service.mem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.busi.BillDao;
import com.qisen.mts.beauty.model.constant.BeautyDoConsts;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.dao.mem.CardRecordDao;
import com.qisen.mts.common.dao.mem.MemItemDao;
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.dao.sys.EnterpriseDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.CardRecord;
import com.qisen.mts.common.model.entity.mem.Item;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.util.DateUtil;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memMemberDao;

	@Autowired
	CardDao memCardDao;

	@Autowired
	MemItemDao memItemDao;

	@Autowired
	CardRecordDao cardRecordDao;

	@Autowired
	BillDao billDao;

	@Autowired
	EnterpriseDao enterpriseDao;

	@Override
	public BaseResponse create(Member memMember) {
		BaseResponse response = new BaseResponse();
		int rcount = this.memMemberDao.check(memMember);
		if (rcount == 0) {
			this.memMemberDao.create(memMember);
		} else {
			response.setResult(ResultCode.FAILED);
		}
		return response;
	}

	@Override
	public BaseResponse update(Member memMember) {
		BaseResponse response = new BaseResponse();
		this.memMemberDao.update(memMember);
		return response;
	}

	@Override
	public PageResponse<List<Member>> list(PageRequest<JSONObject> req) throws Exception {
		PageResponse<List<Member>> response = new PageResponse<List<Member>>();
		int count = this.memMemberDao.count(req);
		if (count > 0) {
			response.setPageNum(req.getPageNum());
			response.setPageSize(req.getPageSize());
			response.setCount(count);

			List<Member> memberList = this.memMemberDao.list(req);
			// 获取消费周期
			for (Member member : memberList) {
				if (member.getConsumeTimes() != null && member.getConsumeTimes() > 1) {
					String fistdate = DateUtil.dateToString(member.getFirstConsumeTime());
					String lastdate = DateUtil.dateToString(member.getLastConsumeTime());
					int day = 0;
					if (fistdate != null && lastdate != null) {
						day = DateUtil.daysBetween(fistdate, lastdate, true);
					}
					Double cycle = (double) day / (double) member.getConsumeTimes();
					member.setCycle(cycle);
				}
			}
			response.setBody(memberList);
		}
		return response;
	}

	@Override
	public BaseResponse updateStatus(BaseRequest<JSONObject> req) {
		BaseResponse response = new BaseResponse();
		this.memMemberDao.updatestatus(req);
		int status = req.getBody().getIntValue("status");
		int memId = req.getBody().getIntValue("id");
		int eid = req.getEid();
		List<Card> cardlist = this.memCardDao.findByMemId(eid, memId);
		String tbuser = req.getSessionAccount().getMetaString("tbuser");
		// ${tbuser}.e${eid}_mem_card_record
		if (status == 0) {// 删除
			for (Card card : cardlist) {
				CardRecord cardRecord = new CardRecord();
				cardRecord.setSid(req.getSid());
				cardRecord.setMemSid(card.getSid());
				cardRecord.setMemId(memId);
				cardRecord.setMemCardId(card.getId());
				cardRecord.setPayCard(-card.getCardFee());
				cardRecord.setPayPresent(-card.getPresentFee());
				cardRecord.setCardTypeNo(card.getCardTypeNo());
				cardRecord.setCardNo(card.getCardNo());
				// cardRecord.setCardName();
				cardRecord.setType(BeautyDoConsts.CARDRECORD_TYPE_DEL);
				cardRecord.setStype(1);
				this.memCardDao.updatestatus(eid, status, card.getId());
				this.cardRecordDao.create(tbuser, eid, cardRecord);
			}
		} else {// 恢复
			for (Card card : cardlist) {
				CardRecord cardRecord = new CardRecord();
				cardRecord.setSid(req.getSid());
				cardRecord.setMemSid(card.getSid());
				cardRecord.setMemId(memId);
				cardRecord.setMemCardId(card.getId());
				cardRecord.setCardFee(card.getCardFee());
				cardRecord.setPayCard(card.getCardFee());
				cardRecord.setPresentFee(card.getPresentFee());
				cardRecord.setPayPresent(card.getPresentFee());
				cardRecord.setCardTypeNo(card.getCardTypeNo());
				cardRecord.setCardNo(card.getCardNo());
				// cardRecord.setCardName();
				cardRecord.setType(BeautyDoConsts.CARDRECORD_TYPE_RECOVER);
				cardRecord.setStype(1);
				this.cardRecordDao.create(tbuser, eid, cardRecord);
				this.memCardDao.updatestatus(eid, status, card.getId());
			}
		}

		return response;
	}

	@Override
	public CommObjResponse<JSONObject> edit(BaseRequest<Member> req) {
		CommObjResponse<JSONObject> response = new CommObjResponse<JSONObject>();
		Member member = req.getBody();
		member.setSid(req.getSid());
		member.setEid(req.getEid());
		JSONObject body = new JSONObject();
		Integer id = 0;
		if (member.getId() == null || member.getId().equals(0)) {
			int rcount = 0;
			if (member.getMobile() != null && !member.getMobile().equals("")) {
				rcount = this.memMemberDao.check(member);
			}
			if (rcount == 0) {
				this.memMemberDao.create(member);
			} else {
				response.setCode(MsgCode.MEMBER_EDIT_MOBILE_REPEAT);
			}
		} else {
			this.memMemberDao.update(member);
		}
		id = member.getId();
		body.put("id", id);
		response.setBody(body);
		return response;
	}

	@Override
	public CommObjResponse<JSONObject> cardAndItem(BaseRequest<JSONObject> req) {
		CommObjResponse<JSONObject> response = new CommObjResponse<JSONObject>();
		JSONObject obj = new JSONObject();
		Member member = this.memMemberDao.find(req.getEid(), req.getBody().getIntValue("id"));
		List<Card> memCardList = this.memCardDao.findByMemId(req.getEid(), req.getBody().getIntValue("id"));// 根据会员id查询它的所有会员卡
		List<Item> memItemList = this.memItemDao.findByMemId(req.getEid(), req.getBody().getIntValue("id"));// 根据会员id
		obj.put("member", member);
		obj.put("cards", memCardList);
		obj.put("packItems", memItemList);
		response.setBody(obj);
		return response;
	}

	@Override
	public PageResponse<List<JSONObject>> cosumeDetail(PageRequest<JSONObject> req) {
		PageResponse<List<JSONObject>> response = new PageResponse<List<JSONObject>>();
		String tbuser = req.getSessionAccount().getMetaString("tbuser");
		Integer count = 0;
		JSONObject body = req.getBody();

		count = this.billDao.countListByMemId(tbuser, req.getEid(), body.getInteger("id"));
		if (count > 0) {
			response.setCount(count);
			response.setPageNum(req.getPageNum());
			response.setPageSize(req.getPageSize());
			JSONArray cosumeDetailList = this.billDao.findListByMemId(tbuser, req.getEid(), body.getInteger("id"),
					req.getStartIndex(), req.getEndIndex());

			Set<Long> tempBillIdSet = new HashSet<Long>();

			List<JSONObject> billDetailList = new ArrayList<JSONObject>();// 单据明细

			List<JSONObject> billList = new ArrayList<JSONObject>();// 单据
			for (int i = 0; i < cosumeDetailList.size(); i++) {
				JSONObject cosumeDetail = cosumeDetailList.getJSONObject(i);
				Long id = cosumeDetail.getLong("ID");
				if (!tempBillIdSet.contains(id)) {
					tempBillIdSet.add(id);
					JSONObject bill = new JSONObject();
					bill.put("id", id);
					bill.put("billNO", cosumeDetail.getString("NO"));
					bill.put("bType", cosumeDetail.getInteger("BTYPE"));
					bill.put("fee", cosumeDetail.getDouble("FEE"));
					bill.put("consumeTime", cosumeDetail.getString("CONSUMETIME"));
					bill.put("atSid", cosumeDetail.getLong("SID"));
					billList.add(bill);

					JSONObject detail = new JSONObject();
					detail.put("detailType", cosumeDetail.getInteger("DETAILTYPE"));
					detail.put("itemNo", cosumeDetail.getString("ITEMNO"));
					detail.put("itemName", cosumeDetail.getString("ITEMNAME"));
					detail.put("detailFee", cosumeDetail.getDouble("DETAILFEE"));
					detail.put("billId", cosumeDetail.getLong("BILLID"));
					detail.put("detailId", cosumeDetail.getLong("DETAILID"));
					billDetailList.add(detail);
				} else {
					JSONObject detail = new JSONObject();
					detail.put("detailType", cosumeDetail.getInteger("DETAILTYPE"));
					detail.put("itemNo", cosumeDetail.getString("ITEMNO"));
					detail.put("itemName", cosumeDetail.getString("ITEMNAME"));
					detail.put("detailFee", cosumeDetail.getDouble("DETAILFEE"));
					detail.put("billId", cosumeDetail.getLong("BILLID"));
					detail.put("detailId", cosumeDetail.getLong("DETAILID"));
					billDetailList.add(detail);
				}
			}
			for (JSONObject bill : billList) {
				List<JSONObject> arrylist = new ArrayList<JSONObject>();
				for (JSONObject detail : billDetailList) {
					if (bill.getIntValue("id") == detail.getIntValue("billId")) {
						arrylist.add(detail);
					}
				}
				bill.put("consumeDetail", arrylist);
			}
			response.setBody(billList);
		}
		response.setPageNum(req.getPageNum());
		response.setPageSize(req.getPageSize());
		response.setCount(count);
		return response;
	}

	@Override
	public PageResponse<JSONObject> memCardRecord(PageRequest<JSONObject> req) {
		PageResponse<JSONObject> response = new PageResponse<JSONObject>();
		Integer eid = req.getEid();
		Integer sid = req.getSid();
		Integer id = req.getBody().getInteger("id");
		Integer memsid = req.getBody().getInteger("memsid");
		Integer startIndex = req.getStartIndex();
		Integer endIndex = req.getEndIndex();
		Integer count = this.memCardDao.countCardRecord(eid, memsid, id);

		JSONObject obj = new JSONObject();
		JSONArray list = null;
		if (count > 0) {
			Card memCard = this.memCardDao.getCardById(eid, memsid, id);
			list = this.memCardDao.cardRecord(eid, memsid, sid, startIndex, endIndex);
			obj.put("initCardFee", memCard.getInitCardFee());
			obj.put("initPresentFee", memCard.getInitPresentFee());
			obj.put("list", list);
			response.setBody(obj);
		}
		response.setPageNum(req.getPageNum());
		response.setPageSize(req.getPageSize());
		response.setCount(count);
		return response;
	}

}
