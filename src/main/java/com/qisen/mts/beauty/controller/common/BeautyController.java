package com.qisen.mts.beauty.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.service.common.BeautyService;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
public class BeautyController {

	@Autowired
	private BeautyService beautyService;
	
	@RequestMapping({"/beauty/metadata", "/beauty/metaData"})
	@ResponseBody
	public CommObjResponse<JSONObject> metaData(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return beautyService.metadata(req);
	}
	
	@RequestMapping("/beauty/login")
	@ResponseBody
	public BaseResponse login(@RequestBody BeautyRequest<Account> req) throws Exception {
		return beautyService.login(req);
	}

	@RequestMapping("/beauty/logout")
	@ResponseBody
	public BaseResponse logout(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return beautyService.logout(req);
	}
}
