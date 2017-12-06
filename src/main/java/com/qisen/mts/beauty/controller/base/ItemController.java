package com.qisen.mts.beauty.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.service.common.ItemService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.BulkChangeTypeNoRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/beauty/baseItem")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return itemService.save(req);
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<JSONObject>> list(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return itemService.list(req);
	}
	
	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return itemService.status(req);
	}
	
	
	@RequestMapping("/bulkChangeTypeNo")
	@ResponseBody
	public BaseResponse bulkChangeTypeNo(@RequestBody BeautyRequest<BulkChangeTypeNoRequest> req) throws Exception {
		return itemService.bulkChangeTypeNo(req);
	}
	
	@RequestMapping("/bulkChangeStatus")
	@ResponseBody
	public BaseResponse bulkChangeStatus(@RequestBody BeautyRequest<BulkChangeStatusRequest> req) throws Exception {
		return itemService.bulkChangeStatus(req);
	}
}
