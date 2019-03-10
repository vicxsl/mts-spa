package com.qisen.mts.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.util.FileUtil;
import com.qisen.mts.spa.model.request.SpaRequest;
import com.qisen.mts.spa.service.QiniuService;

@Controller
@RequestMapping("/spa/file")
public class FileController {
	@Autowired
    private QiniuService qiniuService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
	public CommObjResponse<JSONObject> uploadImage(@RequestBody SpaRequest<JSONObject> req)throws Exception {
		MultipartFile file=FileUtil.base64ToMultipart(req.getBody().getString("base64data"));
		if(file.isEmpty()) {
			throw new Exception();
        }
		return this.qiniuService.saveImage(file);
	}
}
