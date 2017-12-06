package com.qisen.mts.beauty.controller.busi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.busi.Daily;
import com.qisen.mts.beauty.service.busi.DailyService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Controller
@RequestMapping("/beauty/daily")
public class DailyController {

	@Autowired
	private DailyService dailyService;
	
	@RequestMapping("/create")
	public @ResponseBody BaseResponse create(@RequestBody BeautyRequest<Daily> req) throws Exception  {
		return dailyService.create(req);
	}

	@RequestMapping("/update")	
	public @ResponseBody BaseResponse update(@RequestBody BeautyRequest<JSONObject> req) throws Exception  {
		return dailyService.update(req);
	}
	
	@RequestMapping("/list")	
	public @ResponseBody PageResponse<JSONObject> list(@RequestBody PageRequest<JSONObject> req) throws Exception  {
		return dailyService.list(req);
	}
	
}
