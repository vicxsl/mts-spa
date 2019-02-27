package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;

public interface GoodsShopCarDao {
	/**
	 * 删除会员购物车内容
	 * @param spaAccount
	 * @return
	 */
	public int deleteByOpenid(SpaGoodsShopCar spa);
	
	/**
	 * 保存购物车列表
	 * @param list
	 * @return
	 */
	public int saveList(List<SpaGoodsShopCar> list);

	/**
	 * 查询购物车列表
	 * @param mobile
	 * @return
	 */
	public List<SpaGoodsShopCar> list(SpaGoodsShopCar spa);
	
}
