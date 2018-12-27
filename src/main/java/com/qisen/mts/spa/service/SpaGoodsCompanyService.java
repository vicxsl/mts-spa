package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;

public interface SpaGoodsCompanyService {
	
	BaseResponse deleteByPrimaryKey(Integer id);

	BaseResponse insert(SpaGoodsCompany record);
    
	BaseResponse updateByPrimaryKey(SpaGoodsCompany record);
    
	CommObjResponse<List<SpaGoodsCompany>> selectGoodsCompanys(SpaGoodsCompany record);

}
