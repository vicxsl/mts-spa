package com.qisen.mts.beauty.controller.busi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.service.busi.BillService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/beauty/bill")
public class BillController {

	@Autowired
	private BillService billService;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<JSONObject> list(@RequestBody PageRequest<JSONObject> req) throws Exception  {
		return billService.list(req);
	}

	@RequestMapping("/status")
	@ResponseBody 
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return billService.status(req);
	}
	
	@RequestMapping("/recalc")
	@ResponseBody 
	public BaseResponse recalc(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return billService.recalc(req);
	}
}
