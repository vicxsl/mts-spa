package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;

public interface EnterpriseService {

	public PageResponse<JSONObject> list(PageRequest<JSONObject> req);

	public CommObjResponse<Shop> shop(PageRequest<JSONObject> req);
	
	/**
	 * 修改企业信息
	 * @param req
	 * @return
	 */
	public BaseResponse set(AdminRequest<Enterprise> req);

	public BaseResponse shopValidSet(AdminRequest<JSONObject> req);
}
