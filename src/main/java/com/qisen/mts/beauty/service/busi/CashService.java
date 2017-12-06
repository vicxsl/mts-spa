package com.qisen.mts.beauty.service.busi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.busi.EmpFee;
import com.qisen.mts.beauty.model.entity.busi.Pay;
import com.qisen.mts.common.model.entity.base.Item;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface CashService {

	public CommObjResponse<Long> doCash(BeautyRequest<JSONObject> req) throws Exception;
	
	public CommObjResponse<Boolean> checkCardNo(BeautyRequest<JSONObject> req) throws Exception;
	
	public CommObjResponse<Boolean> checkBillNo(BeautyRequest<JSONObject> req) throws Exception;
	
	public void calcEmpFeeAndGain(Pay pay, EmpFee emp, Item item, JSONArray empAchiRules);
}
