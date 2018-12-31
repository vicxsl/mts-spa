package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsSubType;

public interface GoodsSubTypeDao {

	/**
	 * 检查spa商品子类型是否已存在
	 * @param spaGoodsSubType
	 * @return
	 */
	public int check(SpaGoodsSubType spaGoodsSubType);
	
	/**
	 * 删除spa商品子类型
	 * @param spaGoodsSubType
	 * @return
	 */
	public int delete(SpaGoodsSubType spaGoodsSubType);

	/**
	 * 新增spa商品子类型
	 * @param spaGoodsSubType
	 */
	public void create(SpaGoodsSubType spaGoodsSubType);
	
	/**
	 * 编辑spa商品子类型
	 * @param spaGoodsSubType
	 */
	public void update(SpaGoodsSubType spaGoodsSubType);
	
	/**
	 * 查询spa商品子类型列表
	 * @param spaGoodsSubType
	 * @return
	 */
	public List<SpaGoodsSubType> list(SpaGoodsSubType spaGoodsType);
}
