package com.qisen.mts.beauty.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.ItemPrice;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.base.ItemPriceService;



@Controller
@RequestMapping("beauty/itemPrice")
public class ItemPriceController {

	@Autowired
	private ItemPriceService itemPriceService;

	@RequestMapping("/save")
	public @ResponseBody BaseResponse save(@RequestBody BeautyRequest<ItemPrice> req) throws Exception {
		return itemPriceService.save(req);
	}
	
	@RequestMapping("/delete")
	public @ResponseBody BaseResponse delete(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return itemPriceService.delete(req);
	}

}
