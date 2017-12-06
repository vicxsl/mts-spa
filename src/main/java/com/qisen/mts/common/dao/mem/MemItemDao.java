package com.qisen.mts.common.dao.mem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.mem.Item;

public interface MemItemDao {

	/**
	 * 创建套餐
	 * 
	 * @param eid
	 * @param sid
	 * @param item
	 * @return
	 */
	public Integer create(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("item") Item item);
	
	/**
	 * 批量创建套餐
	 * 
	 * @param eid
	 * @param sid
	 * @param billId
	 * @param memId
	 * @param memCardId
	 * @param list
	 * @return
	 */
	public Integer createBatch(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("billId") Long billId, @Param("memId") Integer memId, @Param("memCardId") Integer memCardId, @Param("list") JSONArray list);

	/**
	 * 套餐消费
	 * 
	 * @param eid
	 * @param list
	 * @return
	 */
	public Integer update4Pay(@Param("eid") Integer eid, @Param("list") JSONArray list);

	/**
	 * 套餐消费销单回滚
	 * 
	 * @param eid
	 * @param list
	 * @return
	 */
	public Integer rollback4Pay(@Param("eid") Integer eid, @Param("list") JSONArray list);

	/**
	 * 套餐购买销单回滚
	 * 
	 * @param eid
	 * @param billId
	 * @return
	 */
	public Integer rollback4Bill(@Param("eid") Integer eid, @Param("billId") Long billId);

	/**
	 * 根据会员id查询所有的疗程
	 * 
	 * @param sid
	 * @param memid
	 * @return
	 */
	public List<Item> findByMemId(@Param("eid") Integer eid, @Param("memId") int memid);
	
	
	/**
	 * 检测该项目疗程是否存在
	 * @param itemNo 项目编号
	 * @param memCardId 卡id
	 * @param eid
	 * @param sid
	 * @return
	 */
	public List<Item> checkTreat(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("itemNo") String itemNo, @Param("memCardId") Integer memCardId);
	
	/**
	 * 插入疗程
	 * @param eid
	 * @param sid
	 * @param obj)
	 * 
	 */
	public void importTreat(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("obj") JSONObject obj);
	
	/**
	 * 修改疗程
	 * @param eid
	 * @param sid
	 * @param obj
	 */
	public void importUpdateTreat(@Param("eid") Integer eid,@Param("sid") Integer sid,@Param("obj") JSONObject obj);

}
