package com.qisen.mts.beauty.service.busi;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.busi.Daily;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

public interface DailyService {
	
	public BaseResponse create(BeautyRequest<Daily> req);
	
	public BaseResponse update(BeautyRequest<JSONObject> req);
	
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req);

	
	
}
