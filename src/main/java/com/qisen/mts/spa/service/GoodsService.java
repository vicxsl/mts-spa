package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface GoodsService {

	/**
	 * 新增spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoods>> save(SpaRequest<SpaGoods> req);
	
	/**
	 * 删除spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoods>> delete(SpaRequest<SpaGoods> req);
	
	/**
	 * 查询spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoods>> list(SpaRequest<SpaGoods> req);

	void updateGoodsNum(List<SpaGoods> goodsList, String inoutdepottype);
	

	
}
