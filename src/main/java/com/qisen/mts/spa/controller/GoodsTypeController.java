package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsType;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.GoodsTypeService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/goodsType")
public class GoodsTypeController {

	@Autowired
	private GoodsTypeService goodsTypeService;


//	查询商品列表接口
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsType>> list(@RequestBody SpaRequest<SpaGoodsType> req) throws Exception {
		return goodsTypeService.list(req);
	}
	
//	保存商品接口
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsType>> save(@RequestBody SpaRequest<SpaGoodsType> req) throws Exception {
		return goodsTypeService.save(req);
	}
	
//	删除商品接口
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsType>> delete(@RequestBody SpaRequest<SpaGoodsType> req) throws Exception {
		return goodsTypeService.delete(req);
	}
	


}

