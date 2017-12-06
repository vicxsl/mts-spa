package com.qisen.mts.myk.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.myk.model.request.MykRequest; 


public interface ReservationEmpService {

	/**
	 * 获取员工预约情况
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CommObjResponse<JSONObject> reservationList(BaseRequest<Reservation> request)throws Exception;

	public CommObjResponse<JSONObject> getReservationTime(MykRequest<Reservation> req);

	public CommObjResponse<JSONObject> addReservation(MykRequest<Reservation> req);

	public CommObjResponse<JSONObject> findMemReservation(MykRequest<JSONObject> req);

	public CommObjResponse<JSONObject> deleteReservation(MykRequest<Reservation> req);

	public CommObjResponse<JSONObject> empReservationList(MykRequest<JSONObject> req);

	public CommObjResponse<List<Emp>> empList(MykRequest<JSONObject> req);
}
