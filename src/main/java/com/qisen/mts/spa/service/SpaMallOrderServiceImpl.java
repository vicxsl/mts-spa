package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.dao.SpaMallOrderDao;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.entity.SpaMallOrder;

@Service
public class SpaMallOrderServiceImpl implements SpaMallOrderService{
	
	@Autowired
	private SpaMallOrderDao spaMallOrderDao;
	
	@Autowired
	private SpaInoutDepotDetailDao inoutDepotDetailDao;

	@Override
	public CommObjResponse<List<SpaMallOrder>> save(SpaMallOrder body) {
		if(!CollectionUtils.isEmpty(body.getGoodsList())){
			spaMallOrderDao.create(body);//插入订单表
			
			for(SpaInoutDepotDetail spaInoutDepotDetail:body.getGoodsList()){
				spaInoutDepotDetail.setInOutNo(body.getId().toString());
				spaInoutDepotDetail.setTotalMoney(spaInoutDepotDetail.getNum()
						*spaInoutDepotDetail.getSalePrice());
			}
			//执行明细表的插入或修改操作
			inoutDepotDetailDao.saveList(body.getGoodsList());
		}
		return null;
	}

	@Override
	public CommObjResponse<List<SpaMallOrder>> list(SpaMallOrder body) {
		CommObjResponse<List<SpaMallOrder>> response = new CommObjResponse<List<SpaMallOrder>>();
		response.setBody(spaMallOrderDao.list(body));
		return response;
	}

}
