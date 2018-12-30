package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotDetailService;

@Controller
@RequestMapping("/spa/inoutDepotDetail")
public class SpaInoutDepotDetailController {

	@Autowired
	private SpaInoutDepotDetailService spaInoutDepotDetailService;

	@RequestMapping("/saveInoutDepotDetail")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.insert(req.getBody());
	}

	@RequestMapping("/editInoutDepotDetail")
	@ResponseBody
	public BaseResponse edit(@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.updateByPrimaryKey(req.getBody());
	}

	@RequestMapping("/delInoutDepotDetail")
	@ResponseBody
	public BaseResponse del(@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.deleteByPrimaryKey(req.getBody().getId());
	}

	@RequestMapping("/getInoutDepotDetails")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotDetail>> selectInoutDepotDetails(
			@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.selectInoutDepotDetails(req.getBody());
	}

}
