package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;

public interface GoodsShopCarDao {
	/**
	 * 删除spa账号
	 * @param spaAccount
	 * @return
	 */
	public int delete(SpaGoodsShopCar spa);

	/**
	 * 新增spa账号 
	 * @param spaAccount
	 */
	public void add(SpaGoodsShopCar spa);
	
	/**
	 * 编辑spa账号 
	 * @param spaAccount
	 */
	public void update(SpaGoodsShopCar spa);
	

	/**
	 * 查询spa账号列表
	 * @param mobile
	 * @return
	 */
	public List<SpaGoodsShopCar> list(SpaGoodsShopCar spa);
}
