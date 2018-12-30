package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotTypeService;

@Controller
@RequestMapping("/spa/inoutDepotType")
public class SpaInoutDepotTypeController {
	
	@Autowired
	private SpaInoutDepotTypeService spaInoutDepotTypeService;
	
	@RequestMapping("/saveInoutDepotType")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.insert(req.getBody());
	}
	
	@RequestMapping("/editInoutDepotType")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.updateByPrimaryKey(req.getBody());
	}
	
	@RequestMapping("/delInoutDepotType")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.deleteByPrimaryKey(req.getBody().getId());
	}
	
	@RequestMapping("/getInoutDepotTypes")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotType>> selectDepotTypes(@RequestBody SpaRequest<SpaInoutDepotType> req) throws Exception {
		return spaInoutDepotTypeService.selectDepotTypes(req.getBody());
	}

}
