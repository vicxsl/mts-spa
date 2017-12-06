package com.qisen.mts.common.service.base;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
public interface EmpService {
	
	public BaseResponse edit(BaseRequest<JSONObject> req) throws Exception;
	
	public BaseResponse updateStatus(BaseRequest<JSONObject> req) throws Exception;

	public PageResponse<JSONObject> list(PageRequest<JSONObject> req);

	public CommObjResponse<Emp> find(int id);
	
}
