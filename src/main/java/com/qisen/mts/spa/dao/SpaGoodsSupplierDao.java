package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;

public interface SpaGoodsSupplierDao {
	
	int deleteByPrimaryKey(Integer id);

    int insert(SpaGoodsSupplier record);
    
    int updateByPrimaryKeySelective(SpaGoodsSupplier record);
    
    List<SpaGoodsSupplier> selectSuppliers(SpaGoodsSupplier record);


}
