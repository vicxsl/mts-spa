package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsCompany;

public interface SpaGoodsCompanyDao {
	
	int deleteByPrimaryKey(Integer id);

    int insert(SpaGoodsCompany record);
    
    int updateByPrimaryKey(SpaGoodsCompany record);
    
    List<SpaGoodsCompany> selectGoodsCompanys(SpaGoodsCompany record);

}
