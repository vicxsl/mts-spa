package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepot;

@Service
public class SpaInoutDepotServiceImpl implements SpaInoutDepotService{

	@Autowired
	private SpaInoutDepotDao spaInoutDepotDao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaInoutDepot record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaInoutDepot record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDao.updateByPrimaryKey(record);
		return resp;
	}

	@Override
	public CommObjResponse<SpaInoutDepot> selectByPrimaryKey(Integer id) {
		CommObjResponse<SpaInoutDepot> resp = new CommObjResponse<>();
		SpaInoutDepot spainoutdepot = spaInoutDepotDao.selectByPrimaryKey(id);
		resp.setBody(spainoutdepot);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepot>> selectInoutDepots(SpaInoutDepot body) {
		CommObjResponse<List<SpaInoutDepot>> response = new CommObjResponse<List<SpaInoutDepot>>();
		response.setBody(spaInoutDepotDao.selectInoutDepots(body));
		return response;
	}

}
