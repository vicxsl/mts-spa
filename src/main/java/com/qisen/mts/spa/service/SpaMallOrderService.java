package com.qisen.mts.spa.service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface SpaMallOrderService {

	CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) throws Exception;

	CommObjResponse<List<SpaMallOrder>> list(SpaRequest<SpaMallOrder> req) throws Exception;
	
	void changePayStatus(ServletRequest req,HttpServletResponse response) throws Exception;

	

}
