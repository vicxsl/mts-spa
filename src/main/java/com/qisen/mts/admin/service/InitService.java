package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.response.BaseResponse;

public interface InitService {

	public BaseResponse createEnterprise(AdminRequest<JSONObject> req) throws Exception;
	
	public BaseResponse createShop(AdminRequest<JSONObject> req) throws Exception;

	public BaseResponse eidtShop(AdminRequest<Shop> req);
}
