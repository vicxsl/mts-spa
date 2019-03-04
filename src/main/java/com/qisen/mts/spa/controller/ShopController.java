package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.entity.SpaShopsImg;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.ShopService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;


   //通过小程序id查询商户
	@RequestMapping("/spaMall/queryByAppId")
	@ResponseBody
	public CommObjResponse<SpaShop> queryByAppId(@RequestBody SpaRequest<JSONObject> req) throws Exception {
		return shopService.queryByAppId(req);
	}
	
	//查询店铺轮播图集合
	@RequestMapping("/shopsImgList")
	@ResponseBody
	public CommObjResponse<List<SpaShopsImg>> shopsImgList(@RequestBody SpaRequest<SpaShopsImg> req) throws Exception {
		return shopService.shopsImgList(req);
	}
	
}

