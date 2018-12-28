package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;

public interface SpaGoodsSupplierService {
	
	BaseResponse deleteByPrimaryKey(Integer id);

	BaseResponse insert(SpaGoodsSupplier record);
    
	BaseResponse updateByPrimaryKey(SpaGoodsSupplier record);
    
	CommObjResponse<List<SpaGoodsSupplier>> selectSuppliers(SpaGoodsSupplier spaGoodsSupplier);

}
