package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.dao.GetMoneyDao;
import com.qisen.mts.spa.dao.MemberDao;
import com.qisen.mts.spa.model.entity.GetMoney;
import com.qisen.mts.spa.model.entity.SpaMember;

@Service
public class GetMoneyServiceImpl implements GetMoneyService {

	@Autowired
	private GetMoneyDao getMoneyDao;

	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 查询提现记录列表
	 */
	@Override
	public PageResponse<List<GetMoney>> list(PageRequest<GetMoney> req) {
		PageResponse<List<GetMoney>> resp = new PageResponse<List<GetMoney>>();
		GetMoney body = req.getBody();
		List<GetMoney> list = getMoneyDao.list(body,req.getStartIndex(),req.getPageSize());
		resp.setBody(list);
		return resp;
	}
	
	/**
	 * 新增提现记录
	 */
	@Override
	public BaseResponse create(PageRequest<GetMoney> req) {
		BaseResponse resp = new BaseResponse();
		GetMoney body = req.getBody();
		SpaMember member = new SpaMember();
		member.setAppid(body.getAppid());
		member.setBalance(body.getGetMoney());
		member.setOpenid(body.getOpenid());
		int count =  memberDao.reduceBalance(member);
		if(count > 0){
			getMoneyDao.create(body);
		}else{
			resp.setCode(MsgCode.GET_MONEY_FAILD);
		}
		return resp;
	}

}
