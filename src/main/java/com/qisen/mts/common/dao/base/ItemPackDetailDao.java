package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.ItemPackDetail;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;

public interface ItemPackDetailDao {
	
	/**
	 * 查询套餐明细
	 * 
	 * @param eid
	 * @param packId
	 * @return
	 */
	public List<ItemPackDetail> findByPackId(@Param("eid") Integer eid, @Param("packId") Integer packId);
	
	/**
	 * 查询套餐明细
	 * 
	 * @param eid
	 * @param packIds
	 * @return
	 */
	public List<ItemPackDetail> findByPackIds(@Param("eid") Integer eid, @Param("packIds") JSONArray packIds);
	
	public void create(BaseRequest<JSONObject> req);// 新增

	/**
	 * 根据套餐id删除套餐明细
	 * 
	 * @param packid(套餐id)
	 * @param eid
	 */
	public void deleteByPackid(@Param("packid") Integer packid, @Param("eid") Integer eid);

	public void update(JSONArray arry);// 更新字段

	public List<ItemPackDetail> list(PageRequest<JSONObject> req);// 查询结果集

}
