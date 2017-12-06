package com.qisen.mts.beauty.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.base.EmpService;

@Controller
@RequestMapping("/beauty/baseEmp")
public class EmpController {
	@Autowired
	private EmpService empservice;

	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<JSONObject> list(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return empservice.list(req);
	}
	@RequestMapping("/edit")
	@ResponseBody
	public BaseResponse edit(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return empservice.edit(req);
	}
	@RequestMapping("/updateStatus")
	@ResponseBody
	public BaseResponse updateStatus(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return empservice.updateStatus(req);
	}
	
}
