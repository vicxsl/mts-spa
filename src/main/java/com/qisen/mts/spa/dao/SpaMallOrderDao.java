package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.SpaMallOrder;

public interface SpaMallOrderDao {
	
	int check(SpaMallOrder record);

	int delete(SpaMallOrder record);

	int create(SpaMallOrder record);

	void update(SpaMallOrder record);
	//发货
	int sendGoods(SpaMallOrder record);

	//收货
	int confirmGoods(SpaMallOrder record);
	
	List<SpaMallOrder> list(@Param("body")SpaMallOrder record,@Param("startIndex") Integer startIndex,@Param("pageSize")Integer pageSize);
	
	SpaMallOrder getOrder(@Param("id") String id,@Param("appid") String appid,@Param("totalMoney") String totalMoney);
	//更新支付状态
	void updatePayStatus(@Param("id") String id,@Param("appid") String appid,@Param("totalMoney") String totalMoney);

}
