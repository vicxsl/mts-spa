package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaImg;

public interface GoodsDao {

	/**
	 * 删除spa账号
	 * @param spaAccount
	 * @return
	 */
	public int delete(List<SpaGoods> list);

	
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
	public List<SpaGoods> list(@Param("body")SpaGoods spaGoods,@Param("startIndex") Integer startIndex,@Param("pageSize")Integer pageSize);
	/**
	 * 新增或编辑商品
	 * @param spaGoods
	 */
	public void saveOrUpdate(SpaGoods spaGoods);
	
	public List<SpaImg> goodsImgList(SpaImg spa);
	
	public SpaGoods details(SpaGoods spa);//查询商品信息
	
	public void updateGoodsNum(List<SpaGoods> goodList);

	public SpaGoods getGoodsByPara(SpaGoods spaGoods);
}
