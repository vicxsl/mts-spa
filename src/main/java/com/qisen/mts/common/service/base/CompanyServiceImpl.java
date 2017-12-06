package com.qisen.mts.common.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.CompanyDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.Company;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Override
	public BaseResponse save(BaseRequest<Company> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Company company = req.getBody();
		company.setEid(req.getEid());
		Integer result = null;
		if (company.getId() != null && company.getId() > 0) { // update
			result = companyDao.update(company);
		} else { // create
			int count = companyDao.check(company);
			if (count == 0){
				result = companyDao.create(company);
			}else{
				resp.setCode(MsgCode.COMMON_NO_EXIST);
			}
		}
		if (result != null && result > 0){
			resp.notify4Metadata(req.getEid(), req.getSid());
		}else{
			resp.setResult(ResultCode.FAILED);
		}
		return resp;
	}

	@Override
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = companyDao.status(req.getEid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public CommObjResponse<List<Company>> list(BaseRequest<JSONObject> req) {
		CommObjResponse<List<Company>> resp = new CommObjResponse<>();
		List<Company> companys = companyDao.list(req.getEid(), req.getSid(), req.getBody());
		resp.setBody(companys);
		return resp;
	}

}
