package com.qisen.mts.common.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.sys.ShopSettingDao;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
@Service
public class ShopSettingServiceImpl implements ShopSettingService {

	@Autowired
	private ShopSettingDao shopSettingDao;

	@Override
	public BaseResponse save(BaseRequest<JSONArray> req) {
		BaseResponse resp = new BaseResponse();
		shopSettingDao.delete(req.getEid(),req.getSid(),req.getBody().getJSONObject(0).get("mid"));
		shopSettingDao.create(req.getEid(),req.getSid(),req.getBody());
		resp.notify4Metadata(req.getEid(), req.getSid());
		return resp;
	}

	@Override
	public CommObjResponse<List<ShopSetting>> list(BaseRequest<JSONObject> req) {
		CommObjResponse<List<ShopSetting>> response = new CommObjResponse<List<ShopSetting>>();
		List<ShopSetting> settingList = shopSettingDao.list(req.getEid(),req.getSid(),req.getBody());
		response.setBody(settingList);
		return response;
	}
}
