package com.qisen.mts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.CityDao;
import com.qisen.mts.admin.dao.OrganDao;
import com.qisen.mts.admin.dao.OutlayClassDao;
import com.qisen.mts.admin.dao.ProductsClassDao;
import com.qisen.mts.admin.dao.ProvinceDao;
import com.qisen.mts.admin.model.entity.City;
import com.qisen.mts.admin.model.entity.Organ;
import com.qisen.mts.admin.model.entity.OutlayClass;
import com.qisen.mts.admin.model.entity.ProductsClass;
import com.qisen.mts.admin.model.entity.Province;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
@Service
public class AdminClassServiceImpl implements AdminClassService{
	@Autowired 
	private ProductsClassDao  productsClassDao;
	
	@Autowired 
	private OutlayClassDao  outlayClassClassDao;
	
	@Autowired 
	private OrganDao  organDao;
	
	@Autowired 
	private ProvinceDao provinceDao;
	
	@Autowired 
	private CityDao cityDao;

	@Override
	public CommObjResponse<List<ProductsClass>> prodClassList(AdminRequest<JSONObject> req) {
		CommObjResponse<List<ProductsClass>> resp=new CommObjResponse<List<ProductsClass>>();
		List<ProductsClass> list=null;
		if(req.getBody() !=null){
			list=this.productsClassDao.list(req.getBody().getString("keyword"));
		}else{
			list=this.productsClassDao.list(null);
		}
		
		resp.setBody(list);
		return resp;
	}

	@Override
	public BaseResponse prodClassCreate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		ProductsClass productsClass=req.getBody().toJavaObject(ProductsClass.class);
		int count = productsClassDao.productsClassCheck(productsClass);
		if(count ==0){
			this.productsClassDao.create(productsClass);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse prodClassDelete(AdminRequest<JSONObject> req){
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.productsClassDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse prodClassUpdate(AdminRequest<JSONObject> req){
		BaseResponse resp=new BaseResponse();
		ProductsClass productsClass=req.getBody().toJavaObject(ProductsClass.class);
		int count = productsClassDao.productsClassCheck(productsClass);
		if(count ==0){
			this.productsClassDao.update(productsClass);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public CommObjResponse<List<OutlayClass>> outlayClassList(AdminRequest<JSONObject> req) {
		CommObjResponse<List<OutlayClass>> resp=new CommObjResponse<List<OutlayClass>>();
		List<OutlayClass> list=this.outlayClassClassDao.list(req.getBody().getString("keyword"));
		resp.setBody(list);
		return resp;
	}

	@Override
	public BaseResponse outlayClassCreate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		OutlayClass outlayClass=req.getBody().toJavaObject(OutlayClass.class);
		int count = outlayClassClassDao.outlayClassCheck(outlayClass);
		if(count ==0){
			this.outlayClassClassDao.create(outlayClass);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse outlayClassDelete(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.outlayClassClassDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse outlayClassUpdate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		OutlayClass outlayClass=req.getBody().toJavaObject(OutlayClass.class);
		int count = outlayClassClassDao.outlayClassCheck(outlayClass);
		if(count ==0){
			this.outlayClassClassDao.update(outlayClass);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> organList(AdminRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp=new CommObjResponse<JSONObject>();
		JSONObject obj=new JSONObject();
		List<Organ> organlist=this.organDao.list(req.getBody().getString("keyword"));
		List<Province> provinceList=this.provinceDao.list(null);
		List<City> cityList=this.cityDao.list(null);
		obj.put("organList", organlist);
		obj.put("provinceList", provinceList);
		obj.put("cityList", cityList);
		resp.setBody(obj);
		return resp;
	}

	@Override
	public BaseResponse organCreate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Organ organ=req.getBody().toJavaObject(Organ.class);
		int count = organDao.organCount(organ);
		if(count ==0){
			this.organDao.create(organ);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse organDelete(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.organDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse organUpdate(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Organ organ=req.getBody().toJavaObject(Organ.class);
		int count = organDao.organCount(organ);
		if(count ==0){
			this.organDao.update(organ);
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

}
