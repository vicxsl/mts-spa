package com.qisen.mts.beauty.controller.inte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.inte.IReservationService;

@RequestMapping("/beauty/inte/reservation")
@RestController
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	
	/**
	 * 新增预约
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/add")
	public  BaseResponse addReservation(@RequestBody BeautyRequest<Reservation> request) throws Exception{
		return reservationService.add(request);
	}
	
	/**
	 * 获取预约列表
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public  BaseResponse queryReservations(@RequestBody BeautyRequest<Reservation> request) throws Exception{
		return reservationService.queryReservations(request);
	}
	
	/**
	 * 获取预约列表
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/scheduling")
	public  BaseResponse findScheduling(@RequestBody BeautyRequest<Reservation> request) throws Exception {
		return this.reservationService.queryScheduling(request);
	}
	
	/**
	 * 取消预约
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cancel")
	public  BaseResponse cacel(@RequestBody BeautyRequest<Reservation> request) throws Exception {
		request.getBody().setStatus(2);
		return this.reservationService.cancel(request);
	}
	
	/**
	 * 修改预约时间
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateTime")
	public  BaseResponse updateTime(@RequestBody BeautyRequest<Reservation> request) throws Exception {
		return this.reservationService.updateReservation(request);
	}
	
	/**
	 * 修改备注
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateComment")
	
	public BaseResponse updateCommon(@RequestBody BeautyRequest<Reservation> request) throws Exception{
		return this.reservationService.updateReservation(request);
	}
	
	/**
	 * 更改预约状态
	 * @param reservation
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/changeStatus")
	public  BaseResponse changeStatus(@RequestBody BeautyRequest<Reservation> request) throws Exception {
		return this.reservationService.updateReservation(request);
	}
	
	/**
	 * 根据门店id获取当日预约数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getShopReservationsNum")
	public  BaseResponse getShopReservationsNum(@RequestBody BeautyRequest<Reservation> request) throws Exception{
		return this.reservationService.getShopReservationsNum(request);
	}
}
