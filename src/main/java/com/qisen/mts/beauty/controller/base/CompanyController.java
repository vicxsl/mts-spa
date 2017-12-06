package com.qisen.mts.beauty.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.entity.base.Company;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.service.base.CompanyService;

@Controller
@RequestMapping("/beauty/baseCompany")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<Company>> list(@RequestBody BeautyRequest<JSONObject> req) {
		return companyService.list(req);
	}

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<Company> req) throws Exception {
		return companyService.save(req);
	}

	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return companyService.status(req);
	}
}
