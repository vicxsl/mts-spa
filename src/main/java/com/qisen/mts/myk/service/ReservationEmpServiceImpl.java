package com.qisen.mts.myk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.inte.ReservationDao;
import com.qisen.mts.common.dao.inte.VacationDao;
import com.qisen.mts.common.dao.sys.ShopSettingDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.entity.inte.Vacation;
import com.qisen.mts.common.model.entity.sys.ShopSetting;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.myk.model.request.MykRequest;


@Service
public class ReservationEmpServiceImpl implements ReservationEmpService {
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private VacationDao vacationDao;
	
	@Autowired
	private ShopSettingDao shopSettingDao;
	
	@Autowired
	private EmpDao empDao;
	
	@Override
	public CommObjResponse<JSONObject> reservationList(BaseRequest<Reservation> request) throws Exception {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		Reservation reservation = request.getBody();
		reservation.setEid(request.getEid());
		reservation.setSid(request.getSid());
		JSONObject objs = new JSONObject();
		//查询预约
		request.getBody().setEid(request.getEid());
		request.getBody().setSid(request.getSid());
		objs.put("reservations", reservationDao.queryReservations(reservation));
		//查询休假
		Vacation vacation = new Vacation();
		vacation.setEid(request.getEid());
		vacation.setSid(request.getSid());
		vacation.setEmpId(request.getBody().getEmpId());
		vacation.setVacationDate(request.getBody().getReservationTime());
		objs.put("vacations", this.vacationDao.queryByDay(vacation));
		resp.setBody(objs);
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> getReservationTime(MykRequest<Reservation> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject obj = new JSONObject();
		JSONObject object = new JSONObject();
		obj.put("mid", 2);
		List<ShopSetting> settingList = shopSettingDao.list(req.getEid(),req.getSid(),obj);
		
		for( ShopSetting set : settingList){
			if(set.getKey().equals("RES_startTime")){
				object.put("RES_startTime", set.getValue());
			}
			if(set.getKey().equals("RES_endTime")){
				object.put("RES_endTime", set.getValue());
			}
			if(set.getKey().equals("RES_strategy")){
				object.put("RES_strategy", set.getValue());
			}
		}
		resp.setBody(object);
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> addReservation(MykRequest<Reservation> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		Reservation reservation = req.getBody();
		reservation.setEid(req.getEid());
		reservation.setSid(req.getSid());
		reservation.setReservationSource(0);
		if(this.vacationDao.checkVacationAvailabilty(reservation)>0){
			resp.setCode(MsgCode.RESERVATION_VACATION_CONFLICT);
		}
		if(this.reservationDao.checkReservationAvailabilty(reservation)>0){
			resp.setCode(MsgCode.RESERVATION_TIME_CONFLICT);
		}
		if(resp.getResult() == 0){
			reservationDao.add(reservation);
		}
		
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> findMemReservation(MykRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		List<Reservation> lists = reservationDao.fingBymemId(req.getBody().getInteger("memId"));
		JSONObject object = new JSONObject();
		object.put("ReservationList", lists);
		resp.setBody(object);
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> deleteReservation(MykRequest<Reservation> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		int count = reservationDao.update(req.getBody());
		if(count == 0){
			resp.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return resp;
	}

	@Override
	public CommObjResponse<JSONObject> empReservationList(MykRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		int count = reservationDao.empReservationList(req.getBody().getInteger("empId"));
		JSONObject object = new JSONObject();
		object.put("count", count);
		resp.setBody(object);
		return resp;
	}

	@Override
	public CommObjResponse<List<Emp>> empList(MykRequest<JSONObject> req) {
		CommObjResponse<List<Emp>> resp = new CommObjResponse<>();
		List<Emp> employees = empDao.listBoookingEmp(req.getSid());
		resp.setBody(employees);
		return resp;
	}
}
