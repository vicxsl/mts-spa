package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotTypeDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

@Service
public class SpaInoutDepotTypeServiceImpl implements SpaInoutDepotTypeService{

	@Autowired
	private SpaInoutDepotTypeDao spaInoutDepotTypeDao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotTypeDao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaInoutDepotType record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotTypeDao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaInoutDepotType record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotTypeDao.updateByPrimaryKey(record);
		return resp;
	}

	@Override
	public CommObjResponse<SpaInoutDepotType> selectByPrimaryKey(Integer id) {
		CommObjResponse<SpaInoutDepotType> resp = new CommObjResponse<>();
		SpaInoutDepotType spainoutdepottype = spaInoutDepotTypeDao.selectByPrimaryKey(id);
		resp.setBody(spainoutdepottype);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepotType>> selectDepotTypes(SpaInoutDepotType body) {
		CommObjResponse<List<SpaInoutDepotType>> response = new CommObjResponse<List<SpaInoutDepotType>>();
		response.setBody(spaInoutDepotTypeDao.selectDepotTypes(body));
		return response;
	}

}
