package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepot;

public interface SpaInoutDepotService {
	
	CommObjResponse<List<SpaInoutDepot>> delete(SpaInoutDepot record);
	  
	CommObjResponse<List<SpaInoutDepot>> save(SpaInoutDepot record);
    
    CommObjResponse<SpaInoutDepot> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepot>> list(SpaInoutDepot body);

	CommObjResponse<SpaInoutDepot> getWithDetail(SpaInoutDepot body);

}
