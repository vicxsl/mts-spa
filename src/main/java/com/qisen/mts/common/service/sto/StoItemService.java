package com.qisen.mts.common.service.sto;

import java.util.List;

import com.qisen.mts.common.model.entity.base.ItemWithStoNum;
import com.qisen.mts.common.model.entity.sto.Item;
import com.qisen.mts.common.model.request.ItemListRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoItemListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
public interface StoItemService {

	public BaseResponse create(Item stoItem);
	
	public BaseResponse delete(int id) ;

	public BaseResponse update(Item stoItem);

	public BaseResponse list(PageRequest<StoItemListRequest> request);

	public CommObjResponse<Item> find(int id);
	
	/**
	 * 商品列表，带库存
	 * @param req
	 * @return
	 */
	public PageResponse<List<ItemWithStoNum>> productlist(PageRequest<ItemListRequest> req);

}
