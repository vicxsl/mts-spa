package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShopPreferential;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface ShopPreferentialService {
	/**
	 * 查询spa商户优惠列表
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShopPreferential>> list(SpaRequest<SpaShopPreferential> req);

	/**
	 * 编辑商户优惠
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShopPreferential>> edit(SpaRequest<SpaShopPreferential> req);

	/**
	 * 批量删除商户优惠
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<SpaShopPreferential>> deleteList(SpaRequest<List<SpaShopPreferential>> req);

}
