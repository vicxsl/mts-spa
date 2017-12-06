package com.qisen.mts.myk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.myk.model.request.MykRequest;
import com.qisen.mts.myk.service.ReservationEmpService;

@Controller
@RequestMapping("/myk/baseEmp")
public class ReservationEmpController {
	
	@Autowired
	private ReservationEmpService reservationEmpService;
	
	@RequestMapping("/reservationList")
	@ResponseBody
	public CommObjResponse<JSONObject> reservationList(@RequestBody MykRequest<Reservation> req) throws Exception {
		return reservationEmpService.reservationList(req);
	}
	
	/**
	 * 查询门店可预约时间段
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getReservationTime")
	@ResponseBody
	public CommObjResponse<JSONObject> getReservationTime(@RequestBody MykRequest<Reservation> req) throws Exception {
		return reservationEmpService.getReservationTime(req);
	}
	
	/**
	 * 新增预约
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addReservation")
	@ResponseBody
	public CommObjResponse<JSONObject> addReservation(@RequestBody MykRequest<Reservation> req) throws Exception {
		return reservationEmpService.addReservation(req);
	}
	
	/**
	 * 新增预约
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findMemReservation")
	@ResponseBody
	public CommObjResponse<JSONObject> findMemReservation(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return reservationEmpService.findMemReservation(req);
	}
	
	/**
	 * 删除预约
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteReservation")
	@ResponseBody
	public CommObjResponse<JSONObject> deleteReservation(@RequestBody MykRequest<Reservation> req) throws Exception {
		return reservationEmpService.deleteReservation(req);
	}
	
	/**
	 * 查询员工预约的总数
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/empReservationList")
	@ResponseBody
	public CommObjResponse<JSONObject> empReservationList(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return reservationEmpService.empReservationList(req);
	}
	
	/**
	 * 查询门店员工
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/empList")
	@ResponseBody
	public CommObjResponse<List<Emp>> empList(@RequestBody MykRequest<JSONObject> req) throws Exception {
		return reservationEmpService.empList(req);
	}
}
