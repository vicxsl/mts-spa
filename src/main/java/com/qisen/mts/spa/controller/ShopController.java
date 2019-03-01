package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShop;
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
	
   //查询轮播图urls,按顺序返回
	@RequestMapping("/rotateImg")
	@ResponseBody
	public CommObjResponse<JSONArray> queryRotateImg(@RequestBody SpaRequest<JSONObject> req) throws Exception {
		return shopService.queryRotateImg(req);
	}
	
   //查询查询商品列表
	@RequestMapping("/good/list")
	@ResponseBody
	public CommObjResponse<JSONArray> queryGoodList(@RequestBody SpaRequest<JSONObject> req) throws Exception {
		return shopService.queryGoodList(req);
	}
	
}

