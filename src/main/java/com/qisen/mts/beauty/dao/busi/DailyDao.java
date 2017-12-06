package com.qisen.mts.beauty.dao.busi;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.busi.Daily;
import com.qisen.mts.common.model.request.PageRequest;

@Repository
public interface DailyDao {
	
	/**
	 * 新增开支
	 * @param daily
	 * @return
	 */
	public Integer create(Daily daily);// 新增

	/**
	 * 查询开支结果集
	 * @param daily
	 * @return
	 */
	public Integer count(PageRequest<JSONObject> req);
	
	/**
	 * 查询开支结果集
	 * @param req
	 * @return
	 */
	public List<Daily> list(PageRequest<JSONObject> req);
	
	/**
	 * 批量删除/恢复开支
	 * @param eid
	 * @param sid
	 * @param body.ids 待修改的记录ID
	 * @param body.status 待修改的记录status
	 */
	public int update(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);
	
	/**
	 * 查询合计收入、合计支出
	 * @param req
	 * @return
	 */
	public List<Daily> sumMoney(PageRequest<JSONObject> req);

}
