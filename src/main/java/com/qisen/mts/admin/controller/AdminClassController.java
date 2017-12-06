package com.qisen.mts.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.OutlayClass;
import com.qisen.mts.admin.model.entity.ProductsClass;
import com.qisen.mts.admin.service.AdminClassService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
@Controller
@RequestMapping("/admin/class")
public class AdminClassController {
	@Autowired
	private AdminClassService adminClassService;
	/**
	 * 产品类型增删改查
	 */
	@RequestMapping("/prodClass/list")
	@ResponseBody
	public CommObjResponse<List<ProductsClass>> prodClassList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.prodClassList(req);
	}
	@RequestMapping("/prodClass/create")
	@ResponseBody
	public BaseResponse prodClassCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.prodClassCreate(req);
	}
	@RequestMapping("/prodClass/delete")
	@ResponseBody
	public BaseResponse prodClassDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.prodClassDelete(req);
	}
	@RequestMapping("/prodClass/update")
	@ResponseBody
	public BaseResponse prodClassUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.prodClassUpdate(req);
	}
	/**
	 * 支出类型增删改查
	 */
	@RequestMapping("/outlayClass/list")
	@ResponseBody
	public CommObjResponse<List<OutlayClass>> outlayClassList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.outlayClassList(req);
	}
	@RequestMapping("/outlayClass/create")
	@ResponseBody
	public BaseResponse outlayClassCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.outlayClassCreate(req);
	}
	@RequestMapping("/outlayClass/delete")
	@ResponseBody
	public BaseResponse outlayClassDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.outlayClassDelete(req);
	}
	@RequestMapping("/outlayClass/update")
	@ResponseBody
	public BaseResponse outlayClassUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.outlayClassUpdate(req);
	}
	/**
	 * 片区增删改查
	 */
	@RequestMapping("/organ/list")
	@ResponseBody
	public CommObjResponse<JSONObject> organList(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.organList(req);
	}
	@RequestMapping("/organ/create")
	@ResponseBody
	public BaseResponse organCreate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.organCreate(req);
	}
	@RequestMapping("/organ/delete")
	@ResponseBody
	public BaseResponse organDelete(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.organDelete(req);
	}
	@RequestMapping("/organ/update")
	@ResponseBody
	public BaseResponse organUpdate(@RequestBody AdminRequest<JSONObject> req) throws Exception {
		return this.adminClassService.organUpdate(req);
	}
	
}
