package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface GoodsShopCarService {

	/**
	 * 新增spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsShopCar>> edit(SpaRequest<SpaGoodsShopCar> req);
	

	/**
	 * 查询spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsShopCar>> list(SpaRequest<SpaGoodsShopCar> req);

	
}
