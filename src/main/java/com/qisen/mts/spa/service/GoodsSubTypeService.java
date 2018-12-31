package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface GoodsSubTypeService {

	/**
	 * 新增spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsSubType>> save(SpaRequest<SpaGoodsSubType> req);
	
	/**
	 * 删除spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsSubType>> delete(SpaRequest<SpaGoodsSubType> req);
	
	/**
	 * 查询spa商品
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaGoodsSubType>> list(SpaRequest<SpaGoodsSubType> req);
	

	
}
