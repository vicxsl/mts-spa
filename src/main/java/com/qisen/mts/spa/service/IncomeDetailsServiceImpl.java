package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.dao.IncomeDetailsDao;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;

@Service
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	@Autowired
	private IncomeDetailsDao incomeDetailsDao;
	
	/**
	 * 查询获利列表
	 */
	@Override
	public PageResponse<List<SpaIncomeDetails>> list(PageRequest<SpaIncomeDetails> req) {
		PageResponse<List<SpaIncomeDetails>> resp = new PageResponse<List<SpaIncomeDetails>>();
		SpaIncomeDetails body = req.getBody();
		List<SpaIncomeDetails> list = incomeDetailsDao.list(body,req.getStartIndex(),req.getPageSize());
		resp.setBody(list);
		return resp;
	}


}
