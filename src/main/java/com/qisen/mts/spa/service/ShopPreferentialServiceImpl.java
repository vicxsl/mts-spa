package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.ShopPreferentialDao;
import com.qisen.mts.spa.model.entity.SpaShopPreferential;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class ShopPreferentialServiceImpl implements ShopPreferentialService{
	
	
	
	@Autowired
	private ShopPreferentialDao shopPreferentialDao;

	/**
	 * 查询店铺优惠列表
	 */
	@Override
	public CommObjResponse<List<SpaShopPreferential>> list(SpaRequest<SpaShopPreferential> req) {
		CommObjResponse<List<SpaShopPreferential>> resp = new CommObjResponse<List<SpaShopPreferential>>();
		SpaShopPreferential query = req.getBody();
		query.setEid(req.getEid());
		List<SpaShopPreferential> list = shopPreferentialDao.list(query);
		resp.setBody(list);
		return resp;
	}

	/**
	 * 编辑优惠后再查询列表
	 */
	@Override
	public CommObjResponse<List<SpaShopPreferential>> edit(SpaRequest<SpaShopPreferential> req) {
		CommObjResponse<List<SpaShopPreferential>> resp = new CommObjResponse<List<SpaShopPreferential>>();
		SpaShopPreferential query = req.getBody();
		shopPreferentialDao.saveOrUpdate(query);//编辑优惠
		List<SpaShopPreferential> list = shopPreferentialDao.list(query);
		resp.setBody(list);
		return resp;
	}
	/**
	 * 批量删除优惠
	 */
	@Override
	public CommObjResponse<List<SpaShopPreferential>> deleteList(SpaRequest<List<SpaShopPreferential>> req) {
		CommObjResponse<List<SpaShopPreferential>> resp = new CommObjResponse<List<SpaShopPreferential>>();
		SpaShopPreferential query = new SpaShopPreferential();
		query.setAppid(req.getAppid());
		query.setEid(req.getEid());
		List<SpaShopPreferential> deleteList = req.getBody();
		shopPreferentialDao.deleteList(deleteList);
		List<SpaShopPreferential> list = shopPreferentialDao.list(query);
		resp.setBody(list);
		return resp;
	}
}
