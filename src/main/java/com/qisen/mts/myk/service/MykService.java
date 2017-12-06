package com.qisen.mts.myk.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.myk.model.SessionMember;
import com.qisen.mts.myk.model.request.MykRequest;

public interface MykService {

	/**
	 * 登录
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public CommObjResponse<SessionMember> login(MykRequest<JSONObject> req) throws Exception;
	
	/**
	 * metadata
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<JSONObject> metadata(MykRequest<JSONObject> req) throws Exception;

	/**
	 * 构建sessionMember
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public SessionMember buildSessionMember(BaseEntity entity) throws Exception;
}
