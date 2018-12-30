package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

public interface SpaInoutDepotTypeService {
	BaseResponse deleteByPrimaryKey(Integer id);

	BaseResponse insert(SpaInoutDepotType record);
    
	BaseResponse updateByPrimaryKey(SpaInoutDepotType record);
    
	CommObjResponse<SpaInoutDepotType> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepotType>> selectDepotTypes(SpaInoutDepotType body);

}
