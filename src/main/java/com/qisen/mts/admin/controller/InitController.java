package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.admin.service.InitService;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.response.BaseResponse;

@Controller
@RequestMapping("/admin/init")
public class InitController {

	@Autowired
	private InitService initService;
	
	@RequestMapping("/enterprise")
	@ResponseBody
	public BaseResponse init(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return initService.createEnterprise(req);
	}
	
	@RequestMapping("/shop")
	@ResponseBody
	public BaseResponse createShop(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return initService.createShop(req);
	}
	
	@RequestMapping("/shopSet")
	@ResponseBody
	public BaseResponse eidtShop(@RequestBody AdminRequest<Shop> req) throws Exception {
		return initService.eidtShop(req);
	}
}
