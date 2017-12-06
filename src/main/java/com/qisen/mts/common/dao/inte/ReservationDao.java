/**
 * 
 */
package com.qisen.mts.common.dao.inte;

import java.util.List;
import java.util.Map;

import com.qisen.mts.common.model.entity.inte.Reservation;

/**
 * @author forbr
 *
 */
public interface ReservationDao {

	/**
	 * 查询最近几天的预约个数
	 * @param body
	 * @return
	 */
	List<Map<String, Object>> queryReservationCount(Reservation body);
	
	/**
	 * 查询门店的预约
	 * @param body
	 * @return
	 */
	List<Reservation> queryReservations(Reservation body);
	
	/**
	 * 查询会员的预约
	 * @param body
	 * body.eid 企业id
	 * body.sid 门店id
	 * body.memID 会员id
	 * @return
	 */
	List<Reservation> memberReservations(Reservation body);

	/**
	 * 查询员工排班
	 * @param body
	 * @return
	 */
	List<Map<String, Object>> queryScheduling(Reservation body);

	/**
	 * 更改预约
	 * @param body
	 * @return
	 */
	int update(Reservation body);

	/**
	 * 根据门店id获取当日预约数
	 * @param body
	 * @return
	 */
	Integer getShopReservationsNum(Reservation body);

	/**
	 * 新增预约
	 * @param body
	 */
	void add(Reservation body);
	
	/**
	 * 检查是否有预约冲突
	 * @param reservation
	 * @return
	 */
	public Integer checkReservationAvailabilty(Reservation reservation);

	/**
	 * 通过ID加载预约记录
	 * @param id
	 * @return
	 */
	Reservation load(Integer id); 
	
	/**
	 * 通过memId加载预约记录
	 * @param memId
	 * @return
	 */
	List<Reservation> fingBymemId(Integer memId);
	
	/**
	 * 通过empId查询员工预约总数
	 * @param integer
	 * @return
	 */
	int empReservationList(Integer empId); 

}
