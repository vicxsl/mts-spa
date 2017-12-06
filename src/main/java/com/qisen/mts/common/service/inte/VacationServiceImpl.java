/**
 * 
 */
package com.qisen.mts.common.service.inte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.inte.VacationDao;
import com.qisen.mts.common.model.entity.inte.Vacation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

/**
 * @author forbr
 *
 */
@Service
public class VacationServiceImpl implements IVacationService {
	
	@Autowired
	private VacationDao vacationDao;

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IVacationService#add(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse add(BaseRequest<Vacation> request) throws Exception {
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		this.vacationDao.add(request.getBody());
		return new CommObjResponse<Vacation>(request.getBody());
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IVacationService#delete(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse delete(BaseRequest<Vacation> request) throws Exception {
		this.vacationDao.delete(request.getBody());
		return new BaseResponse();
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IVacationService#queryByDay(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse queryByDay(BaseRequest<Vacation> request) throws Exception {
		// TODO Auto-generated method stub
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		CommObjResponse<List<Vacation>> resp = new CommObjResponse<>();
		resp.setBody(this.vacationDao.queryByMonth(request.getBody()));
		return resp;
	}

}
