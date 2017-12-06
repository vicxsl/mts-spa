package com.qisen.mts.common.dao.sto;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sto.Item;
import com.qisen.mts.common.model.entity.sto.StoItemWithPdInfo;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoItemListRequest;
import com.qisen.mts.common.model.response.StoItemListResponse;

public interface StoItemDao {

	public void create(Item stoItem);
	
	public void delete(Integer id);
	
	/**
	 * 商品销售更新库存
	 * 
	 * @param sid
	 * @param item
	 * @return
	 */
	public Integer update4Sale(@Param("sid") Integer sid, @Param("item") JSONObject item);
	
	/**
	 * 商品销售撤单回滚
	 * 
	 * @param sid
	 * @param stoBillDetails
	 * @return
	 */
	public Integer rollback4Sale(@Param("sid") Integer sid, @Param("list") JSONArray stoBillDetails);
	
	public Integer update(Item stoItem);
	
	public List<Item> list(Item stoItem);
	
	public Item find(Integer id);
	
	public Integer check(Item stoItem);

	/**
	 * 获取库存的一些汇总信息
	 * @param request
	 * @return
	 */
	public StoItemListResponse listCounts(PageRequest<StoItemListRequest> request);

	/**
	 * 根据条件获取库存列表
	 * @param request
	 * @return
	 */
	public List<StoItemWithPdInfo> stoItemlist(PageRequest<StoItemListRequest> request);
	
	/**
	 * 根据条件查询库存记录
	 * @param stoItem
	 * @return
	 */
	public Item findByProduct(Item stoItem);
}
