package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;

public interface SpaGoodsSupplierService {
	
	BaseResponse delete(Integer id);

	BaseResponse save(SpaGoodsSupplier record);
    
	BaseResponse edit(SpaGoodsSupplier record);
    
	CommObjResponse<List<SpaGoodsSupplier>> list(SpaGoodsSupplier spaGoodsSupplier);

}
