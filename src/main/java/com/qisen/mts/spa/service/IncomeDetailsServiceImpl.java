package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.IncomeDetailsDao;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class IncomeDetailsServiceImpl implements IncomeDetailsService {

	@Autowired
	private IncomeDetailsDao incomeDetailsDao;
	
	/**
	 * 查询获利列表
	 */
	@Override
	public CommObjResponse<List<SpaIncomeDetails>> list(SpaRequest<SpaIncomeDetails> req) {
		CommObjResponse<List<SpaIncomeDetails>> resp = new CommObjResponse<List<SpaIncomeDetails>>();
		SpaIncomeDetails body = req.getBody();
		List<SpaIncomeDetails> list = incomeDetailsDao.list(body);
		resp.setBody(list);
		return resp;
	}


}
