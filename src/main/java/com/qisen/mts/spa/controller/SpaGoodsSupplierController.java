package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSupplier;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaGoodsSupplierService;

@Controller
@RequestMapping("/spa/goodsSupplier")
public class SpaGoodsSupplierController {
	
	@Autowired
	private SpaGoodsSupplierService spaGoodsSupplierService;
	
	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.save(req.getBody());
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.edit(req.getBody());
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse delete(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.delete(req.getBody().getId());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSupplier>> list(@RequestBody SpaRequest<SpaGoodsSupplier> req) throws Exception {
		return spaGoodsSupplierService.list(req.getBody());
	}

}
