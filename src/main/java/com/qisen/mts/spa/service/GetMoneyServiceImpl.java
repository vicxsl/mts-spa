package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.dao.GetMoneyDao;
import com.qisen.mts.spa.model.entity.GetMoney;

@Service
public class GetMoneyServiceImpl implements GetMoneyService {

	@Autowired
	private GetMoneyDao getMoneyDao;
	
	/**
	 * 查询提现记录列表
	 */
	@Override
	public PageResponse<List<GetMoney>> list(PageRequest<GetMoney> req) {
		PageResponse<List<GetMoney>> resp = new PageResponse<List<GetMoney>>();
		GetMoney body = req.getBody();
		List<GetMoney> list = getMoneyDao.list(body,req.getStartIndex(),req.getPageSize());
		resp.setBody(list);
		return resp;
	}
	
	/**
	 * 新增提现记录
	 */
	@Override
	public PageResponse<List<GetMoney>> create(PageRequest<GetMoney> req) {
		PageResponse<List<GetMoney>> resp = new PageResponse<List<GetMoney>>();
		GetMoney body = req.getBody();
		getMoneyDao.create(body);
		List<GetMoney> list = getMoneyDao.list(body,req.getStartIndex(),req.getPageSize());
		resp.setBody(list);
		return resp;
	}

}
