package com.qisen.mts.spa.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface QiniuService {
	public CommObjResponse<JSONObject>  saveImage(MultipartHttpServletRequest  request) throws Exception ;
	public String getUpToken() throws Exception;
}
