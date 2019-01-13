package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

public interface SpaInoutDepotDetailService {
	
	CommObjResponse<List<SpaInoutDepotDetail>> delete(SpaInoutDepotDetail record);

	CommObjResponse<List<SpaInoutDepotDetail>> save(SpaInoutDepotDetail record);
    
	CommObjResponse<SpaInoutDepotDetail> selectByPrimaryKey(Integer id);

	CommObjResponse<List<SpaInoutDepotDetail>> list(SpaInoutDepotDetail body);
	
	CommObjResponse<List<SpaInoutDepotDetail>> queryByInOutNo(SpaInoutDepotDetail body);
	
}
