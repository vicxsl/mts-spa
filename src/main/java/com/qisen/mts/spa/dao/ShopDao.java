package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.entity.SpaImg;

public interface ShopDao {
	/**
	 * 查询商户
	 * @param 
	 * @return
	 */
	public SpaShop queryByAppId(@Param("appId")String appId);
	
	/**
	 * 查询轮播图urls,按顺序返回
	 */
	public List<SpaImg> shopsImgList(SpaImg spaShopsImg);
}
