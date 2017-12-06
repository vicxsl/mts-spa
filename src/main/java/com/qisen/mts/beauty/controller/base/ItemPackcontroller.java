package com.qisen.mts.beauty.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.base.ItemPackService;
@Controller
@RequestMapping("/beauty/itemPack")
public class ItemPackcontroller {
	@Autowired
	ItemPackService itemPackService;
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<JSONObject>> list(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return itemPackService.list(req);
	}
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResponse edit(@RequestBody  BeautyRequest<JSONObject> req) throws Exception {
		return itemPackService.edit(req);
	}
	@RequestMapping("/editDetail")
	@ResponseBody
	public BaseResponse editDetail(@RequestBody  BeautyRequest<JSONObject> req) throws Exception {
		return itemPackService.editDetail(req);
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public BaseResponse updateStatus(@RequestBody  BeautyRequest<JSONObject> req) throws Exception {
		return itemPackService.updateStatus(req);
	}
	
	
}
