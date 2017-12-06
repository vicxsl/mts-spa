package com.qisen.mts.beauty.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.service.sys.ShopSettingService;

@Controller
@RequestMapping("/beauty/shopSetting")
public class ShopSettingController {

	@Autowired
	private ShopSettingService shopSettingService;
	
	@RequestMapping("/save")	
	public @ResponseBody BaseResponse save(@RequestBody BeautyRequest<JSONArray> req) {
		return shopSettingService.save(req);
	}
	
	@RequestMapping("/list")
	public @ResponseBody CommObjResponse<List<ShopSetting>> listEnterprise(@RequestBody BeautyRequest<JSONObject> req ) {
		return shopSettingService.list(req);
	}
	
}
