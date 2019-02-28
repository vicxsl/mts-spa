package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaMallOrder;

public interface SpaMallOrderDao {
	
	int check(SpaMallOrder record);

	int delete(SpaMallOrder record);

	void create(SpaMallOrder record);

	void update(SpaMallOrder record);

	List<SpaMallOrder> list(SpaMallOrder record);

}
