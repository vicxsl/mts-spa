package com.qisen.mts.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Province;
import com.qisen.mts.admin.service.AdminAreaService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/admin/area")
public class AdminAreaController {
	@Autowired
	private AdminAreaService adminAreaService;
	/**
	 * 省份增删改查
	 */
	@RequestMapping("/province/list")
	@ResponseBody
	public CommObjResponse<List<Province>> prodClassList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.provinceList(req);
	}
	@RequestMapping("/province/create")
	@ResponseBody
	public BaseResponse prodClassCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.provinceCreate(req);
	}
	@RequestMapping("/province/delete")
	@ResponseBody
	public BaseResponse prodClassDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.provinceDelete(req);
	}
	@RequestMapping("/province/update")
	@ResponseBody
	public BaseResponse prodClassUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.provinceUpdate(req);
	}
	/**
	 * 城市增删改查
	 */
	@RequestMapping("/city/list")
	@ResponseBody
	public CommObjResponse<JSONObject> outlayClassList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.cityList(req);
	}
	@RequestMapping("/city/create")
	@ResponseBody
	public BaseResponse outlayClassCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.cityCreate(req);
	}
	@RequestMapping("/city/delete")
	@ResponseBody
	public BaseResponse outlayClassDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.cityDelete(req);
	}
	@RequestMapping("/city/update")
	@ResponseBody
	public BaseResponse outlayClassUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminAreaService.cityUpdate(req);
	}

}
