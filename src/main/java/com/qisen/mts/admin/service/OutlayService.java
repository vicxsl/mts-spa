package com.qisen.mts.admin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Outlay;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;


public interface OutlayService
{
	/**
	 * 查询产品列表
	 * @param req
	 * @return
	 */
	public PageResponse<List<Outlay>> List(PageRequest<JSONObject> req);

	/**
	 * 新增产品
	 * @param req
	 * @return
	 */
	public BaseResponse create(AdminRequest<Outlay> req);
	
	/**
	 * 更新产品
	 * @param req
	 * @return
	 */
	public BaseResponse update(AdminRequest<Outlay> req);
	
	/**
	 * 删除产品
	 * @param req
	 * @return
	 */
	public BaseResponse delete(AdminRequest<Outlay> req);

}
