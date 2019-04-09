package com.qisen.mts.spa.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaImg;
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
	public CommObjResponse<List<SpaGoods>> delete(SpaRequest<List<SpaGoods>> req);
	
	/**
	 * 查询spa商品
	 * @param req
	 * @return
	 */
	public PageResponse<List<SpaGoods>> list(PageRequest<SpaGoods> req);

	void updateGoodsNum(List<SpaGoods> goodsList, String inoutdepottype);
	
	public CommObjResponse<List<SpaImg>> goodsImgList(SpaRequest<SpaImg> req);
	
	public CommObjResponse<JSONObject> details(SpaRequest<SpaGoods> req);
		
	
}
