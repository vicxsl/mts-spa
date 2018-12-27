package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsType;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface GoodsTypeService {

	/**
	 * 新增spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsType>> save(SpaRequest<SpaGoodsType> req);
	
	/**
	 * 删除spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsType>> delete(SpaRequest<SpaGoodsType> req);
	
	/**
	 * 查询spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsType>> list(SpaRequest<SpaGoodsType> req);
	

	
}
