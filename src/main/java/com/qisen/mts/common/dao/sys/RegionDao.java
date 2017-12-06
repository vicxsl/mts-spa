package com.qisen.mts.common.dao.sys;

import java.util.List;

import com.qisen.mts.common.model.entity.sys.Region;

public interface RegionDao {
	
	public void create(Region region);
	
	public void delete(Integer id);
	
	public void update(Region region);
	
	public List<Region> list(Integer eid);
	
	public Region find(Integer id);
	
}
