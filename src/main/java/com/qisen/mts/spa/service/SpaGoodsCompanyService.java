package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface SpaGoodsCompanyService {
	
	CommObjResponse<List<SpaGoodsCompany>> delete(SpaRequest<SpaGoodsCompany> req);

	CommObjResponse<List<SpaGoodsCompany>> save(SpaRequest<SpaGoodsCompany> req);
    
	CommObjResponse<List<SpaGoodsCompany>> list(SpaRequest<SpaGoodsCompany> req);

}
