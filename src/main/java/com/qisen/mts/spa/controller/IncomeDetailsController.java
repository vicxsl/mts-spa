package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;
import com.qisen.mts.spa.service.IncomeDetailsService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/incomeDetails")
public class IncomeDetailsController {

	@Autowired
	private IncomeDetailsService incomeDetailsService;


	//	查询获利详情表
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<SpaIncomeDetails>> list(@RequestBody PageRequest<SpaIncomeDetails> req) throws Exception {
		return incomeDetailsService.list(req);
	}

}

