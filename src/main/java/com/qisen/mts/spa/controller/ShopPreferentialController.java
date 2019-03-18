package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaShopPreferential;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.ShopPreferentialService;

/*
 * auth chali店铺优惠
 */
@Controller
@RequestMapping("/spa/shopPreferential")
public class ShopPreferentialController {

	@Autowired
	private ShopPreferentialService shopPreferentialService;
	
	// 通过企业eid、小程序id查询商户
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaShopPreferential>> list(@RequestBody SpaRequest<SpaShopPreferential> req) throws Exception {
		return shopPreferentialService.list(req);
	}

	// 编辑商户
	@RequestMapping("/edit")
	@ResponseBody
	public CommObjResponse<List<SpaShopPreferential>> edit(@RequestBody SpaRequest<SpaShopPreferential> req) throws Exception {
		return shopPreferentialService.edit(req);
	}

	// 查询店铺轮播图集合
	@RequestMapping("/deleteList")
	@ResponseBody
	public CommObjResponse<List<SpaShopPreferential>> deleteList(@RequestBody SpaRequest<List<SpaShopPreferential>> req) throws Exception {
		return shopPreferentialService.deleteList(req);
	}

}
