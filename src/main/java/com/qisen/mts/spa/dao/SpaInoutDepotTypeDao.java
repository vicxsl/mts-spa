package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

public interface SpaInoutDepotTypeDao {
	
	    int delete(SpaInoutDepotType record);

	    int save(SpaInoutDepotType record);
	    
	    int edit(SpaInoutDepotType record);
	    
	    SpaInoutDepotType selectByPrimaryKey(Integer id);

		List<SpaInoutDepotType> list(SpaInoutDepotType record);

		int check(SpaInoutDepotType record);

}
