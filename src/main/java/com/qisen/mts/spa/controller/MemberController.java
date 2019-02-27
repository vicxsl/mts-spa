package com.qisen.mts.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
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

}

