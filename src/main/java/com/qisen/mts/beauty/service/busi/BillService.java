package com.qisen.mts.beauty.service.busi;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

public interface BillService {
	
	/**
	 * 查询单据
	 * 
	 * @param req
	 * @return
	 */
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req);

	/**
	 * 修改单据状态
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse status(BeautyRequest<JSONObject> req) throws Exception;

	/**
	 * 重新计算
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse recalc(BeautyRequest<JSONObject> req);

}
