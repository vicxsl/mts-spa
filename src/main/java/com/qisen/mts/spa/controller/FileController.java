package com.qisen.mts.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.service.QiniuService;

@Controller
@RequestMapping("/spa/file")
public class FileController {
	@Autowired
    private QiniuService qiniuService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
	public CommObjResponse<JSONObject> uploadImage(MultipartHttpServletRequest  request)throws Exception {
		return this.qiniuService.saveImage(request);
	}
}
