package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaGoodsSubTypeDao;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;

@Service
public class SpaGoodsSubTypeServiceImpl implements SpaGoodsSubTypeService{

	@Autowired
	private SpaGoodsSubTypeDao spagoodssubtypedao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spagoodssubtypedao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaGoodsSubType record) {
		BaseResponse resp = new BaseResponse();
		spagoodssubtypedao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaGoodsSubType record) {
		BaseResponse resp = new BaseResponse();
		spagoodssubtypedao.updateByPrimaryKey(record);
		return resp;
	}

	@Override
	public CommObjResponse<SpaGoodsSubType> selectByPrimaryKey(Integer id) {
		CommObjResponse<SpaGoodsSubType> resp = new CommObjResponse<>();
		SpaGoodsSubType spagoodssubtype = spagoodssubtypedao.selectByPrimaryKey(id);
		resp.setBody(spagoodssubtype);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaGoodsSubType>> selectGoodsSubTys(SpaGoodsSubType body) {
		CommObjResponse<List<SpaGoodsSubType>> response= new CommObjResponse<List<SpaGoodsSubType>>();
		response.setBody(spagoodssubtypedao.selectGoodsSubTys(body));
		return response;
	}

}
