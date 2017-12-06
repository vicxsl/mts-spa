package com.qisen.mts.beauty.controller.sto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.entity.sto.BillWithDetails;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoBillListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.service.sto.StoBillService;

@Controller
@RequestMapping("/beauty/stoBill")
public class StoBillController {

	@Autowired
	private StoBillService stoBillService;

	@RequestMapping("/create")
	public @ResponseBody
	BaseResponse create(@RequestBody BeautyRequest<BillWithDetails> req) throws Exception  {
		req.getBody().setOptId(req.getSessionUser().getId());
		req.getBody().setOptName(req.getSessionUser().getName());
		if(StringUtils.isBlank(req.getBody().getHandler()))
			req.getBody().setHandler(req.getSessionUser().getAccount());
		if(StringUtils.isBlank(req.getBody().getOptName()))
			req.getBody().setOptName(req.getSessionUser().getAccount());
		return stoBillService.create(req);
	}

	@RequestMapping("/list")
	public @ResponseBody
	BaseResponse list(@RequestBody PageRequest<StoBillListRequest> req) throws Exception  {
		return stoBillService.list(req);
	}

	@RequestMapping("/bulkChangeStatus")
	@ResponseBody
	public BaseResponse bulkChangeStatus(@RequestBody BeautyRequest<BulkChangeStatusRequest> req) throws Exception {
		req.getBody().setExamId(req.getSessionUser().getId());
		req.getBody().setExamName(req.getSessionUser().getName());
		return stoBillService.bulkChangeStatus(req);
	}

}
