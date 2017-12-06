package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.service.EnterpriseService;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/admin/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<JSONObject> init(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return enterpriseService.list(req);
	}
	
	@RequestMapping("/set")
	@ResponseBody
	public BaseResponse set(@RequestBody AdminRequest<Enterprise> req) throws Exception {
		return enterpriseService.set(req);
	}
	
	@RequestMapping("/shop")
	@ResponseBody
	public CommObjResponse<Shop> shopList(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return  enterpriseService.shop(req);
	}
	
	@RequestMapping("/shopValidSet")
	@ResponseBody
	public BaseResponse shopValidSet(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return  enterpriseService.shopValidSet(req);
	}
}
