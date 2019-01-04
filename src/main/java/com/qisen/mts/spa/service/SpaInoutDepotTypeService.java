package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

public interface SpaInoutDepotTypeService {
	CommObjResponse<List<SpaInoutDepotType>> delete(SpaInoutDepotType record);

	CommObjResponse<List<SpaInoutDepotType>> save(SpaInoutDepotType record);
    
	CommObjResponse<SpaInoutDepotType> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepotType>> list(SpaInoutDepotType body);

}
