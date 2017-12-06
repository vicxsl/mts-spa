package com.qisen.mts.admin.service;

import java.util.List;

import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;


public interface GoodsService
{
	/**
	 * 查询产品列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<Goods>> List(AdminRequest<Goods> req);

	/**
	 * 新增产品
	 * @param req
	 * @return
	 */
	public BaseResponse create(AdminRequest<Goods> req);
	
	/**
	 * 更新产品
	 * @param req
	 * @return
	 */
	public BaseResponse update(AdminRequest<Goods> req);
	
	/**
	 * 删除产品
	 * @param req
	 * @return
	 */
	public BaseResponse delete(AdminRequest<Goods> req);

}
