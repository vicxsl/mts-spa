package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;

public interface IncomeDetailsService {

	/**
	 * 查询获利详情表
	 * @param req
	 * @return
	 */
	public PageResponse<List<SpaIncomeDetails>> list(PageRequest<SpaIncomeDetails> req);
}
