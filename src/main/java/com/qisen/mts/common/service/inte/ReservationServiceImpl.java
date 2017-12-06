/**
 * 
 */
package com.qisen.mts.common.service.inte;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.inte.ReservationDao;
import com.qisen.mts.common.dao.inte.VacationDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.entity.inte.Vacation;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.gateway.ExceptionWithCode;

/**
 * @author forbr
 *
 */
@Service
public class ReservationServiceImpl implements IReservationService{
	
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private VacationDao vacationDao;

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#add(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse add(BaseRequest<Reservation> request)throws Exception {
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		//检查是否可以预约
		this.checkAvailablity(request);
		this.reservationDao.add(request.getBody());
		return new BaseResponse();
	}

	/**
	 * 检查是否可以预约
	 * @param request
	 */
	private void checkAvailablity(BaseRequest<Reservation> request) throws Exception{
		
		if(this.vacationDao.checkVacationAvailabilty(request.getBody())>0){
			throw new ExceptionWithCode(MsgCode.RESERVATION_VACATION_CONFLICT);
		}
		if(this.reservationDao.checkReservationAvailabilty(request.getBody())>0){
			throw new ExceptionWithCode(MsgCode.RESERVATION_TIME_CONFLICT);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#queryReservations(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse queryReservations(BaseRequest<Reservation> request) {
		CommObjResponse<Map<String,Object>> resp = new CommObjResponse<>();
		Map<String,Object> objs = new HashMap<>();
		//查询预约
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		objs.put("reservations", this.reservationDao.queryReservations(request.getBody()));
		//查询休假
		Vacation vacation = new Vacation();
		vacation.setEid(request.getEid());
		vacation.setSid(request.getSid());
		vacation.setVacationDate(request.getBody().getReservationTime());
		objs.put("vacations", this.vacationDao.queryByDay(vacation));
		resp.setBody(objs);
		return resp;
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#queryScheduling(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse queryScheduling(BaseRequest<Reservation> request) {
		CommObjResponse<Map<String, Map<String,Object>>> resp = new CommObjResponse<>();
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		Map<String, Map<String,Object>> scheduling = new HashMap<>();
		List<Map<String,Object>> objs = this.reservationDao.queryScheduling(request.getBody());
		Iterator<Map<String,Object>> ite = objs.iterator();
		//加入所有的休假
		while (ite.hasNext()) {
			Map<String,Object> r = ite.next();
			if (((BigDecimal)r.get("TYPE")).intValue()==10) {
				Map<String,Object> sch = new HashMap<>();
				sch.put("vacation", Boolean.TRUE);
				scheduling.put(r.get("RDATE").toString(), sch);
			} else {
				break;
			}
		}
		//加入所有预约
		for (Map<String,Object> r : objs) {
			if (((BigDecimal)r.get("TYPE")).intValue()==0||((BigDecimal)r.get("TYPE")).intValue()==1) {
				Map<String,Object> sch = scheduling.get(r.get("RDATE").toString());
				if (sch == null) {
					sch = new HashMap<>();
					List<Map<String,Object>> resvs = new ArrayList<>();
					Map<String,Object> resv = new HashMap<>();
					resv.put("reservationTime", r.get("RESERVATIONTIME"));
					resv.put("type", r.get("TYPE"));
					resvs.add(resv);
					sch.put("reservations", resvs);
					scheduling.put(r.get("RDATE").toString(), sch);
				} else {
					if (sch.get("vacation")!=null&&sch.get("vacation").equals(Boolean.TRUE)) {
						List<Map<String,Object>> resvs;
						if(sch.containsKey("reservations")){
							resvs = (List<Map<String, Object>>) sch.get("reservations");
						}else{
							resvs = new ArrayList<>();
						}
						Map<String,Object> resv = new HashMap<>();
						resv.put("reservationTime", r.get("RESERVATIONTIME"));
						resv.put("type", r.get("TYPE"));
						resvs.add(resv);
						sch.put("reservations", resvs);
						scheduling.put(r.get("RDATE").toString(), sch);
					}
				}
			}
		}
		resp.setBody(scheduling);
		return resp;
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#cancel(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse cancel(BaseRequest<Reservation> request) throws Exception {
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		if(this.reservationDao.update(request.getBody())!=1){
			throw new ExceptionWithCode(MsgCode.RESERVATION_INVALID_STATUS);
		}
		return new BaseResponse();
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#changeStatus(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse changeStatus(BaseRequest<Reservation> request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#updateReservation(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse updateReservation(BaseRequest<Reservation> request) throws Exception {
		Reservation reservation =  this.reservationDao.load(request.getBody().getId());
		request.getBody().setEid(reservation.getEid());
		request.getBody().setSid(reservation.getSid());
		request.getBody().setEmpId(reservation.getEmpId());
		if(request.getBody().getReservationTime()!=null){
			this.checkAvailablity(request);
		}
		if(this.reservationDao.update(request.getBody())!=1){
			throw new ExceptionWithCode(MsgCode.RESERVATION_INVALID_STATUS);
		}
		return new BaseResponse();
	}

	/* (non-Javadoc)
	 * @see com.qisen.mts.common.service.inte.IReservationService#getShopReservationsNum(com.qisen.mts.common.model.request.BaseRequest)
	 */
	@Override
	public BaseResponse getShopReservationsNum(BaseRequest<Reservation> request) {
		CommObjResponse<Integer> resp = new CommObjResponse<>();
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		resp.setBody(this.reservationDao.getShopReservationsNum(request.getBody()));
		return resp;
	}

}
