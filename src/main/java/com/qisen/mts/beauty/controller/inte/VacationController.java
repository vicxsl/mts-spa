/**
 * 
 */
package com.qisen.mts.beauty.controller.inte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qisen.mts.common.model.entity.inte.Vacation;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.inte.IVacationService;

/**
 * @author forbr
 *
 */
@RestController
@RequestMapping("/beauty/inte/vacation")
public class VacationController {
	@Autowired
	private IVacationService requestService;
	
	/**
	 * 新增员工休假
	 */
	@RequestMapping("/add")
	public @ResponseBody BaseResponse add(@RequestBody BeautyRequest<Vacation> request)throws Exception{
		return this.requestService.add(request);
	}
	
	/**
	 * 删除员工休假
	 */
	@RequestMapping("/delete")
	public @ResponseBody BaseResponse delete(@RequestBody BeautyRequest<Vacation> request)throws Exception{
		return this.requestService.delete(request);
	}
	
	/**
	 * 查询员工休假
	 */
	@RequestMapping("/list")
	public @ResponseBody BaseResponse queryByDay(@RequestBody BeautyRequest<Vacation> request)throws Exception{
		return this.requestService.queryByDay(request);
	}
}
