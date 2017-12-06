package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.common.model.entity.base.RulePresentCardType;

public interface RulePresentCardTypeDao {
	
	/**
	 * 批量新增充值赠送规则
	 * 
	 * @param eid
	 * @param sid
	 * @param list
	 * @return
	 */
	public Integer create(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("list") JSONArray list);

	/**
	 * 批量修改充值赠送规则
	 * 
	 * @param list
	 */
	public Integer update(List<RulePresentCardType> list);
	
	/**
	 * 批量删除
	 * 
	 * @param eid
	 * @param cardTypeNo
	 * @return
	 */
	public Integer delete(@Param("eid") Integer eid, @Param("cardTypeNo") String cardTypeNo);
	
	/**
	 * 查詢充值赠送规则
	 * 
	 * @param eid
	 * @param sid
	 * @param cardTypeNo 为空查询全部
	 * @return
	 */
	public List<RulePresentCardType> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("cardTypeNo") String cardTypeNo);

}
