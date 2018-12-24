package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoods;

public interface GoodsDao {

	/**
	 * 检查spa账号是否已存在
	 * @param spaAccount
	 * @return
	 */
	public int check(SpaGoods spaGoods);
	
	/**
	 * 删除spa账号
	 * @param spaAccount
	 * @return
	 */
	public int delete(SpaGoods spaGoods);

	/**
	 * 新增spa账号 
	 * @param spaAccount
	 */
	public void create(SpaGoods spaGoods);
	
	/**
	 * 编辑spa账号 
	 * @param spaAccount
	 */
	public void update(SpaGoods spaGoods);
	

	/**
	 * 查询spa账号列表
	 * @param mobile
	 * @return
	 */
	public List<SpaGoods> list(SpaGoods spaGoods);
}
