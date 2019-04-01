package com.qisen.mts.spa.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaMyInfoGains;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface MemberService {

	/**
	 * 商城登录
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<MetaData> login(SpaRequest<SpaMember> req) throws Exception;
	
	/**
	 * 更新用户手机号码
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<String> addMobile(SpaRequest<JSONObject> req) throws Exception;
	
	public CommObjResponse<List<SpaMember>> memberIncomeDetailsList(SpaRequest<SpaMember> req);
	

	public CommObjResponse<SpaMyInfoGains> myInfoGains(SpaRequest<SpaMyInfoGains> req);
}
