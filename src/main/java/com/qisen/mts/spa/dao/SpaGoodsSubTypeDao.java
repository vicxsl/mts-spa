package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsSubType;

public interface SpaGoodsSubTypeDao {
	
	int deleteByPrimaryKey(Integer id);

    int insert(SpaGoodsSubType record);
    
    int updateByPrimaryKey(SpaGoodsSubType record);
    
    SpaGoodsSubType selectByPrimaryKey(Integer id);

	List<SpaGoodsSubType> selectGoodsSubTys(SpaGoodsSubType body);

}
