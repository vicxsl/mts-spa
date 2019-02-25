package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.MemberDao;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 商城登录
	 */
	@Override
	public CommObjResponse<List<SpaMember>> login(SpaRequest<SpaMember> req) {
		CommObjResponse<List<SpaMember>> resp = new CommObjResponse<List<SpaMember>>();
		SpaMember body = req.getBody();
		SpaMember query = new SpaMember();
		query.setEid(body.getEid());
		query.setSid(body.getSid());
		query.setOpenid(body.getOpenid());
		SpaMember checkMember = memberDao.check(query);
		if (checkMember != null && checkMember.getId() > 0 ) {
			if((body.getMobile() != null && checkMember.getMobile() != body.getMobile()) || (body.getName() != null && checkMember.getName() != body.getName())){
				memberDao.update(body);
			}
		}else {
			memberDao.create(body);
		}
		return resp;
	}

	
	
}
