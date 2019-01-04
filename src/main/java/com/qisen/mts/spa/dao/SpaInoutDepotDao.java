package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepot;

public interface SpaInoutDepotDao {
	
	int delete(SpaInoutDepot record);
  
    int save(SpaInoutDepot record);
    
    int edit(SpaInoutDepot record);
    
    SpaInoutDepot selectByPrimaryKey(Integer id);

	List<SpaInoutDepot> list(SpaInoutDepot body);

	int check(SpaInoutDepot record);

}
