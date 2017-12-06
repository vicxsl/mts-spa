package com.qisen.mts.common.service.sys;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

public interface AccountService {
	
	/**
	 * 保存账号信息
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public BaseResponse save(BaseRequest<Account> req) throws Exception;
	
	/**
	 * 修改账号状态
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception;
	
	/**
	 * 查询账号
	 * 
	 * @param req
	 * @return
	 */
	public PageResponse<List<Account>> list(PageRequest<JSONObject> req);

	/**
	 * 保存权限
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public BaseResponse rights(BaseRequest<Account> req) throws Exception;

}
