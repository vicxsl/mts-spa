package com.qisen.mts.beauty.service.common;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.BulkChangeTypeNoRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
public interface ItemService {

	/**
	 * 查询项目、商品、会员卡
	 * 
	 * @param req
	 * @return
	 */
	public PageResponse<List<JSONObject>> list(PageRequest<JSONObject> req);
	
	/**
	 * 保存项目、商品、会员卡
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse save(BeautyRequest<JSONObject> req) throws Exception;

	/**
	 * 修改项目、商品、会员卡状态
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse status(BeautyRequest<JSONObject> req) throws Exception;

	/**
	 * @param req
	 * @return
	 */
	public BaseResponse bulkChangeTypeNo(BeautyRequest<BulkChangeTypeNoRequest> req) throws Exception;

	/**
	 * @param req
	 * @return
	 */
	public BaseResponse bulkChangeStatus(BeautyRequest<BulkChangeStatusRequest> req) throws Exception;

}
