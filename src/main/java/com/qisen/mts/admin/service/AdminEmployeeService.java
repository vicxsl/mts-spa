package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface AdminEmployeeService {
	public CommObjResponse<JSONObject> list(AdminRequest<JSONObject> req);

	public BaseResponse create(AdminRequest<JSONObject> req);

	public BaseResponse delete(AdminRequest<JSONObject> req);

	public BaseResponse update(AdminRequest<JSONObject> req);

}
