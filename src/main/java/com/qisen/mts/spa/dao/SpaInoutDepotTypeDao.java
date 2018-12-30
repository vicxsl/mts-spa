package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

public interface SpaInoutDepotTypeDao {
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(SpaInoutDepotType record);
	    
	    int updateByPrimaryKey(SpaInoutDepotType record);
	    
	    SpaInoutDepotType selectByPrimaryKey(Integer id);

		List<SpaInoutDepotType> selectDepotTypes(SpaInoutDepotType record);

}
