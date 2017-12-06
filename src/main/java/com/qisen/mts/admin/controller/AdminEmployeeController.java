package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.service.AdminEmployeeService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/admin/employee")
public class AdminEmployeeController {
	@Autowired
	private AdminEmployeeService adminEmployeeService;
	
	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<JSONObject> prodClassList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminEmployeeService.list(req);
	}
	@RequestMapping("/create")
	@ResponseBody
	public BaseResponse prodClassCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminEmployeeService.create(req);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse prodClassDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminEmployeeService.delete(req);
	}
	@RequestMapping("/update")
	@ResponseBody
	public BaseResponse prodClassUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminEmployeeService.update(req);
	}
}
