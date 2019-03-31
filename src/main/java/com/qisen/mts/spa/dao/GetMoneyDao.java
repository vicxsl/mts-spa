package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.GetMoney;

public interface GetMoneyDao {

	/**
	 * 查询提现记录
	 * @param req
	 * @return
	 */
	public List<GetMoney> list(@Param("body")GetMoney req,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	/**
	 * 新增提现记录  
	 * @param req
	 */
	public void create(GetMoney req);
}
