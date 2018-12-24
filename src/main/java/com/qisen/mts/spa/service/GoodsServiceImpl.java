package com.qisen.mts.spa.service;

import java.util.List;

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
		SpaGoods spaAccount = req.getBody();
		int count = goodsDao.check(spaAccount);
		if(spaAccount.getId() != null && spaAccount.getId() > 0){
			if (count == 0 ) {
				goodsDao.update(spaAccount);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				goodsDao.create(spaAccount);
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
		SpaGoods spaAccount = req.getBody();
		int count = goodsDao.delete(spaAccount);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}
		
		return resp;
	}

	/**
	 * 查询spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoods>> list(SpaRequest<SpaGoods> req) {
		CommObjResponse<List<SpaGoods>> resp = new CommObjResponse<List<SpaGoods>>();
		SpaGoods spaAccount = req.getBody();
		List<SpaGoods>  spaList = goodsDao.list(spaAccount);
		resp.setBody(spaList);
		return resp;
	}
	
}
