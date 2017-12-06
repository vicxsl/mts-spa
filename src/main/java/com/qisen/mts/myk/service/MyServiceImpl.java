package com.qisen.mts.myk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.inte.ReservationDao;
import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.dao.mem.MemItemDao;
import com.qisen.mts.common.model.entity.inte.Reservation;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.entity.mem.Item;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class MyServiceImpl implements MyService{

	@Autowired
	CardDao memCardDao;

	@Autowired
	MemItemDao memItemDao;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Override
	public BaseResponse myCards(BaseRequest<JSONObject> req) {
		CommObjResponse<List<Card>> response = new CommObjResponse<List<Card>>();
		List<Card> memCardList = this.memCardDao.findByMemId(req.getEid(), req.getBody().getIntValue("id"));// 根据会员id查询它的所有会员卡
		response.setBody(memCardList);
		return response;
	}

	@Override
	public BaseResponse myItems(BaseRequest<JSONObject> req) {
		CommObjResponse<List<Item>> response = new CommObjResponse<List<Item>>();
		List<Item> memItemList = this.memItemDao.findByMemId(req.getEid(), req.getBody().getIntValue("id"));// 根据会员id
		response.setBody(memItemList);
		return response;
	}
	@Override
	public BaseResponse memberReservations(BaseRequest<Reservation> request) {
		CommObjResponse<Map<String,Object>> resp = new CommObjResponse<>();
		Map<String,Object> objs = new HashMap<>();
		//查询预约
		request.getBody().setEid(request.getEid());
		objs.put("reservations", this.reservationDao.memberReservations(request.getBody()));
//		//查询休假
//		Vacation vacation = new Vacation();
//		vacation.setEid(request.getEid());
//		vacation.setSid(request.getSid());
//		vacation.setVacationDate(request.getBody().getReservationTime());
//		objs.put("vacations", this.vacationDao.queryByDay(vacation));
		resp.setBody(objs);
		return resp;
	}
}
