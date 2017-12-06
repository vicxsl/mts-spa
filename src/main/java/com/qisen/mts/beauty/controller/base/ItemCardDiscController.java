package com.qisen.mts.beauty.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.base.RuleItemCardDiscService;

@Controller
@RequestMapping("beauty/itemCardDisc")
public class ItemCardDiscController {

	@Autowired
	private RuleItemCardDiscService itemCardDiscService;

	@RequestMapping("/save")
	public @ResponseBody
	BaseResponse save(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return itemCardDiscService.save(req);
	}

	@RequestMapping("/delete")
	public @ResponseBody
	BaseResponse delete(@RequestBody BeautyRequest<JSONObject> req) throws Exception  {
		return itemCardDiscService.delete(req);
	}

}
