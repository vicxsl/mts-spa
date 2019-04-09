package com.qisen.mts.spa.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaImg;

public interface QiniuService {
	public CommObjResponse<SpaImg>  saveImage(MultipartHttpServletRequest  request) throws Exception ;
}
