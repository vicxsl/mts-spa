package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaGoodsCompanyService;

@Controller
@RequestMapping("/spa/goodsCompany")
public class SpaGoodsCompanyController {
	
	@Autowired
	private SpaGoodsCompanyService spaGoodsCompanyService;
	
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsCompany>> save(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.save(req);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsCompany>> delete(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.delete(req);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsCompany>> list(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.list(req);
	}
	
}
