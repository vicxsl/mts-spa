package com.qisen.mts.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Outlay;
import com.qisen.mts.admin.service.OutlayService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/admin/outlay")
public class OutlayController {

	@Autowired
	private OutlayService outlayService;

	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<Outlay>> list(@RequestBody PageRequest<JSONObject> req) {
		return outlayService.List(req);
	}

	@RequestMapping("/create")
	@ResponseBody
	public BaseResponse create(@RequestBody AdminRequest<Outlay> req) {
		return outlayService.create(req);
	}

	@RequestMapping("/update")
	@ResponseBody
	public BaseResponse update(@RequestBody AdminRequest<Outlay> req) {
		return outlayService.update(req);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse delete(@RequestBody AdminRequest<Outlay> req) {
		return outlayService.delete(req);
	}

}
