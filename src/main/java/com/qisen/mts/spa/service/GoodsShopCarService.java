package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface GoodsShopCarService {


	/**
	 * 查询购物车列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsShopCar>> list(SpaRequest<SpaGoodsShopCar> req);

	/**
	 * 更新购物车列表
	 * @param req
	 * @return
	 */
	public CommObjResponse<Integer> saveList(SpaRequest<List<SpaGoodsShopCar>> req);

	
}
