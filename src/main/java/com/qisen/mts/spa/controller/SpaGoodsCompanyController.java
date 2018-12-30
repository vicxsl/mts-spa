package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsCompany;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaGoodsCompanyService;

@Controller
@RequestMapping("/spa/goodsCompany")
public class SpaGoodsCompanyController {
	
	@Autowired
	private SpaGoodsCompanyService spaGoodsCompanyService;
	
	@RequestMapping("/saveGoodsCompany")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.insert(req.getBody());
	}
	
	@RequestMapping("/editGoodsCompany")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.updateByPrimaryKey(req.getBody());
	}
	
	@RequestMapping("/delGoodsCompany")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.deleteByPrimaryKey(req.getBody().getId());
	}
	
	@RequestMapping("/getGoodsCompanys")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsCompany>> selectGoodsCompanys(@RequestBody SpaRequest<SpaGoodsCompany> req) throws Exception {
		return spaGoodsCompanyService.selectGoodsCompanys(req.getBody());
	}
	
}
