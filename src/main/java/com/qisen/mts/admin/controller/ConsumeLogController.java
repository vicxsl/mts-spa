package com.qisen.mts.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.ConsumeLog;
import com.qisen.mts.admin.service.ConsumeLogService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/admin/consumeLog")
public class ConsumeLogController {
	@Autowired
	private ConsumeLogService consumeLogService;
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<JSONObject> list(@RequestBody PageRequest<JSONObject> req)throws Exception{
		return consumeLogService.list(req);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public BaseResponse update(@RequestBody AdminRequest<ConsumeLog> req)throws Exception{
		return consumeLogService.update(req);
	}
	
	@RequestMapping("/updateAuditFlag")
	@ResponseBody
	public BaseResponse updateAuditFlag(@RequestBody AdminRequest<JSONObject> req)throws Exception{
		return consumeLogService.updateAuditFlag(req);
	}
	@RequestMapping("/create")
	@ResponseBody
	public BaseResponse create(@RequestBody AdminRequest<ConsumeLog> req)throws Exception{
		return consumeLogService.create(req);
	}
}
