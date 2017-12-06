package com.qisen.mts.beauty.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.service.report.ReportService;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.CommObjResponse;

@Controller
@RequestMapping("/beauty/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@RequestMapping("/daySummary")
	@ResponseBody
	public CommObjResponse<JSONObject> summary(@RequestBody BeautyRequest<JSONObject> req) {
		return reportService.daySummary(req);
	}
	
	@RequestMapping("/sumCashReport")
	@ResponseBody
	public CommObjResponse<JSONObject> list(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.sumCashReport(req);
	}

	
	@RequestMapping("/empFeeSheet")
	@ResponseBody
	public CommObjResponse<JSONObject> empFeeSheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.empFeeSheet(req);
	}
	
	@RequestMapping("/empGainSheet")
	@ResponseBody
	public CommObjResponse<JSONObject> empGainSheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.empGainSheet(req);
	}
	
	@RequestMapping("/empGainDetailSheet")
	@ResponseBody
	public CommObjResponse<JSONObject> empGainDetailSheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.empGainDetailSheet(req);
	}
	
	@RequestMapping("/itemTypeFeeSheet")
	@ResponseBody
	public CommObjResponse<JSONObject> itemTypeFeeSheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.itemTypeFeeSheet(req);
	}
	
	@RequestMapping("/itemFeeDetailSheet")
	@ResponseBody
	public CommObjResponse<JSONObject> itemFeeDetailSheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.itemFeeDetailSheet(req);
	}
	
	@RequestMapping("/shopSummarySheet")
	@ResponseBody
	public CommObjResponse<JSONObject> shopSummarySheet(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.shopSummarySheet(req);
	}
	
	@RequestMapping("/shopCardRecord")
	@ResponseBody
	public CommObjResponse<JSONObject> shopCardRecord(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return reportService.shopCardRecord(req);
	}
}
