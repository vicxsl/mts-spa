package com.qisen.mts.spa.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface QiniuService {
	public CommObjResponse<JSONObject>  saveImage(MultipartFile file) throws Exception ;
	public String getUpToken() throws Exception;
}
