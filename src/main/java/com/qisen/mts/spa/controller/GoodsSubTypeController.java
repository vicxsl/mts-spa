package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.GoodsSubTypeService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/goodsSubType")
public class GoodsSubTypeController {

	@Autowired
	private GoodsSubTypeService goodsSubTypeService;


//	查询商品列表接口
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSubType>> list(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return goodsSubTypeService.list(req);
	}
	
//	保存商品接口
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSubType>> save(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return goodsSubTypeService.save(req);
	}
	
//	删除商品接口
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSubType>> delete(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return goodsSubTypeService.delete(req);
	}
	


}

