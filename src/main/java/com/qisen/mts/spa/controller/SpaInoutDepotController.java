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
import com.qisen.mts.spa.model.entity.SpaInoutDepot;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotService;

@Controller
@RequestMapping("/spa/inoutDepot")
public class SpaInoutDepotController {
	
	@Autowired
	private SpaInoutDepotService spaInoutDepotService;
	
	@RequestMapping("/saveInoutDepot")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.insert(req.getBody());
	}
	
	@RequestMapping("/editInoutDepot")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.updateByPrimaryKey(req.getBody());
	}
	
	@RequestMapping("/delInoutDepot")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.deleteByPrimaryKey(req.getBody().getId());
	}
	
	@RequestMapping("/getInoutDepots")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepot>> selectInoutDepots(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.selectInoutDepots(req.getBody());
	}

}
