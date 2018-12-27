package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsType;

public interface GoodsTypeDao {

	/**
	 * 检查spa账号是否已存在
	 * @param spaAccount
	 * @return
	 */
	public int check(SpaGoodsType spaGoodsType);
	
	/**
	 * 删除spa账号
	 * @param spaAccount
	 * @return
	 */
	public int delete(SpaGoodsType spaGoodsType);

	/**
	 * 新增spa账号 
	 * @param spaAccount
	 */
	public void create(SpaGoodsType spaGoodsType);
	
	/**
	 * 编辑spa账号 
	 * @param spaAccount
	 */
	public void update(SpaGoodsType spaGoodsType);
	
	/**
	 * 查询spa账号列表
	 * @param mobile
	 * @return
	 */
	public List<SpaGoodsType> list(SpaGoodsType spaGoodsType);
}
