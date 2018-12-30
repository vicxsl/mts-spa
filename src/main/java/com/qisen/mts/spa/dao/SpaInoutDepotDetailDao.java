package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

public interface SpaInoutDepotDetailDao {
	
	int deleteByPrimaryKey(Integer id);

    int insert(SpaInoutDepotDetail record);
    
    int updateByPrimaryKey(SpaInoutDepotDetail record);
    
    SpaInoutDepotDetail selectByPrimaryKey(Integer id);

	List<SpaInoutDepotDetail> selectInoutDepotDetails(SpaInoutDepotDetail body);

}
