package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

@Service
public class SpaInoutDepotDetailServiceImpl implements SpaInoutDepotDetailService{

	@Autowired
	private SpaInoutDepotDetailDao spaInoutDepotDetailDao;
	
	@Override
	public BaseResponse deleteByPrimaryKey(Integer id) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDetailDao.deleteByPrimaryKey(id);
		return resp;
	}

	@Override
	public BaseResponse insert(SpaInoutDepotDetail record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDetailDao.insert(record);
		return resp;
	}

	@Override
	public BaseResponse updateByPrimaryKey(SpaInoutDepotDetail record) {
		BaseResponse resp = new BaseResponse();
		spaInoutDepotDetailDao.updateByPrimaryKey(record);
		return resp;
	}

	@Override
	public CommObjResponse<SpaInoutDepotDetail> selectByPrimaryKey(Integer id) {
		CommObjResponse<SpaInoutDepotDetail> resp = new CommObjResponse<>();
		SpaInoutDepotDetail spainoutdepotdetail = spaInoutDepotDetailDao.selectByPrimaryKey(id);
		resp.setBody(spainoutdepotdetail);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepotDetail>> selectInoutDepotDetails(SpaInoutDepotDetail body) {
		CommObjResponse<List<SpaInoutDepotDetail>> response = new CommObjResponse<List<SpaInoutDepotDetail>>();
		response.setBody(spaInoutDepotDetailDao.selectInoutDepotDetails(body));
		return response;
	}

}
