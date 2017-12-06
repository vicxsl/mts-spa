package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.ConsumeLog;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

public interface ConsumeLogService {
	
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req);
	
	public BaseResponse update(AdminRequest<ConsumeLog> req);
	
	public BaseResponse updateAuditFlag(AdminRequest<JSONObject> req);

	public BaseResponse create(AdminRequest<ConsumeLog> req);
	
}
