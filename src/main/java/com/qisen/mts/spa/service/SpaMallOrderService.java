package com.qisen.mts.spa.service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface SpaMallOrderService {

	CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) throws Exception;

	PageResponse<List<SpaMallOrder>> list(PageRequest<SpaMallOrder> req) throws Exception;
	
	void changePayStatus(ServletRequest req,HttpServletResponse response) throws Exception;

	

}
