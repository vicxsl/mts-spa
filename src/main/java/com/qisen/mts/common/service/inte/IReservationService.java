/**
 * 
 */
package com.qisen.mts.common.service.inte;

import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse; 

/**
 * @author forbr
 *
 */
public interface IReservationService {
	/**
	 * 添加预约
	 * @param reservation
	 * @return
	 */
	public BaseResponse add(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 查询预约
	 * @param reservation
	 * @return
	 */
	public BaseResponse queryReservations(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 查寻员工排班记录
	 * @param reservationVo
	 * @return
	 */
	public BaseResponse queryScheduling(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 取消预约
	 * @param reservationVo
	 * @return
	 */
	public BaseResponse cancel(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 修改预约
	 * @param reservationVo
	 * @return
	 */
	public BaseResponse changeStatus(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 修改预约时间
	 * @param reservation
	 * @return
	 */
	public BaseResponse updateReservation(BaseRequest<Reservation> request)throws Exception;
	
	/**
	 * 根据门店id获取当日预约数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public BaseResponse getShopReservationsNum(BaseRequest<Reservation> request)throws Exception;

	
}
