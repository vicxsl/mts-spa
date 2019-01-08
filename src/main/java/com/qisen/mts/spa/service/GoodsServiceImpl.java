package com.qisen.mts.spa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsDao;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;
	
	/**
	 * 新增spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoods>> save(SpaRequest<SpaGoods> req) {
		CommObjResponse<List<SpaGoods>> resp = new CommObjResponse<List<SpaGoods>>();
		SpaGoods spaGoods = req.getBody();
		SpaGoods queryGoods = new SpaGoods();
		queryGoods.setEid(spaGoods.getEid());
		queryGoods.setSid(spaGoods.getSid());
		int count = goodsDao.check(spaGoods);
		if(spaGoods.getId() != null && spaGoods.getId() > 0){
			if (count == 0 ) {
				goodsDao.update(spaGoods);
				List<SpaGoods>  goodsList = goodsDao.list(queryGoods);
				resp.setBody(goodsList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				goodsDao.create(spaGoods);
				List<SpaGoods>  goodsList = goodsDao.list(queryGoods);
				resp.setBody(goodsList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}
		return resp;
	}

	/**
	 * 删除spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoods>> delete(SpaRequest<SpaGoods> req) {
		CommObjResponse<List<SpaGoods>> resp = new CommObjResponse<List<SpaGoods>>();
		SpaGoods spaGoods = req.getBody();
		SpaGoods queryGoods = new SpaGoods();
		queryGoods.setEid(spaGoods.getEid());
		queryGoods.setSid(spaGoods.getSid());
		int count = goodsDao.delete(spaGoods);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}else{
			List<SpaGoods>  spaList = goodsDao.list(queryGoods);
			resp.setBody(spaList);
		}
		return resp;
	}

	/**
	 * 查询spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoods>> list(SpaRequest<SpaGoods> req) {
		CommObjResponse<List<SpaGoods>> resp = new CommObjResponse<List<SpaGoods>>();
		SpaGoods spaGoods = req.getBody();
		List<SpaGoods>  spaList = goodsDao.list(spaGoods);
		resp.setBody(spaList);
		return resp;
	}
	@Override
	public void updateGoodsNum(List<SpaGoods> goodsList, String inoutdepottype) {
		List<SpaGoods> oldGoodsList = new ArrayList<SpaGoods>();
		Map<String,Double> goodsNumMap = new HashMap<String,Double>();
		for(SpaGoods spaGoods : goodsList){
			SpaGoods goods = goodsDao.getGoodsByPara(spaGoods);
			oldGoodsList.add(goods);
			goodsNumMap.put(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getSid(), spaGoods.getNum());
		}
		double num = 0.0;
		for(SpaGoods spaGoods :oldGoodsList){
			if(inoutdepottype.equals("2")){
				num = spaGoods.getNum()+goodsNumMap.get(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getSid());
			}else{
				num = spaGoods.getNum()-goodsNumMap.get(spaGoods.getId()+"-"+spaGoods.getEid()+spaGoods.getSid());
			}
			spaGoods.setNum(num);
			goodsDao.update(spaGoods);
		}
		
	}
	
}
