/**
 * 
 */
package com.qisen.mts.common.dao.inte;

import java.util.List;

import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.entity.inte.Vacation;

/**
 * @author forbr
 *
 */
public interface VacationDao {
	/**
	 * 新增休假
	 * @param vacation
	 */
	public void add(Vacation vacation);
	
	/**
	 * 删除休假
	 * @param vacation
	 */
	public void delete(Vacation vacation);
	
	/**
	 * 按天查询休假
	 * @param vacation
	 */
	public List<Vacation> queryByDay(Vacation vacation);
	
	/**
	 * 按月查询休假
	 * @param vacation
	 */
	public List<Vacation> queryByMonth(Vacation vacation);
	
	/**
	 * 检查是否有休假冲突
	 * @param reservation
	 * @return
	 */
	public Integer checkVacationAvailabilty(Reservation reservation); 
	

}
