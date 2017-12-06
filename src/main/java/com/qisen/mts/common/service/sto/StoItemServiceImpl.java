package com.qisen.mts.common.service.sto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.base.ItemDao;
import com.qisen.mts.common.dao.sto.StoItemDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.ItemWithStoNum;
import com.qisen.mts.common.model.entity.sto.Item;
import com.qisen.mts.common.model.request.ItemListRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoItemListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.model.response.StoItemListResponse;
@Service
public class StoItemServiceImpl implements StoItemService {

	@Autowired
	private StoItemDao stoItemDao;
	
	@Autowired 
	private ItemDao itemDao;

	@Override
	public BaseResponse create(Item stoItem) {
		BaseResponse response = new BaseResponse();
		int rcount = stoItemDao.check(stoItem);
		if (rcount == 0) {
			stoItemDao.create(stoItem);
		} else {
			response.setResult(ResultCode.FAILED);
		}
		return response;
	}

	@Override
	public BaseResponse delete(int id) {
		BaseResponse response = new BaseResponse();
		stoItemDao.delete(id);
		return response;
	}

	@Override
	public BaseResponse update(Item stoItem) {
		BaseResponse response = new BaseResponse();
		stoItemDao.update(stoItem);
		return response;
	}

	@Override
	public BaseResponse list(PageRequest<StoItemListRequest> request) {
		PageResponse<StoItemListResponse> resp = new PageResponse<>();
		StoItemListResponse listResp = this.stoItemDao.listCounts(request);
		resp.setCount(listResp.getTotalCount());
		resp.setPageSize(request.getPageSize());
		listResp.setItems(this.stoItemDao.stoItemlist(request));
		resp.setBody(listResp);
		return resp;
	}

	@Override
	public CommObjResponse<Item> find(int id) {
		CommObjResponse<Item> response = new CommObjResponse<Item>();
		Item stoItem = stoItemDao.find(id);
		response.setBody(stoItem);
		return response;
	}
	
	@Override
	public PageResponse<List<ItemWithStoNum>> productlist(PageRequest<ItemListRequest> req) {
		PageResponse<List<ItemWithStoNum>> resp = new PageResponse<>();
		resp.setCount(this.itemDao.productListCount(req));
		resp.setBody(this.itemDao.productList(req));
		resp.setPageSize(req.getPageSize());
		return resp;
	}

}
