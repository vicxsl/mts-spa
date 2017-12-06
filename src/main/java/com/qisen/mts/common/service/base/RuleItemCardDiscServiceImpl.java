package com.qisen.mts.common.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.RuleItemCardDiscDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.RuleItemCardDisc;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;

@Service
public class RuleItemCardDiscServiceImpl implements RuleItemCardDiscService {

	@Autowired
	private RuleItemCardDiscDao itemCardDiscDao;

	@Override
	public BaseResponse save(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		RuleItemCardDisc itemCardDisc = req.getBody().toJavaObject(RuleItemCardDisc.class);
		itemCardDisc.setEid(req.getEid());
		itemCardDisc.setSid(req.getSid());
		Integer result = null;
		if (itemCardDisc.getId() == null || itemCardDisc.getId().equals(0)) {
			JSONArray jsonArray = req.getBody().getJSONArray("itemNo");
			for (int i = 0; i < jsonArray.size(); i++) {
				itemCardDisc.setItemNo((String) jsonArray.get(i));
				Integer count = itemCardDiscDao.check(itemCardDisc.getEid(), itemCardDisc.getSid(),
						itemCardDisc.getItemNo(), itemCardDisc.getCardTypeNo());
				if (count == 0) {
					result = itemCardDiscDao.create(itemCardDisc);
				}
			}
		} else {
			result = itemCardDiscDao.update(itemCardDisc);
		}
		
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);

		return resp;
	}

	@Override
	public BaseResponse delete(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = itemCardDiscDao.delete(req.getEid(), req.getSid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

}
