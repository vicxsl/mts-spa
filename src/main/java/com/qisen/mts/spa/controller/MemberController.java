package com.qisen.mts.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaMyInfoGains;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.MemberService;

/*
 * auth chali
 */
@Controller
@RequestMapping("/spa/member")
public class MemberController {

	@Autowired
	private MemberService memberService;


//	商城登录
	@RequestMapping("/login")
	@ResponseBody
	public CommObjResponse<MetaData> list(@RequestBody SpaRequest<SpaMember> req) throws Exception {
		return memberService.login(req);
	}
	
	@RequestMapping("/memberIncomeDetailsList")
	@ResponseBody
	public CommObjResponse<List<SpaMember>> memberIncomeDetailsList(@RequestBody SpaRequest<SpaMember> req) throws Exception {
		return memberService.memberIncomeDetailsList(req);
	}
	/**
	 * 小程序我的页面 4个数据
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myInfoGains")
	@ResponseBody
	public CommObjResponse<SpaMyInfoGains> myInfoGains(@RequestBody SpaRequest<SpaMyInfoGains> req) throws Exception {
		return memberService.myInfoGains(req);
	}
	
	/**
	 * 更新用户手机号码
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addMobile")
	@ResponseBody
	public CommObjResponse<String> addMobile(@RequestBody SpaRequest<JSONObject> req) throws Exception {
		return memberService.addMobile(req);
	}
}

