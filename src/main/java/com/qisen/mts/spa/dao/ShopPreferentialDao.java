package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaShopPreferential;

public interface ShopPreferentialDao {
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	public List<SpaShopPreferential> list(SpaShopPreferential spa);
	
	/**
	 * 编辑
	 * @param 
	 * @return
	 */
	public void saveOrUpdate(SpaShopPreferential spa);
	
	/**
	 * 删除
	 */
	public void deleteList(List<SpaShopPreferential> list);
}
