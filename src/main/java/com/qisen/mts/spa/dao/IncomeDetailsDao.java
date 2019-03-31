package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.SpaIncomeDetails;

public interface IncomeDetailsDao {

	/**
	 * 检查会员是否已存在
	 * @param spaAccount
	 * @return
	 */
	public List<SpaIncomeDetails> list(@Param("body")SpaIncomeDetails req,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	//批量生成推广收益
	public void saveList(List<SpaIncomeDetails> list);
}
