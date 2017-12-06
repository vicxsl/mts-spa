package com.qisen.mts.beauty.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.sys.AccountService;

@Controller
@RequestMapping("/beauty/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<Account> req) throws Exception {
		return accountService.save(req);
	}

	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return accountService.status(req);
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<Account>> list(@RequestBody PageRequest<JSONObject> req) {
		return accountService.list(req);
	}
	
	@RequestMapping("/rights")
	@ResponseBody
	public BaseResponse rights(@RequestBody BeautyRequest<Account> req) throws Exception {
		return accountService.rights(req);
	}

}
