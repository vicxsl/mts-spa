package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.request.SpaRequest;

public interface MemberService {

	/**
	 * 商城登录
	 * @param req
	 * @return
	 */
	public CommObjResponse<MetaData> login(SpaRequest<SpaMember> req);

	public CommObjResponse<List<SpaMember>> memberIncomeDetailsList(SpaRequest<SpaMember> req);
}
