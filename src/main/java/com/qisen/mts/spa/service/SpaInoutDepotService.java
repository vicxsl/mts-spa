package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepot;

public interface SpaInoutDepotService {
	
	BaseResponse deleteByPrimaryKey(Integer id);
	  
	BaseResponse insert(SpaInoutDepot record);
    
	BaseResponse updateByPrimaryKey(SpaInoutDepot record);
    
    CommObjResponse<SpaInoutDepot> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepot>> selectInoutDepots(SpaInoutDepot body);

}
