package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaGoodsCompanyDao;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;

@Service
public class SpaGoodsCompanyServiceImpl implements SpaGoodsCompanyService{

	@Autowired
	private SpaGoodsCompanyDao spaGoodsCompanyDao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaGoodsCompanyDao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaGoodsCompany record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsCompanyDao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaGoodsCompany record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsCompanyDao.updateByPrimaryKey(record);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaGoodsCompany>> selectGoodsCompanys(SpaGoodsCompany record) {
		CommObjResponse<List<SpaGoodsCompany>> response = new CommObjResponse<List<SpaGoodsCompany>>();
		response.setBody(spaGoodsCompanyDao.selectGoodsCompanys(record));
		return response;
	}

}
