package com.qisen.mts.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.admin.service.GoodsService;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<Goods>> list(@RequestBody AdminRequest<Goods> req) {
		return goodsService.List(req);
	}

	@RequestMapping("/create")
	@ResponseBody
	public BaseResponse create(@RequestBody AdminRequest<Goods> req) {
		return goodsService.create(req);
	}

	@RequestMapping("/update")
	@ResponseBody
	public BaseResponse update(@RequestBody AdminRequest<Goods> req) {
		return goodsService.update(req);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse delete(@RequestBody AdminRequest<Goods> req) {
		return goodsService.delete(req);
	}
	
}
