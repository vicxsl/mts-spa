package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class GoodsShopCarServiceImpl implements GoodsShopCarService {

	@Autowired
	private GoodsShopCarDao goodsShopCarDao;

	/**
	 * 查询spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoodsShopCar>> list(SpaRequest<SpaGoodsShopCar> req) {
		CommObjResponse<List<SpaGoodsShopCar>> resp = new CommObjResponse<List<SpaGoodsShopCar>>();
		SpaGoodsShopCar body = req.getBody();
		List<SpaGoodsShopCar> spaList = goodsShopCarDao.list(body);
		resp.setBody(spaList);
		return resp;
	}

	@Override
	public CommObjResponse<Integer> saveList(SpaRequest<List<SpaGoodsShopCar>> req) {
		CommObjResponse<Integer> response = new CommObjResponse<Integer>();
		List<SpaGoodsShopCar> shopCarList=req.getBody();
		SpaGoodsShopCar shopCar = new SpaGoodsShopCar();
		shopCar.setEid(req.getEid());
		shopCar.setAppid(req.getAppid());
		shopCar.setOpenid(req.getToken());
		goodsShopCarDao.deleteByOpenid(shopCar);
		if(!CollectionUtils.isEmpty(shopCarList)){
			goodsShopCarDao.saveList(shopCarList);
		}
		return response;
	}

}
