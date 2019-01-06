package com.qisen.mts.spa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.SpaInoutDepotDao;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaInoutDepot;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;

@Service
public class SpaInoutDepotServiceImpl implements SpaInoutDepotService{

	@Autowired
	private SpaInoutDepotDao spaInoutDepotDao;
	
	@Autowired
	private SpaInoutDepotDetailDao inoutDepotDetailDao;
	
	@Autowired
	private GoodsService goodsService;
	
	@Override
	public CommObjResponse<List<SpaInoutDepot>> delete(SpaInoutDepot record) {
		CommObjResponse<List<SpaInoutDepot>> resp = new CommObjResponse<List<SpaInoutDepot>>();
		spaInoutDepotDao.delete(record);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepot>> save(SpaInoutDepot record) {
		CommObjResponse<List<SpaInoutDepot>> resp = new CommObjResponse<List<SpaInoutDepot>>();
		int count = spaInoutDepotDao.check(record);
		int saveCount = 0;
		SpaInoutDepot query = new SpaInoutDepot();
		query.setEid(record.getEid());
		query.setSid(record.getSid());
		if(count ==0 && null==record.getId()){
			saveCount = spaInoutDepotDao.save(record);
		}else if(count ==1){
			saveCount =spaInoutDepotDao.edit(record);
		}
		
		if(saveCount==1&&!CollectionUtils.isEmpty(record.getGoodsList())){
			List<SpaInoutDepotDetail> detailList = record.getGoodsList();
			List<SpaGoods> goodsList = new ArrayList<SpaGoods>(); 
			SpaGoods goods = null;
			for(SpaInoutDepotDetail spaInoutDepotDetail:detailList){
				spaInoutDepotDetail.setInoutno(record.getNo());
				spaInoutDepotDetail.setStatus("1");
				goods = new SpaGoods();
				goods.setNo(spaInoutDepotDetail.getNo());
				goods.setEid(spaInoutDepotDetail.getEid());
				goods.setSid(spaInoutDepotDetail.getSid());
				goods.setNum(spaInoutDepotDetail.getNum());//入库or出库数量
				goods.setStatus("1");
				goodsList.add(goods);
			}
			//执行明细表的插入或修改操作
			inoutDepotDetailDao.saveList(detailList);
			//判断出库还是入库对物品数量进行操作
			goodsService.updateGoodsNum(goodsList,record.getType());
		}
		resp.setBody(spaInoutDepotDao.list(query)); 
		return resp;
	}

	@Override
	public CommObjResponse<SpaInoutDepot> selectByPrimaryKey(Integer id) {
		CommObjResponse<SpaInoutDepot> resp = new CommObjResponse<>();
		SpaInoutDepot spainoutdepot = spaInoutDepotDao.selectByPrimaryKey(id);
		resp.setBody(spainoutdepot);
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaInoutDepot>> list(SpaInoutDepot body) {
		CommObjResponse<List<SpaInoutDepot>> response = new CommObjResponse<List<SpaInoutDepot>>();
		response.setBody(spaInoutDepotDao.list(body));
		return response;
	}

	@Override
	public CommObjResponse<SpaInoutDepot> getWithDetail(SpaInoutDepot body) {
		// TODO Auto-generated method stub
		return null;
	}

}
