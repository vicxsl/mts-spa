package com.qisen.mts.spa.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.entity.SpaImg;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface ShopService {

	/**
	 * 查询spa商户
	 * @param req
	 * @return
	 */
	public CommObjResponse<SpaShop> queryByAppId(SpaRequest<JSONObject> req);
	
	/**
	 * 查询spa商户列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShop>> list(SpaRequest<SpaShop> req);
	
	/**
	 * 编辑商户列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShop>> edit(SpaRequest<SpaShop> req);
	
	/**
	 * 查询商户轮播图
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaImg>> shopsImgList(SpaRequest<SpaImg> req);

	
}
