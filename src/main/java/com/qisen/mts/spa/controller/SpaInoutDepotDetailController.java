package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaInoutDepotDetailService;

@Controller
@RequestMapping("/spa/inoutDepotDetail")
public class SpaInoutDepotDetailController {

	@Autowired
	private SpaInoutDepotDetailService spaInoutDepotDetailService;

	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotDetail>> save(@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.save(req.getBody());
	}

	@RequestMapping("/delete")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotDetail>> delete(@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.delete(req.getBody());
	}

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotDetail>> list(
			@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.list(req.getBody());
	}
	
//	查询出入库明细，通过出入库单号
	@RequestMapping("/queryByInOutNo")
	@ResponseBody
	public CommObjResponse<List<SpaInoutDepotDetail>> queryByInOutNo(
			@RequestBody SpaRequest<SpaInoutDepotDetail> req) throws Exception {
		return spaInoutDepotDetailService.queryByInOutNo(req.getBody());
	}
}
