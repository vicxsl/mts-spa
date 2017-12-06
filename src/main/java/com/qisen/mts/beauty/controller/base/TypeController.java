package com.qisen.mts.beauty.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Type;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.service.base.TypeService;

@Controller
@RequestMapping("/beauty/baseType")
public class TypeController {

	@Autowired
	private TypeService typeService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<Type>> list(@RequestBody BeautyRequest<JSONObject> req) {
		return typeService.list(req);
	}

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<Type> req) throws Exception {
		return typeService.save(req);
	}

	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return typeService.status(req);
	}
}
