package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotTypeService;

@Controller
@RequestMapping("/spa/inoutDepotType")
public class SpaInoutDepotTypeController {
	
	@Autowired
	private SpaInoutDepotTypeService spaInoutDepotTypeService;
	
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotType>> save(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.save(req.getBody());
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotType>> delete(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.delete(req.getBody());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotType>> list(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.list(req.getBody());
	}

}
