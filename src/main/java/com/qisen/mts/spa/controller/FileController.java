package com.qisen.mts.spa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.service.QiniuService;

@Controller
@RequestMapping("/spa/upload")
public class FileController {
	@Autowired
    private QiniuService qiniuService;
	
	@RequestMapping(value = "/photo", method = RequestMethod.POST)
    @ResponseBody
	public CommObjResponse<JSONObject> uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request)throws Exception {
		if(file.isEmpty()) {
			throw new Exception();
        }
		return this.qiniuService.saveImage(file);
	}
}
