package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.common.model.entity.base.ItemWithStoNum;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.BulkChangeTypeNoRequest;
import com.qisen.mts.common.model.request.ItemListRequest;
import com.qisen.mts.common.model.request.PageRequest;

@Repository
public interface ItemDao {

	/**
	 * 查询项目、商品、会员卡总记录数
	 * 
	 * @param eid
	 * @param sid
	 * @param body.keyword
	 * @param body.typeNo
	 * @param body.type
	 *            0全部1项目2商品3会员卡
	 * @param body.status
	 *            数组格式 0删除1正常3停用
	 * @return
	 */
	public Integer count(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 查询项目、商品、会员卡
	 * 
	 * @param eid
	 * @param sid
	 * @param body.keyword
	 * @param body.typeNo
	 * @param body.type
	 *            0全部1项目2商品3会员卡
	 * @param body.status
	 *            数组格式 0删除1正常3停用
	 * @param startIndex
	 *            分页开始记录
	 * @param endIndex
	 *            分页结束记录 为空时不分页
	 * @return
	 */
	public List<Item> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body,
			@Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

	/**
	 * 创建项目、商品、会员卡
	 * 
	 * @param baseItem
	 * @return
	 */
	public Integer create(Item baseItem);

	/**
	 * 修改项目、商品、会员卡
	 * 
	 * @param baseItem
	 * @return
	 */
	public Integer update(Item baseItem);

	/**
	 * 修改状态
	 * 
	 * @param eid
	 * @param sid
	 * @param body.status
	 *            0删除1正常2停用
	 * @param body.ids
	 *            ID数组
	 * @return
	 */
	public Integer status(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 检查编号重复
	 * 
	 * @param eid
	 * @param type
	 *            1项目2商品3会员卡
	 * @param no
	 * @param id
	 * @return
	 */
	public Integer check(@Param("eid") Integer eid, @Param("type") Integer type, @Param("no") String no,
			@Param("id") Integer id);

	/**
	 * 查询商品列表
	 * 
	 * @param req
	 * @return
	 */
	public List<ItemWithStoNum> productList(PageRequest<ItemListRequest> req);

	/**
	 * 查询商品总数
	 * 
	 * @param req
	 * @return
	 */
	public Integer productListCount(PageRequest<ItemListRequest> req);

	/**
	 * 批量更改商品类型
	 * 
	 * @param body
	 */
	public Integer bulkChangeTypeNo(BulkChangeTypeNoRequest body);

	/**
	 * 批量更改商品状态
	 * 
	 * @param body
	 */
	public Integer bulkChangeStatus(BulkChangeStatusRequest body);

	/**
	 * 导入疗程检测项目编号是否存在
	 * 
	 * @param eid
	 * @param itemNo
	 * @return
	 */
	public Integer checkItemNo(@Param("eid") Integer eid, @Param("itemNo") String itemNo);
	
	

}
