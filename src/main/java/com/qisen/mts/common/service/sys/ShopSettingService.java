package com.qisen.mts.common.service.sys;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
public interface ShopSettingService {
	/**
	 * 创建、修改门店配置
	 */
	public BaseResponse save(BaseRequest<JSONArray> req);
	
	/**
	 * 查询门店配置
	 */
	public CommObjResponse<List<ShopSetting>> list(BaseRequest<JSONObject> req);
	

	
}
