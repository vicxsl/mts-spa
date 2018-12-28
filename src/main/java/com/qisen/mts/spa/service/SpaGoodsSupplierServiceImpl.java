package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaGoodsSupplierDao;
import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;

@Service
public class SpaGoodsSupplierServiceImpl implements SpaGoodsSupplierService{

	@Autowired
	private SpaGoodsSupplierDao spaGoodsSupplierDao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaGoodsSupplier record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaGoodsSupplier record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.updateByPrimaryKeySelective(record);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaGoodsSupplier>> selectSuppliers(SpaGoodsSupplier record) {
		CommObjResponse<List<SpaGoodsSupplier>> resp = new CommObjResponse<>();
		List<SpaGoodsSupplier> list = spaGoodsSupplierDao.selectSuppliers(record);
		resp.setBody(list);
		return resp;
	}

}
