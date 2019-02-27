package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaGoodsCompanyDao;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class SpaGoodsCompanyServiceImpl implements SpaGoodsCompanyService{

	@Autowired
	private SpaGoodsCompanyDao spaGoodsCompanyDao;
	
	@Override
	public CommObjResponse<List<SpaGoodsCompany>> delete(SpaRequest<SpaGoodsCompany> req) {
		CommObjResponse<List<SpaGoodsCompany>> resp = new CommObjResponse<List<SpaGoodsCompany>>();
		SpaGoodsCompany spa = req.getBody();
		SpaGoodsCompany query = new SpaGoodsCompany();
		query.setEid(spa.getEid());
		query.setAppid(spa.getAppid());
		int count = spaGoodsCompanyDao.delete(spa);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}else{
			List<SpaGoodsCompany>  queryList = spaGoodsCompanyDao.list(query);
			resp.setBody(queryList);
		}
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaGoodsCompany>> save(SpaRequest<SpaGoodsCompany> req) {
		CommObjResponse<List<SpaGoodsCompany>> resp = new CommObjResponse<List<SpaGoodsCompany>>();
		SpaGoodsCompany spa = req.getBody();
		SpaGoodsCompany query = new SpaGoodsCompany();
		query.setEid(spa.getEid());
		query.setAppid(spa.getAppid());
		int count = spaGoodsCompanyDao.check(spa);
		if(spa.getId() != null && spa.getId() > 0){
			if (count == 0 ) {
				spaGoodsCompanyDao.update(spa);
				List<SpaGoodsCompany>  spaList = spaGoodsCompanyDao.list(query);
				resp.setBody(spaList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				spaGoodsCompanyDao.create(spa);
				List<SpaGoodsCompany>  spaList = spaGoodsCompanyDao.list(query);
				resp.setBody(spaList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}
		return resp;
	}


	@Override
	public CommObjResponse<List<SpaGoodsCompany>> list(SpaRequest<SpaGoodsCompany> req) {
		CommObjResponse<List<SpaGoodsCompany>> response = new CommObjResponse<List<SpaGoodsCompany>>();
		response.setBody(spaGoodsCompanyDao.list(req.getBody()));
		return response;
	}

}
