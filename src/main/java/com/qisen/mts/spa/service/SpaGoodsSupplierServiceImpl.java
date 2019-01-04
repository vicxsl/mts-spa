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
	public BaseResponse delete(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse save(SpaGoodsSupplier record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.save(record);
		return resp;
	}

	@Override
	public BaseResponse edit(SpaGoodsSupplier record) {
		BaseResponse resp = new BaseResponse();
		spaGoodsSupplierDao.edit(record);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaGoodsSupplier>> list(SpaGoodsSupplier record) {
		CommObjResponse<List<SpaGoodsSupplier>> resp = new CommObjResponse<>();
		List<SpaGoodsSupplier> list = spaGoodsSupplierDao.list(record);
		resp.setBody(list);
		return resp;
	}

}
