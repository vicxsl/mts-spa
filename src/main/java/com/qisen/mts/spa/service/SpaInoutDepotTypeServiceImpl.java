package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotTypeDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;

@Service
public class SpaInoutDepotTypeServiceImpl implements SpaInoutDepotTypeService{

	@Autowired
	private SpaInoutDepotTypeDao spaInoutDepotTypeDao;
	
	@Override
	public CommObjResponse<List<SpaInoutDepotType>> delete(SpaInoutDepotType record) {
		CommObjResponse<List<SpaInoutDepotType>> resp = new CommObjResponse<List<SpaInoutDepotType>>();
		SpaInoutDepotType querySpaInoutDepotType = new SpaInoutDepotType();
		querySpaInoutDepotType.setEid(record.getEid());
		querySpaInoutDepotType.setAppid(record.getAppid());
		int count = spaInoutDepotTypeDao.delete(record);
		if(count == 1 ){
			resp.setBody(spaInoutDepotTypeDao.list(querySpaInoutDepotType));
		}else{
			
		}
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepotType>> save(SpaInoutDepotType record) {
		CommObjResponse<List<SpaInoutDepotType>> resp = new CommObjResponse<List<SpaInoutDepotType>>();
		SpaInoutDepotType querySpaInoutDepotType = new SpaInoutDepotType();
		querySpaInoutDepotType.setEid(record.getEid());
		querySpaInoutDepotType.setAppid(record.getAppid());
		int count = spaInoutDepotTypeDao.check(record);//查询出入库类型？
		if(null!=record.getId()&&record.getId()>0){
			if(count == 0){
				spaInoutDepotTypeDao.edit(record);
				resp.setBody(spaInoutDepotTypeDao.list(querySpaInoutDepotType));
			}else{
				
			}
		}else{
			if(count ==0){
				spaInoutDepotTypeDao.save(record);
				resp.setBody(spaInoutDepotTypeDao.list(querySpaInoutDepotType));
			}else{
				
			}
		}
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
	public CommObjResponse<List<SpaInoutDepotType>> list(SpaInoutDepotType body) {
		CommObjResponse<List<SpaInoutDepotType>> response = new CommObjResponse<List<SpaInoutDepotType>>();
		response.setBody(spaInoutDepotTypeDao.list(body));
		return response;
	}

}
