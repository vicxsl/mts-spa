package com.qisen.mts.myk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.myk.model.SessionMember;
import com.qisen.mts.myk.model.request.MykRequest;
import com.qisen.mts.myk.service.MykService;

@Controller
@RequestMapping("/myk")
public class MykController {
	
	@Autowired
	private MykService mykService;
	
	@RequestMapping("/login")
	@ResponseBody
	public CommObjResponse<SessionMember> login(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return mykService.login(req);
	}
	
	@RequestMapping("/metadata")
	@ResponseBody
	public CommObjResponse<JSONObject> metaData(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return mykService.metadata(req);
	}
}
