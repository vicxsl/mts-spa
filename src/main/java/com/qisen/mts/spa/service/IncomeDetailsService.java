package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface IncomeDetailsService {

	/**
	 * 查询获利详情表
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaIncomeDetails>> list(SpaRequest<SpaIncomeDetails> req);
}
