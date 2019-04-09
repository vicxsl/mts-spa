package com.qisen.mts.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaImg;
import com.qisen.mts.spa.service.QiniuService;
import com.qisen.mts.spa.service.TenXunCosService;

@Controller
@RequestMapping("/spa/file")
public class FileController {
	@Autowired
    private QiniuService qiniuService;
	@Autowired
    private TenXunCosService tenXunCosService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
	public CommObjResponse<SpaImg> uploadImage(MultipartHttpServletRequest  request)throws Exception {
//		腾讯云
//		return tenXunCosService.saveImage(request);
//		七牛云
		return qiniuService.saveImage(request);
		
	}
}
