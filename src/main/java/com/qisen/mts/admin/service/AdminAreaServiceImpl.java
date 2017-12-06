package com.qisen.mts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.CityDao;
import com.qisen.mts.admin.dao.OrganDao;
import com.qisen.mts.admin.dao.ProvinceDao;
import com.qisen.mts.admin.model.entity.City;
import com.qisen.mts.admin.model.entity.Organ;
import com.qisen.mts.admin.model.entity.Province;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class AdminAreaServiceImpl implements AdminAreaService{
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private OrganDao organDao;
	
	@Override
	public CommObjResponse<List<Province>> provinceList(AdminRequest<JSONObject> req) {
		CommObjResponse<List<Province>> resp=new CommObjResponse<List<Province>>(); 
		List<Province> list=this.provinceDao.list(req.getBody().getString("keyword"));
		resp.setBody(list);
		return resp;
	}

	@Override
	public BaseResponse provinceCreate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Province province=req.getBody().toJavaObject(Province.class);
//		int count = provinceDao.checkProvince(province);
//		if(count ==0){
			this.provinceDao.create(province);
//		}else{
//			resp.setCode(MsgCode.COMMON_NO_OR_NAME_OR_MOBILE_EXIST);
//		}
		return resp;
	}

	@Override
	public BaseResponse provinceDelete(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.provinceDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse provinceUpdate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Province province=req.getBody().toJavaObject(Province.class);
//		int count = provinceDao.checkProvince(province);
//		if(count ==0){
			this.provinceDao.update(province);
//		}else{
//			resp.setCode(MsgCode.COMMON_NO_OR_NAME_OR_MOBILE_EXIST);
//		}
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> cityList(AdminRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp=new CommObjResponse<JSONObject>(); 
		JSONObject obj=new JSONObject();
		List<Province> provinceList=this.provinceDao.list(null);
		List<City> cityList=this.cityDao.list(req.getBody().getString("keyword"));
		List<Organ> organList=this.organDao.list(null);
		obj.put("provinceList", provinceList);
		obj.put("cityList", cityList);
		obj.put("organList", organList);
		resp.setBody(obj);
		return resp;
	}

	@Override
	public BaseResponse cityCreate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		City city=req.getBody().toJavaObject(City.class);
		int count = cityDao.checkCity(city);
		if(count ==0){
			this.cityDao.create(city);
		}else{
			resp.setCode(MsgCode.CITY_AREAID_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse cityDelete(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.cityDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse cityUpdate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		City city=req.getBody().toJavaObject(City.class);
		int count = cityDao.checkCity(city);
		if(count ==0){
			this.cityDao.update(city);
		}else{
			resp.setCode(MsgCode.CITY_AREAID_EXIST);
		}
		return resp;
	}

}
