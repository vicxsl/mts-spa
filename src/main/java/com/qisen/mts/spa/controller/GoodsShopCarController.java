package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.GoodsShopCarService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/goodsShopCar")
public class GoodsShopCarController {

	@Autowired
	private GoodsShopCarService goodsShopCarService;


//	查询商品列表接口
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsShopCar>> list(@RequestBody SpaRequest<SpaGoodsShopCar> req) throws Exception {
		return goodsShopCarService.list(req);
	}
	
//	保存商品接口
	@RequestMapping("/edit")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsShopCar>> edit(@RequestBody SpaRequest<SpaGoodsShopCar> req) throws Exception {
		return goodsShopCarService.edit(req);
	}

}

