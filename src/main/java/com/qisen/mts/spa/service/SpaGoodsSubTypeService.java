package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;

public interface SpaGoodsSubTypeService {
	
	BaseResponse deleteByPrimaryKey(Integer id);

	BaseResponse insert(SpaGoodsSubType record);
    
	BaseResponse updateByPrimaryKey(SpaGoodsSubType record);
    
	CommObjResponse<SpaGoodsSubType> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaGoodsSubType>> selectGoodsSubTys(SpaGoodsSubType body);

}
