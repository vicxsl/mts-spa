package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepot;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotService;

@Controller
@RequestMapping("/spa/inoutDepot")
public class SpaInoutDepotController {
	
	@Autowired
	private SpaInoutDepotService spaInoutDepotService;
	
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepot>> save(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.save(req.getBody());
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepot>> delete(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.delete(req.getBody());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepot>> list(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.list(req.getBody());
	}
	
	@RequestMapping("/getWithDetail")
	@ResponseBody
	public CommObjResponse<SpaInoutDepot> getWithDetail(@RequestBody SpaRequest<SpaInoutDepot> req) throws Exception {
		return spaInoutDepotService.getWithDetail(req.getBody());
	}

}
