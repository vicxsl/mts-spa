package com.qisen.mts.beauty.controller.busi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.service.busi.CashService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;

@Controller
@RequestMapping("/beauty/cash")
public class CashController {
	
	@Autowired
	private CashService cashService;
	
	@RequestMapping("/doCash")
	@ResponseBody
	public BaseResponse doCash(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return cashService.doCash(req);
	}
	
	@RequestMapping("/checkBillNo")
	@ResponseBody
	public BaseResponse checkBillNo(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return cashService.checkBillNo(req);
	}
	
	@RequestMapping("/checkCardNo")
	@ResponseBody
	public BaseResponse checkCardNo(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return cashService.checkCardNo(req);
	}
}
