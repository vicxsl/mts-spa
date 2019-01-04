package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;

public interface SpaGoodsSupplierDao {
	
	int delete(Integer id);

    int save(SpaGoodsSupplier record);
    
    int edit(SpaGoodsSupplier record);
    
    List<SpaGoodsSupplier> list(SpaGoodsSupplier record);


}
