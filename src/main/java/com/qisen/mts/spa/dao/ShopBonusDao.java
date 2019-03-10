package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.ShopBonus;

public interface ShopBonusDao {
	/**
	 * 批量更新新零售提成方案
	 * @param 
	 * @return
	 */
	public void updateList(List<ShopBonus> list);
	
}
