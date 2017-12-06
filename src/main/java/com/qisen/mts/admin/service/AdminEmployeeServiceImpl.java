package com.qisen.mts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.EmployeeDao;
import com.qisen.mts.admin.dao.OrganDao;
import com.qisen.mts.admin.model.entity.Employee;
import com.qisen.mts.admin.model.entity.Organ;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
@Service
public class AdminEmployeeServiceImpl implements AdminEmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private OrganDao organDao;
	
	@Override
	public CommObjResponse<JSONObject> list(AdminRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp=new CommObjResponse<JSONObject>();
		JSONObject obj=new JSONObject();
		List<Employee> empList=null;
		if(req.getBody() != null){
			empList=this.employeeDao.list(req.getBody().getString("keyword"),req.getBody().getIntValue("orgNo"));
		}else{
			empList=this.employeeDao.list(null,0);
		}
		List<Organ> organList=this.organDao.list(null);
		obj.put("empList", empList);
		obj.put("organList", organList);
		resp.setBody(obj);
		return resp;
	}

	@Override
	public BaseResponse create(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Employee employee=req.getBody().toJavaObject(Employee.class);
		int count = employeeDao.check(employee);
		if(count == 0){
			this.employeeDao.create(employee);
		}else{
			resp.setCode(MsgCode.EMP_EDIT_NOORMOBILE_REPEAT);
		}
		return resp;
	}

	@Override
	public BaseResponse delete(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Integer id=req.getBody().getInteger("id");
		this.employeeDao.delete(id);
		return resp;
	}

	@Override
	public BaseResponse update(AdminRequest<JSONObject> req) {
		BaseResponse resp=new BaseResponse();
		Employee employee=req.getBody().toJavaObject(Employee.class);
		int count = employeeDao.check(employee);
		if(count == 0){
			this.employeeDao.update(employee);
		}else{
			resp.setCode(MsgCode.EMP_EDIT_NOORMOBILE_REPEAT);
		}
		return resp;
	}

}
