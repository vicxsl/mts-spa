package com.qisen.mts.beauty.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.ItemDao;
import com.qisen.mts.common.dao.base.ItemPriceDao;
import com.qisen.mts.common.dao.base.RuleItemCardDiscDao;
import com.qisen.mts.common.dao.base.RulePresentCardTypeDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.CommonDoConsts;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.common.model.entity.base.ItemPrice;
import com.qisen.mts.common.model.entity.base.RuleItemCardDisc;
import com.qisen.mts.common.model.entity.base.RulePresentCardType;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.BulkChangeTypeNoRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.gateway.ExceptionWithCode;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemPriceDao itemPriceDao;
	@Autowired
	private RuleItemCardDiscDao itemCardDiscDao;
	@Autowired
	private RulePresentCardTypeDao presentCardTypeDao;

	@Override
	public BaseResponse save(BeautyRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		JSONObject body = req.getBody();
		Integer type = body.getIntValue("type");
		Item baseItem = JSON.parseObject(body.toJSONString(), Item.class);
		baseItem.setEid(req.getEid());
		Integer result = null;
		if (body.containsKey("id") && body.getInteger("id") != null && body.getInteger("id") > 0) { // update
			result = itemDao.update(baseItem);
		} else { // create
			// check no
			int count = itemDao.check(req.getEid(), baseItem.getType(), baseItem.getNo(), 0);
			if (count == 0)
				result = itemDao.create(baseItem);
			else
				resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		if (null == result || result == 0)
			resp.setResult(ResultCode.FAILED);
		else if (type != null && type == 3 && body.containsKey("presentRules")) { // 会员卡需要单独处理充值赠送规则
			// 删除所有规则
			presentCardTypeDao.delete(req.getEid(), baseItem.getNo());
			JSONArray presentRules = body.getJSONArray("presentRules");
			if (presentRules != null && !presentRules.isEmpty()) {
				// 新增新规则
				Integer count = presentCardTypeDao.create(req.getEid(), req.getSid(), presentRules);
				if (count != presentRules.size())
					throw new ExceptionWithCode(MsgCode.PRESENT_RULE_UPDATE_FAILD);
				/*List<RulePresentCardType> newRules = new ArrayList<>();
				List<RulePresentCardType> updateRules = new ArrayList<>();
				Integer[] deleteIds = null;
				for (int i = 0; i < presentRules.size(); i++) {
					JSONObject presentRuleJ = presentRules.getJSONObject(i);
					RulePresentCardType presentRule = presentRuleJ.toJavaObject(RulePresentCardType.class);
					presentRule.setEid(req.getEid());
					if (presentRule.getId() == null || presentRule.getId() == 0)
						newRules.add(presentRule);
					else if (presentRuleJ.containsKey("status") && presentRuleJ.getIntValue("status") == 0)
						deleteIds = ArrayUtils.add(deleteIds, presentRule.getId());
					else
						updateRules.add(presentRule);
				}
				if (!newRules.isEmpty()) {
					Integer count = presentCardTypeDao.create(newRules);
					if (count != newRules.size())
						throw new ExceptionWithCode(MsgCode.PRESENT_RULE_UPDATE_FAILD);
				}
				if (!updateRules.isEmpty())
					presentCardTypeDao.update(updateRules);

				if (deleteIds != null) {
					Integer count = presentCardTypeDao.delete(req.getEid(), deleteIds);
					if (count != deleteIds.length)
						throw new ExceptionWithCode(MsgCode.PRESENT_RULE_UPDATE_FAILD);
				}*/
			}
		}
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		return resp;
	}

	@Override
	public PageResponse<List<JSONObject>> list(PageRequest<JSONObject> req) {
		JSONObject body = req.getBody();
		Integer count = itemDao.count(req.getEid(), req.getSid(), body);
		PageResponse<List<JSONObject>> resp = new PageResponse<>(req.getPageNum(), req.getPageSize(), count);
		List<Item> itemList = itemDao.list(req.getEid(), req.getSid(), body, req.getStartIndex(), req.getEndIndex());

		Integer type = body.getInteger("type");
		if (type != null) {
			List<JSONObject> bodyList = new ArrayList<>();
			// 商品定价
			List<ItemPrice> itemPriceList = itemPriceDao.list(req.getSid(), type);
			switch (type) {
			case CommonDoConsts.ITEM_TYPE_SERVICE: // 项目 项目卡级折扣会员价
				// 商品卡级折扣会员价
				List<RuleItemCardDisc> itemCardDiscList = itemCardDiscDao.list(req.getEid(), 0, null);
				for (Item item : itemList) {
					JSONObject itemJ = item.toJSON();
					List<RuleItemCardDisc> enterpriseCardDiscRules = new ArrayList<>();
					List<RuleItemCardDisc> shopCardDiscRules = new ArrayList<>();
					for (RuleItemCardDisc itemCardDisc : itemCardDiscList) {
						if (item.getNo().equals(itemCardDisc.getItemNo())) {
							if (itemCardDisc.getSid() == null || itemCardDisc.getSid().equals(0))
								enterpriseCardDiscRules.add(itemCardDisc);
							else if (itemCardDisc.getSid() != null && itemCardDisc.getSid().equals(req.getSid()) )
								shopCardDiscRules.add(itemCardDisc);
						}
					}
					for (ItemPrice itemPrice : itemPriceList) {
						if (item.getNo().equals(itemPrice.getItemNo())) {
							itemJ.put("itemPrice", itemPrice);
							break;
						}
					}
					if (!itemJ.containsKey("itemPrice"))
						itemJ.put("itemPrice", null);
					itemJ.put("enterpriseCardDiscRules", enterpriseCardDiscRules);
					itemJ.put("shopCardDiscRules", shopCardDiscRules);
					bodyList.add(itemJ);
				}
				break;
			case CommonDoConsts.ITEM_TYPE_PRODUCT: // 卖品
				for (Item item : itemList) {
					JSONObject itemJ = item.toJSON();
					for (ItemPrice itemPrice : itemPriceList) {
						if (item.getNo().equals(itemPrice.getItemNo())) {
							itemJ.put("itemPrice", itemPrice);
							break;
						}
					}
					if (!itemJ.containsKey("itemPrice"))
						itemJ.put("itemPrice", null);
					bodyList.add(itemJ);
				}
				break;
			case CommonDoConsts.ITEM_TYPE_CARDTYPE: // 会员卡 充值赠送规则
				List<RulePresentCardType> presentCardTypeList = presentCardTypeDao.list(req.getEid(), null, null);
				for (Item item : itemList) {
					JSONObject itemJ = item.toJSON();
					List<RulePresentCardType> enterprisePresentRules = new ArrayList<>();
					//List<RulePresentCardType> shopPresentRules = new ArrayList<>();
					for (RulePresentCardType presentCardType : presentCardTypeList) {
						if (item.getNo().equals(presentCardType.getCardTypeNo())) {
							enterprisePresentRules.add(presentCardType);
							/*if (presentCardType.getSid() == null || presentCardType.getSid() == 0)
								enterprisePresentRules.add(presentCardType);
							else if (presentCardType.getSid() != null && presentCardType.getSid() == req.getSid())
								shopPresentRules.add(presentCardType);*/
						}
					}
					itemJ.put("presentRules", enterprisePresentRules);
					//itemJ.put("shopPresentRules", shopPresentRules);
					bodyList.add(itemJ);
				}
				break;
			}
			resp.setBody(bodyList);
		}

		return resp;
	}

	@Override
	public BaseResponse status(BeautyRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = itemDao.status(req.getEid(), req.getSid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qisen.mts.beauty.service.common.ItemService#bulkChangeTypeNo(com.qisen.mts.common.model.request.BeautyRequest)
	 */
	@Override
	public BaseResponse bulkChangeTypeNo(BeautyRequest<BulkChangeTypeNoRequest> req) {
		BaseResponse resp = new BaseResponse();
		Integer result = this.itemDao.bulkChangeTypeNo(req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qisen.mts.beauty.service.common.ItemService#bulkChangeStatus(com.qisen.mts.common.model.request.BeautyRequest)
	 */
	@Override
	public BaseResponse bulkChangeStatus(BeautyRequest<BulkChangeStatusRequest> req) {
		BaseResponse resp = new BaseResponse();
		Integer result = this.itemDao.bulkChangeStatus(req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

}
