package com.qisen.mts.spa.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.SpaMallOrderService;

@Controller
@RequestMapping("/spa/mallOrder")
public class SpaMallOrderController {
	
	@Autowired
	private SpaMallOrderService spaMallOrderService;
	
	@RequestMapping("/save")
	@ResponseBody
	public CommObjResponse<SpaMallOrder> save(@RequestBody SpaRequest<SpaMallOrder> req) throws Exception {
		return spaMallOrderService.save(req);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<SpaMallOrder>> list(@RequestBody PageRequest<SpaMallOrder> req) throws Exception {
		return spaMallOrderService.list(req);
	}

	@RequestMapping("/changePayStatus")
//	@ResponseBody
	public void  changePayStatus(ServletRequest req,HttpServletResponse response) throws Exception {
		spaMallOrderService.changePayStatus(req, response);
	}
}
