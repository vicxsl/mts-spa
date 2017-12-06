package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.ItemPrice;

public interface ItemPriceDao {

	/**
	 * 查询基础分类
	 * 
	 * @param sid
	 * @param type 0全部1项目2商品
	 * @return
	 */
	public List<ItemPrice> list(@Param("sid") Integer sid, @Param("type") Integer type);
	
	/**
	 * 新增价格
	 * 
	 * @param itemPrice
	 * @return 
	 */
	public Integer create(ItemPrice itemPrice);

	/**
	 * 删除价格
	 * 
	 * @param sid
	 * @param body.ids 待删除的记录ID
	 */
	public Integer delete(@Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 更新价格
	 * 
	 * @param itemPrice id为0时根据项目编号和类型更新记录
	 */
	public Integer update(ItemPrice itemPrice);

	/**
	 * 检查是否设置价格
	 * 
	 * @param sid
	 * @param type
	 * @param itemNo
	 * @return
	 */
	public Integer check(@Param("sid") Integer sid, @Param("type") Integer type, @Param("itemNo") String itemNo);
}
