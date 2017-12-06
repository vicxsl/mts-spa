package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.admin.service.DepartAndGTypeService;
import com.qisen.mts.common.model.entity.sys.Department;
import com.qisen.mts.common.model.entity.sys.Gtype;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/admin/departAndGType")
public class DepartAndGTypeController {
	
		@Autowired
		private DepartAndGTypeService departAndGTypeService;

		@RequestMapping("/saveDep")
		@ResponseBody
		public BaseResponse saveDep(@RequestBody AdminRequest<Department> req) throws Exception {
			return departAndGTypeService.saveDep(req);
		}

		@RequestMapping("/editDep")
		@ResponseBody
		public BaseResponse editDep(@RequestBody AdminRequest<Department> req) throws Exception {
			return departAndGTypeService.editDep(req);
		}
		
		@RequestMapping("/deleteDep")
		@ResponseBody
		public BaseResponse deleteDep(@RequestBody AdminRequest<Department> req) throws Exception {
			return departAndGTypeService.deleteDep(req);
		}
		
		@RequestMapping("/saveEmp")
		@ResponseBody
		public BaseResponse saveEmp(@RequestBody AdminRequest<Gtype> req) throws Exception {
			return departAndGTypeService.saveEmp(req);
		}
		
		@RequestMapping("/editEmp")
		@ResponseBody
		public BaseResponse editEmp(@RequestBody AdminRequest<Gtype> req) throws Exception {
			return departAndGTypeService.editEmp(req);
		}
		
		@RequestMapping("/deleteEmp")
		@ResponseBody
		public BaseResponse deleteEmp(@RequestBody AdminRequest<Gtype> req) throws Exception {
			return departAndGTypeService.deleteEmp(req);
		}
		
		@RequestMapping("/list")
		@ResponseBody
		public CommObjResponse<JSONObject> list(@RequestBody BaseRequest<JSONObject> req) {
			return departAndGTypeService.list(req);
		}
		
//		@RequestMapping("/rights")
//		@ResponseBody
//		public BaseResponse rights(@RequestBody AdminRequest<Account> req) throws Exception {
//			return accountService.rights(req);
//		}


}
