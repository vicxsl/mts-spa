package com.qisen.mts.myk.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;

public interface MyService {
	
	public BaseResponse myCards(BaseRequest<JSONObject> req);

	public BaseResponse myItems(BaseRequest<JSONObject> req);
	
	/**
	 * 查询会员的预约
	 * @param request
	 * @return
	 */
	public BaseResponse memberReservations(BaseRequest<Reservation> request);
}
