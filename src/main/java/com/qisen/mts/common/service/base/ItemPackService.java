package com.qisen.mts.common.service.base;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
public interface ItemPackService {

	public PageResponse<List<JSONObject>> list(PageRequest<JSONObject> req);
	
	public BaseResponse edit(BaseRequest<JSONObject> req) throws Exception;
	
	public BaseResponse editDetail(BaseRequest<JSONObject> req);
	
	public BaseResponse updateStatus(BaseRequest<JSONObject> req);


}
