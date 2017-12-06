package com.qisen.mts.common.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.EmpDao;
import com.qisen.mts.common.dao.sys.ShopDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao baseEmpDao;
	
	@Autowired
	ShopDao shopDao;

	@Override
	public BaseResponse edit(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Emp baseEmp = req.getBody().toJavaObject(Emp.class);
		baseEmp.setEid(req.getEid());
		baseEmp.setSid(req.getSid());
		Integer result = null;
		if (null != baseEmp.getId() && baseEmp.getId().equals(0)) {
			Integer count = baseEmpDao.check(baseEmp);
			if (count != null && count.equals(0)) {
				result = baseEmpDao.create(baseEmp);
			} else {
				resp.setCode(MsgCode.EMP_EDIT_NOORMOBILE_REPEAT);
			}
		} else {
			result = baseEmpDao.update(baseEmp);
		}
		if (result != null && result.intValue() > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req) {
		PageResponse<JSONObject> response = new PageResponse<JSONObject>();
		int count = this.baseEmpDao.count(req);
		if (count > 0) {
			JSONObject jsonObject = new JSONObject();
			List<Emp> baseEmpList = baseEmpDao.list(req);
			jsonObject.put("emplist", baseEmpList);
			response.setBody(jsonObject);
			response.setPageSize(req.getPageSize());
			response.setPageNum(req.getPageNum());
			response.setCount(count);
		}
		return response;
	}

	@Override
	public CommObjResponse<Emp> find(int id) {
		CommObjResponse<Emp> response = new CommObjResponse<Emp>();
		Emp baseEmp = baseEmpDao.find(id);
		response.setBody(baseEmp);
		return response;
	}

	@Override
	public BaseResponse updateStatus(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = baseEmpDao.updatestatus(req);
		if (result != null && result.intValue() > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

}
