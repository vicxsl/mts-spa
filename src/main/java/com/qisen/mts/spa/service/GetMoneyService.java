package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.GetMoney;

public interface GetMoneyService {

	/**
	 * 查询提现记录
	 * @param req
	 * @return
	 */
	public PageResponse<List<GetMoney>> list(PageRequest<GetMoney> req);
	
	
	/**
	 * 新增提现记录
	 * @param req
	 * @return
	 */
	public BaseResponse create(PageRequest<GetMoney> req);
}
