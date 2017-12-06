package com.qisen.mts.admin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Province;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface AdminAreaService {
	public CommObjResponse<List<Province>> provinceList(AdminRequest<JSONObject> req);

	public BaseResponse provinceCreate(AdminRequest<JSONObject> req);

	public BaseResponse provinceDelete(AdminRequest<JSONObject> req);

	public BaseResponse provinceUpdate(AdminRequest<JSONObject> req);
	
	public CommObjResponse<JSONObject> cityList(AdminRequest<JSONObject> req);

	public BaseResponse cityCreate(AdminRequest<JSONObject> req);

	public BaseResponse cityDelete(AdminRequest<JSONObject> req);

	public BaseResponse cityUpdate(AdminRequest<JSONObject> req);
}
