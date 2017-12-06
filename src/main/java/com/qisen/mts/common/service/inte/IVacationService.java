/**
 * 
 */
package com.qisen.mts.common.service.inte;

import com.qisen.mts.common.model.entity.inte.Vacation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;

/**
 * @author forbr
 *
 */
public interface IVacationService {

	/**
	 * 新增员工休假
	 * @param request
	 * @return
	 */
	BaseResponse add(BaseRequest<Vacation> request)throws Exception;

	/**
	 * 删除员工休假
	 * @param request
	 * @return
	 */
	BaseResponse delete(BaseRequest<Vacation> request)throws Exception;

	/**
	 * 查询员工休假
	 * @param request
	 * @return
	 */
	BaseResponse queryByDay(BaseRequest<Vacation> request)throws Exception;

}
