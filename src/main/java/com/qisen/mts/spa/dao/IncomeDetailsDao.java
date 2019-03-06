package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaIncomeDetails;

public interface IncomeDetailsDao {

	/**
	 * 检查会员是否已存在
	 * @param spaAccount
	 * @return
	 */
	public List<SpaIncomeDetails> list(SpaIncomeDetails req);
}