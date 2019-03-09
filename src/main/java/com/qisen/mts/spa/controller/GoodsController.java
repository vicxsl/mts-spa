package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaImg;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.GoodsService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;


//	查询商品列表接口
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoods>> list(@RequestBody SpaRequest<SpaGoods> req) throws Exception {
		return goodsService.list(req);
	}
	
//	保存商品接口
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaGoods>> save(@RequestBody SpaRequest<SpaGoods> req) throws Exception {
		return goodsService.save(req);
	}
	
//	删除商品接口
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaGoods>> delete(@RequestBody SpaRequest<List<SpaGoods>> req) throws Exception {
		return goodsService.delete(req);
	}
	
	//	查询商品主图、轮播图列表接口
	@RequestMapping("/goodsImgList")
	@ResponseBody
	public CommObjResponse<List<SpaImg>> goodsImgList(@RequestBody SpaRequest<SpaImg> req) throws Exception {
		return goodsService.goodsImgList(req);
	}
	


}

