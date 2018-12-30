package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaGoodsSubTypeService;

@Controller
@RequestMapping("/spa/goodsSubType")
public class SpaGoodsSubTypeController {
	
	@Autowired
	private SpaGoodsSubTypeService spaGoodsSubTypeService;
	
	@RequestMapping("/saveGoodsSubType")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return spaGoodsSubTypeService.insert(req.getBody());
	}
	
	@RequestMapping("/editGoodsSubType")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return spaGoodsSubTypeService.updateByPrimaryKey(req.getBody());
	}
	
	@RequestMapping("/delGoodsSubType")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return spaGoodsSubTypeService.deleteByPrimaryKey(req.getBody().getId());
	}
	
	@RequestMapping("/getGoodsSubTys")
	@ResponseBody
	public CommObjResponse<List<SpaGoodsSubType>> selectGoodsSubTys(@RequestBody SpaRequest<SpaGoodsSubType> req) throws Exception {
		return spaGoodsSubTypeService.selectGoodsSubTys(req.getBody());
	}

}
