package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

public interface SpaInoutDepotDetailDao {
	
	int delete(SpaInoutDepotDetail record);

    int save(SpaInoutDepotDetail record);
    
    int updateByPrimaryKey(SpaInoutDepotDetail record);
    
    SpaInoutDepotDetail selectByPrimaryKey(Integer id);

	List<SpaInoutDepotDetail> list(SpaInoutDepotDetail body);
	
	void saveList(List<SpaInoutDepotDetail> detailList);

}
