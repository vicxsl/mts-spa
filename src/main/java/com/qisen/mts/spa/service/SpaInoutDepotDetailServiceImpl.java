package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

@Service
public class SpaInoutDepotDetailServiceImpl implements SpaInoutDepotDetailService{

	@Autowired
	private SpaInoutDepotDetailDao spaInoutDepotDetailDao;
	
	@Override
	public CommObjResponse<List<SpaInoutDepotDetail>> delete(SpaInoutDepotDetail record) {
		CommObjResponse<List<SpaInoutDepotDetail>> resp = new CommObjResponse<List<SpaInoutDepotDetail>>();
		spaInoutDepotDetailDao.delete(record);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepotDetail>> save(SpaInoutDepotDetail record) {
		CommObjResponse<List<SpaInoutDepotDetail>> resp = new CommObjResponse<List<SpaInoutDepotDetail>>();
		spaInoutDepotDetailDao.save(record);
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
	public CommObjResponse<List<SpaInoutDepotDetail>> list(SpaInoutDepotDetail body) {
		CommObjResponse<List<SpaInoutDepotDetail>> response = new CommObjResponse<List<SpaInoutDepotDetail>>();
		response.setBody(spaInoutDepotDetailDao.list(body));
		return response;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepotDetail>> queryByInOutNo(SpaInoutDepotDetail body) {
		CommObjResponse<List<SpaInoutDepotDetail>> response = new CommObjResponse<List<SpaInoutDepotDetail>>();
		response.setBody(spaInoutDepotDetailDao.queryByInOutNo(body));
		return response;
	}
}
