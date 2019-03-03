package com.qisen.mts.spa.dao;

import java.util.List;

import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

public interface SpaInoutDepotDetailDao {
	
	public int delete(SpaInoutDepotDetail record);

	public int save(SpaInoutDepotDetail record);
    
	public int updateByPrimaryKey(SpaInoutDepotDetail record);
    
	public SpaInoutDepotDetail selectByPrimaryKey(Integer id);

	public List<SpaInoutDepotDetail> list(SpaInoutDepotDetail body);
	
	public List<SpaInoutDepotDetail> queryByInOutNo(SpaInoutDepotDetail body);
	
	public void saveList(List<SpaInoutDepotDetail> list);

}
