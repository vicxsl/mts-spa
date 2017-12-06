package com.qisen.mts.common.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.sys.RegionDao;
import com.qisen.mts.common.model.entity.sys.Region;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Override
	public BaseResponse create(Region region) {
		BaseResponse response = new BaseResponse();
		regionDao.create(region);
		return response;
	}

	@Override
	public BaseResponse delete(int id) {
		BaseResponse response = new BaseResponse();
		regionDao.delete(id);
		return response;
	}

	@Override
	public BaseResponse update(Region region) {
		BaseResponse response = new BaseResponse();
		regionDao.update(region);
		return response;
	}

	@Override
	public CommObjResponse<List<Region>> list(int eid) {
		CommObjResponse<List<Region>> response = new CommObjResponse<List<Region>>();
		List<Region> regionList = regionDao.list(eid);
		response.setBody(regionList);
		return response;
	}

	@Override
	public CommObjResponse<Region> find(int id) {
		CommObjResponse<Region> response = new CommObjResponse<Region>();
		Region region = regionDao.find(id);
		response.setBody(region);
		return response;
	}

}
