package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.SessionAdmin;
import com.qisen.mts.admin.model.entity.Admin;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface AdminService {

	/**
	 * 查询后台账号列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<JSONObject> list(AdminRequest<JSONObject> req);

	/**
	 * 新增后台账号
	 * @param req
	 * @return
	 */
	public BaseResponse save(AdminRequest<Admin> req);
	
	/**
	 * 删除后台账号
	 * @param req
	 * @return
	 */
	public BaseResponse delete(AdminRequest<Admin> req) throws Exception;

	/**
	 * 编辑后台账号
	 * @param req
	 * @return
	 */
	public BaseResponse edit(AdminRequest<Admin> req);
	
	/**
	 * 登录
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<SessionAdmin> login(AdminRequest<Admin> req) throws Exception;
}
