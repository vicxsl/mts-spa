package com.qisen.mts.admin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.OutlayClass;
import com.qisen.mts.admin.model.entity.ProductsClass;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface AdminClassService {

	public CommObjResponse<List<ProductsClass>> prodClassList(AdminRequest<JSONObject> req);

	public BaseResponse prodClassCreate(AdminRequest<JSONObject> req);

	public BaseResponse prodClassDelete(AdminRequest<JSONObject> req);

	public BaseResponse prodClassUpdate(AdminRequest<JSONObject> req);

	public CommObjResponse<List<OutlayClass>> outlayClassList(AdminRequest<JSONObject> req);

	public BaseResponse outlayClassCreate(AdminRequest<JSONObject> req);

	public BaseResponse outlayClassDelete(AdminRequest<JSONObject> req);

	public BaseResponse outlayClassUpdate(AdminRequest<JSONObject> req);

	public CommObjResponse<JSONObject> organList(AdminRequest<JSONObject> req);

	public BaseResponse organCreate(AdminRequest<JSONObject> req);

	public BaseResponse organDelete(AdminRequest<JSONObject> req);

	public BaseResponse organUpdate(AdminRequest<JSONObject> req);

}
