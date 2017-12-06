package com.qisen.mts.beauty.controller.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.beauty.service.rule.RuleEmpAchiService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/beauty/rule")
public class RuleEmpAchiController {

	@Autowired
	private RuleEmpAchiService empAchiService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<RuleEmpAchi>> list(@RequestBody BeautyRequest<JSONObject> req) {
		return empAchiService.list(req);
	}

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<RuleEmpAchi> req) {
		return empAchiService.save(req);
	}

	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) {
		return empAchiService.status(req);
	}

	@RequestMapping("/sort")
	@ResponseBody
	public BaseResponse sort(@RequestBody BeautyRequest<JSONObject> req) {
		return empAchiService.update4Sort(req);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse delete(@RequestBody BeautyRequest<JSONObject> req) {
		return empAchiService.delete(req);
	}
	
	@RequestMapping("/copyRule")
	@ResponseBody
	public BaseResponse copyRule(@RequestBody BeautyRequest<JSONObject> req) {
		return empAchiService.copyRule(req);
	}
}
