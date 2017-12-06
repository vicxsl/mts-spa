package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;

public interface AdminEnterpriseDao {

	/**
	 * 查询项目、商品、会员卡总记录数
	 * 
	 * @param body.keyword
	 * @return
	 */
	public Integer count(@Param("body") JSONObject body);
	
	/**
	 * 查询项目、商品、会员卡
	 * 
	 * @param body.keyword
	 * @param startIndex 分页开始记录
	 * @param endIndex 分页结束记录 为空时不分页
	 * @return
	 */
	public List<Enterprise> list(@Param("body") JSONObject body, @Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

	public List<Shop> listShop(@Param("enterprises")List<Enterprise> enterprises,@Param("body") JSONObject body);

	public Shop shop(@Param("body")JSONObject body);
	
	/**
	 * 修改企业信息
	 * @param body
	 * @return
	 */
	public int set(Enterprise enterprise);
}
