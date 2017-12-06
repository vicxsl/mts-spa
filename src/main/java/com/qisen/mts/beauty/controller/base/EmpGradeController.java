package com.qisen.mts.beauty.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.service.base.EmpGradeService;

@Controller
@RequestMapping("/beauty/empGrade")
public class EmpGradeController {

	@Autowired
	private EmpGradeService empGradeService;

	@RequestMapping("/list")
	@ResponseBody
	public CommObjResponse<List<EmpGrade>> list(@RequestBody BeautyRequest<JSONObject> req) {
		return empGradeService.list(req);
	}

	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse save(@RequestBody BeautyRequest<EmpGrade> req) throws Exception {
		return empGradeService.save(req);
	}

	@RequestMapping("/status")
	@ResponseBody
	public BaseResponse status(@RequestBody BeautyRequest<JSONObject> req) throws Exception {
		return empGradeService.status(req);
	}
}
