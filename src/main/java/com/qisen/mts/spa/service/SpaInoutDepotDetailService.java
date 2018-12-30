package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

public interface SpaInoutDepotDetailService {
	
	BaseResponse deleteByPrimaryKey(Integer id);

	BaseResponse insert(SpaInoutDepotDetail record);
    
	BaseResponse updateByPrimaryKey(SpaInoutDepotDetail record);
    
	CommObjResponse<SpaInoutDepotDetail> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepotDetail>> selectInoutDepotDetails(SpaInoutDepotDetail body);

}
