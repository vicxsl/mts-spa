package com.qisen.mts.spa.service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.SessionSpa;
import com.qisen.mts.spa.model.entity.SpaAccount;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface SpaAccountService {

	/**
	 * 新增spa账号
	 * @param req
	 * @return
	 */
	public BaseResponse save(SpaRequest<SpaAccount> req);
	

	/**
	 * 登录
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<SessionSpa> login(SpaRequest<SpaAccount> req) throws Exception;
}
