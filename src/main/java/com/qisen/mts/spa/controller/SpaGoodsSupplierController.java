package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoods;
import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaGoodsSupplierService;

@Controller
@RequestMapping("/spa/goodsSupplier")
public class SpaGoodsSupplierController {
	
	@Autowired
	private SpaGoodsSupplierService spaGoodsSupplierService;
	
	@RequestMapping("/saveGoodsSubType")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.insert(req.getBody());
	}
	
	@RequestMapping("/editGoodsSubType")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.updateByPrimaryKey(req.getBody());
	}
	
	@RequestMapping("/delGoodsSubType")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.deleteByPrimaryKey(req.getBody().getId());
	}
	
	@RequestMapping("/getGoodsSubTypeById")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSupplier>> selectSuppliers(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.selectSuppliers(req.getBody());
	}

}
