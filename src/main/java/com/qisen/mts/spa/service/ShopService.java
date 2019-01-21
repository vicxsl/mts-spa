package com.qisen.mts.spa.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface ShopService {

	/**
	 * 查询spa商户
	 * @param req
	 * @return
	 */
	public CommObjResponse<SpaShop> queryByAppId(SpaRequest<JSONObject> req);
	
}
