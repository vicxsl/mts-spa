package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaGoodsCompany;

public interface SpaGoodsCompanyDao {
	int check(SpaGoodsCompany record);
	
	int delete(SpaGoodsCompany record);

	void create(SpaGoodsCompany record);
	
	void update(SpaGoodsCompany record);
    
    List<SpaGoodsCompany> list(SpaGoodsCompany record);

}
