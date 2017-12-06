package com.qisen.mts.common.service.mem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.dao.mem.CardDao;
import com.qisen.mts.common.model.entity.mem.Card;
import com.qisen.mts.common.model.response.BaseResponse;
@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private CardDao memCardDao;
	@Override
	public BaseResponse create(Card memCard) {
		BaseResponse response=new BaseResponse();
			this.memCardDao.create(memCard);
		return response;
	}

}
