package com.qisen.mts.beauty.service.rule;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface RuleEmpAchiService {
	
	public BaseResponse save(BeautyRequest<RuleEmpAchi> req);

	public BaseResponse delete(BeautyRequest<JSONObject> req);

	public BaseResponse status(BeautyRequest<JSONObject> req);
	
	public BaseResponse update4Sort(BeautyRequest<JSONObject> req);

	public CommObjResponse<List<RuleEmpAchi>> list(BeautyRequest<JSONObject> req);
	
	public BaseResponse copyRule(BeautyRequest<JSONObject> req);

}
