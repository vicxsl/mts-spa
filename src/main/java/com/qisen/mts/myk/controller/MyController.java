package com.qisen.mts.myk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.common.service.inte.IReservationService;
import com.qisen.mts.common.service.mem.MemberService;
import com.qisen.mts.myk.model.request.MykRequest;
import com.qisen.mts.myk.service.MyService;

@Controller
@RequestMapping("/myk")
public class MyController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MyService myService;
	
	@Autowired
	private IReservationService reservationService;
	
	//我的会员卡
	@RequestMapping("/myCards")
	@ResponseBody
	public BaseResponse myCards(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return myService.myCards(req);
	}
	//我的疗程
	@RequestMapping("/myItems")
	@ResponseBody
	public BaseResponse myItems(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return myService.myItems(req);
	}
	//我的消费记录
	@RequestMapping("/myRecords")
	@ResponseBody
	public PageResponse<List<JSONObject>> myRecords(@RequestBody PageRequest<JSONObject> req) throws Exception {
		
		return memberService.cosumeDetail(req);
	}

	//我的预约
	@RequestMapping("/myReserv")
	@ResponseBody
	public BaseResponse myReserv(@RequestBody BaseRequest<Reservation> req) throws Exception {
		return myService.memberReservations(req);
	}
	
	//取消预约
	@RequestMapping("/cancelReserv")
	@ResponseBody
	public  BaseResponse cacel(@RequestBody BeautyRequest<Reservation> req) throws Exception {
		req.getBody().setStatus(2);
		return this.reservationService.cancel(req);
	}

}
