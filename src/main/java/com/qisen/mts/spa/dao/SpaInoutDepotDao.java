package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepot;

public interface SpaInoutDepotDao {
	
	int deleteByPrimaryKey(Integer id);
  
    int insert(SpaInoutDepot record);
    
    int updateByPrimaryKey(SpaInoutDepot record);
    
    SpaInoutDepot selectByPrimaryKey(Integer id);

	List<SpaInoutDepot> selectInoutDepots(SpaInoutDepot body);

}
