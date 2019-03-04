package com.qisen.mts.spa.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.entity.SpaShopsImg;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface ShopService {

	/**
	 * 查询spa商户
	 * @param req
	 * @return
	 */
	public CommObjResponse<SpaShop> queryByAppId(SpaRequest<JSONObject> req);
	
	
	/**
	 * 查询商户轮播图
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShopsImg>> shopsImgList(SpaRequest<SpaShopsImg> req);

	
}
