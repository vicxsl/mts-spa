package com.qisen.mts.beauty.controller.sto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.entity.base.ItemWithStoNum;
import com.qisen.mts.common.model.entity.sto.Item;
import com.qisen.mts.common.model.request.ItemListRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoItemListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.sto.StoItemService;

@Controller
@RequestMapping("/beauty/stoItem")
public class StoItemController {

	@Autowired
	private StoItemService stoItemService;

	@RequestMapping("/create")
	public @ResponseBody
	BaseResponse create(@RequestBody Item stoItem) throws Exception  {
		return stoItemService.create(stoItem);
	}

	@RequestMapping("/delete/{id}")
	public @ResponseBody
	BaseResponse delete(@PathVariable int id) throws Exception  {
		return stoItemService.delete(id);
	}

	@RequestMapping("/update")
	public @ResponseBody
	BaseResponse update(@RequestBody Item stoItem) throws Exception  {
		return stoItemService.update(stoItem);
	}

	@RequestMapping("/list")
	public @ResponseBody
	BaseResponse list(@RequestBody PageRequest<StoItemListRequest> request) throws Exception  {
		return stoItemService.list(request);
	}

	@RequestMapping("/find/{id}")
	public @ResponseBody
	CommObjResponse<Item> find(@PathVariable int id) throws Exception  {
		return stoItemService.find(id);
	}
	
	@RequestMapping("/productList")
	@ResponseBody
	public PageResponse<List<ItemWithStoNum>> productlist(@RequestBody PageRequest<ItemListRequest> req) throws Exception {
		return stoItemService.productlist(req);
	}

}
