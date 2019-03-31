package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.GetMoney;
import com.qisen.mts.spa.service.GetMoneyService;

/*
 * auth chali 提现
 */
@Controller
@RequestMapping("/spa/getMoney")
public class getMoneyController {

	@Autowired
	private GetMoneyService getMoneyService;


	//	提现列表
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<GetMoney>> list(@RequestBody PageRequest<GetMoney> req) throws Exception {
		return getMoneyService.list(req);
	}

//	新增提现记录
	@RequestMapping("/create")
	@ResponseBody
	public PageResponse<List<GetMoney>> create(@RequestBody PageRequest<GetMoney> req) throws Exception {
		return getMoneyService.create(req);
	}
}

