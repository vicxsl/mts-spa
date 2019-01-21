package com.qisen.mts.spa.dao;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.SpaShop;

public interface ShopDao {

	/**
	 * 查询商户
	 * @param 
	 * @return
	 */
	public SpaShop queryByAppId(@Param("appId")String appId);
	
}
