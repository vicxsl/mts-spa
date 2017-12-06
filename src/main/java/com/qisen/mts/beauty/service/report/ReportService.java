package com.qisen.mts.beauty.service.report;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface ReportService {

	/**
	 * 小计
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<JSONObject> daySummary(BeautyRequest<JSONObject> req);
	
	/**
	 * 员工业绩汇总
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<JSONObject> empFeeSheet(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 员工提成汇总
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public CommObjResponse<JSONObject> empGainSheet(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 员工提成明细
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public CommObjResponse<JSONObject> empGainDetailSheet(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 项目分类业绩汇总
	 * 
	 * @param req
	 * @return
	 * 
	 */
	public CommObjResponse<JSONObject> itemTypeFeeSheet(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 项目分类业绩明细
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<JSONObject> itemFeeDetailSheet(BeautyRequest<JSONObject> req) throws Exception;
	
	public CommObjResponse<JSONObject> shopSummarySheet(BeautyRequest<JSONObject> req) throws Exception;
	
	/**
	 * 现金流水表
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<JSONObject> sumCashReport(BeautyRequest<JSONObject> req) throws Exception;
	
	
	/**
	 * 卡金变动流水
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public CommObjResponse<JSONObject> shopCardRecord(BeautyRequest<JSONObject> req) throws Exception;
	
}
