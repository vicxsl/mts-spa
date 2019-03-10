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
	public SpaShop queryByAppId(@Param("appid")String appid);
	/**
	 * 查询商户
	 * @param 
	 * @return
	 */
	public List<SpaShop> list(SpaShop spa);
	
	/**
	 * 编辑商户
	 * @param 
	 * @return
	 */
	public void edit(SpaShop spa);
	
	/**
	 * 查询轮播图urls,按顺序返回
	 */
	public List<SpaImg> shopsImgList(SpaImg spaShopsImg);
}
