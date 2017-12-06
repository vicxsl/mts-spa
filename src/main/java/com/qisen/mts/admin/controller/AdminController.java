package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.SessionAdmin;
import com.qisen.mts.admin.model.entity.Admin;
import com.qisen.mts.admin.service.AdminService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<JSONObject> list(@RequestBody AdminRequest<JSONObject> req) {
		return adminService.list(req);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody AdminRequest<Admin> req) {
		return adminService.save(req);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse delete(@RequestBody AdminRequest<Admin> req)  throws Exception{
		return adminService.delete(req);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public BaseResponse edit(@RequestBody AdminRequest<Admin> req) {
		return adminService.edit(req);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public CommObjResponse<SessionAdmin> login(@RequestBody AdminRequest<Admin> req) throws Exception {
		return adminService.login(req);
	}
}

