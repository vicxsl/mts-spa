package com.qisen.mts.common.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.EmpGradeDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class EmpGradeServiceImpl implements EmpGradeService {

	@Autowired
	private EmpGradeDao empGradeDao;

	@Override
	public BaseResponse save(BaseRequest<EmpGrade> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		EmpGrade empGrade = req.getBody();
		empGrade.setEid(req.getEid());
		empGrade.setSid(null);
		Integer result = null;
		if (empGrade.getId() != null && empGrade.getId() > 0) { // update
			result = empGradeDao.update(empGrade);
		} else { // create
			// check no
			int count = empGradeDao.check(req.getEid(), null, null, empGrade.getNo(), 0);
			if (count == 0)
				result = empGradeDao.create(empGrade);
			else
				resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = empGradeDao.status(req.getEid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), null);
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public CommObjResponse<List<EmpGrade>> list(BaseRequest<JSONObject> req) {
		CommObjResponse<List<EmpGrade>> resp = new CommObjResponse<>();
		List<EmpGrade> empGrades = empGradeDao.list(req.getEid(), req.getSid(), req.getBody());
		resp.setBody(empGrades);
		return resp;
	}

}
