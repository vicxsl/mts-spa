package com.qisen.mts.beauty.controller.mem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.mem.MemberService;

@Controller
@RequestMapping("/beauty/member")
public class MemberController {
	@Autowired
	MemberService memberService;

	@RequestMapping("/list")
	@ResponseBody
	public PageResponse<List<Member>> list(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return this.memberService.list(req);
	}

	@RequestMapping("/updateStatus")
	@ResponseBody
	public BaseResponse updateStatus(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return this.memberService.updateStatus(req);
	}

	@RequestMapping("/eidt")
	@ResponseBody
	public BaseResponse eidt(@RequestBody BeautyRequest<Member> req) throws Exception {
		return this.memberService.edit(req);
	}

	@RequestMapping("/cardAndItem")
	@ResponseBody
	public CommObjResponse<JSONObject> cardAndItem(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return this.memberService.cardAndItem(req);
	}
	
	@RequestMapping("/cosumeDetail")
	@ResponseBody
	public PageResponse<List<JSONObject>> cosumeDetail(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return this.memberService.cosumeDetail(req);
	}
	
	@RequestMapping("/memCardRecord")
	@ResponseBody
	public PageResponse<JSONObject> memCardRecord(@RequestBody PageRequest<JSONObject> req) throws Exception {
		return this.memberService.memCardRecord(req);
	}
	

}
