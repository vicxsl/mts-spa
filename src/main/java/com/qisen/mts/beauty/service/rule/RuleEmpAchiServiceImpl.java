package com.qisen.mts.beauty.service.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.dao.rule.RuleEmpAchiDao;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.beauty.model.request.BeautyRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class RuleEmpAchiServiceImpl implements RuleEmpAchiService {

	@Autowired
	private RuleEmpAchiDao empAchiDao;

	@Override
	public BaseResponse save(BeautyRequest<RuleEmpAchi> req) {
		BaseResponse resp = new BaseResponse();
		RuleEmpAchi empAchi = req.getBody();
		empAchi.setEid(req.getEid());
		empAchi.setSid(req.getSid());
		Integer result = null;
		if (empAchi.getId() != null && empAchi.getId() > 0)
			result = empAchiDao.update(empAchi);
		else
			result = empAchiDao.create(empAchi);
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse delete(BeautyRequest<JSONObject> req) {
		BaseResponse resp = new BaseResponse();
		Integer result = empAchiDao.delete(req.getEid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse status(BeautyRequest<JSONObject> req) {
		BaseResponse resp = new BaseResponse();
		Integer result = empAchiDao.status(req.getEid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse update4Sort(BeautyRequest<JSONObject> req) {
		BaseResponse resp = new BaseResponse();
		empAchiDao.update4Sort(req.getEid(), req.getBody());
		resp.notify4Metadata(req.getEid(), req.getSid());
		/*
		 * if (result != null && result > 0) resp.notify4Metadata(req.getEid(), req.getSid()); else resp.setResult(ResultCode.FAILED);
		 */
		return resp;
	}

	@Override
	public CommObjResponse<List<RuleEmpAchi>> list(BeautyRequest<JSONObject> req) {
		CommObjResponse<List<RuleEmpAchi>> resp = new CommObjResponse<>();
		List<RuleEmpAchi> empAchis = empAchiDao.list(req.getEid(), req.getSid(), req.getBody());
		resp.setBody(empAchis);
		return resp;
	}

	@Override
	public BaseResponse copyRule(BeautyRequest<JSONObject> req) {
		BaseResponse response = new BaseResponse();
		JSONObject body = req.getBody();
		this.empAchiDao.delRuleBysid(req.getEid(), req.getSid(), body.getInteger("type"));
		this.empAchiDao.copyRule(req.getEid(), req.getSid(), body.getInteger("type"));
		return response;
	}

}
