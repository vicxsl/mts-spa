package com.qisen.mts.common.service.sys;

import java.util.List;

import com.qisen.mts.common.model.entity.sys.Region;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface RegionService {
	
	public BaseResponse create(Region region);
	
	public BaseResponse delete(int id);
	
	public BaseResponse update(Region region);
	
	public CommObjResponse<List<Region>> list(int eid);
	
	public CommObjResponse<Region> find(int id);
	
}
