package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.ItemPack;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;

public interface ItemPackDao {
	
	public Integer create(BaseRequest<JSONObject> req) throws Exception;// 新增

	public Integer update(BaseRequest<JSONObject> req) throws Exception;// 更新字段
	
	public Integer count(PageRequest<JSONObject> req);// 查询结果的个数
	
	public Integer check(@Param("eid") Integer eid,@Param("no") String no);// 查询结果的个数

	/**
	 * 查询项目套餐
	 * 
	 * @param eid
	 * @param sid
	 * @param body.keyword
	 * @param body.status
	 * @param body.shopblock
	 * @param startIndex 分页开始记录
	 * @param endIndex 分页结束记录 为空时不分页
	 * @return
	 */
	public List<ItemPack> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body, @Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

	/**
	 * 
	 * 批量删除/恢复/停用套餐
	 * @param req.body.ids[1,2]
	 * @param req.sid//根据此值判断是否是总部
	 * @param req.body.status //0:删除,1:启用,2：停用
	 */
	public void updatestatus(BaseRequest<JSONObject> req);

}
