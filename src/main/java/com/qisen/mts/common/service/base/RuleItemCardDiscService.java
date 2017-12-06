package com.qisen.mts.common.service.base;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;

public interface RuleItemCardDiscService {
	
	public BaseResponse save(BaseRequest<JSONObject> req) throws Exception;

	public BaseResponse delete(BaseRequest<JSONObject> req) throws Exception;

}
