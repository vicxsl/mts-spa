package com.qisen.mts.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.SessionSpa;
import com.qisen.mts.spa.model.entity.SpaAccount;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaAccountService;

@Controller
@RequestMapping("/spa/account")
public class SpaAccountController {

	@Autowired
	private SpaAccountService spaAccountService;

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody SpaRequest<SpaAccount> req) throws Exception {
		return spaAccountService.save(req);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public CommObjResponse<SessionSpa> login(@RequestBody SpaRequest<SpaAccount> req) throws Exception {
		return spaAccountService.login(req);
	}

}

