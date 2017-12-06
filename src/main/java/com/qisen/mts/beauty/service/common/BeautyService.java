package com.qisen.mts.beauty.service.common;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface BeautyService {

	/**
	 * 登录
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public CommObjResponse<JSONObject> login(BeautyRequest<Account> req) throws Exception;
	
	/**
	 * 登出
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse logout(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * metadata
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<JSONObject> metadata(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 构建SessionUser
	 * 
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public SessionUser buildSessionUser(BaseEntity entity) throws Exception;
}
