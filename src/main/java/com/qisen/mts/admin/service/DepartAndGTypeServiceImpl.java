package com.qisen.mts.admin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.dao.base.EmpGradeDao;
import com.qisen.mts.common.dao.sys.DepartmentDao;
import com.qisen.mts.common.dao.sys.GtypeDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.entity.sys.Department;
import com.qisen.mts.common.model.entity.sys.Gtype;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class DepartAndGTypeServiceImpl implements DepartAndGTypeService{

	@Autowired
	EmpGradeDao empGradeDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	GtypeDao gtypeDao;
	
	
	@Override
	public CommObjResponse<JSONObject> list(BaseRequest<JSONObject> req) {
		CommObjResponse<JSONObject> resp = new CommObjResponse<>();
		JSONObject obj = new JSONObject();
		obj.put("status", 1);
		obj.put("gtype", null);
		List<Gtype> gtypes =  gtypeDao.list(req.getEid());
		List<Department> departments =  departmentDao.list(req.getEid());
		JSONObject object = new JSONObject();
		object.put("gtypes", gtypes);//员工级别
		object.put("departments", departments);//员工级别
		resp.setBody(object);
		return resp;
	}

	@Override
	public BaseResponse saveDep(AdminRequest<Department> req) {
		BaseResponse resp = new BaseResponse();
		Department department = req.getBody();
		department.setEid(req.getEid());
		int count = departmentDao.count(department);
		if(count == 0){
			departmentDao.saveDep(department);
		}else {
			resp.setCode(MsgCode.DEPARTMENT_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse saveEmp(AdminRequest<Gtype> req) {
		BaseResponse resp = new BaseResponse();
		Gtype gtype = req.getBody();
		gtype.setEid(req.getEid());
		
		int count = gtypeDao.count(gtype);
		if(count == 0){
			gtypeDao.save(gtype);
		}else {
			resp.setCode(MsgCode.EMPGRADE_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse editDep(AdminRequest<Department> req) {
		BaseResponse resp = new BaseResponse();
		int count = departmentDao.edit(req.getBody());
		if(count == 0){
			resp.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return resp;
	}

	@Override
	public BaseResponse deleteDep(AdminRequest<Department> req) {
		BaseResponse resp = new BaseResponse();
		Department department = req.getBody();
		departmentDao.deleteDep(department);
		gtypeDao.deleteEmp(department.getEid(),department.getDepCode(),0);
		return resp;
	}

	@Override
	public BaseResponse editEmp(AdminRequest<Gtype> req) {
		BaseResponse resp = new BaseResponse();
		Gtype gtype = req.getBody();
		gtypeDao.editEmp(gtype);
		return resp;
	}

	@Override
	public BaseResponse deleteEmp(AdminRequest<Gtype> req) {
		BaseResponse resp = new BaseResponse();
		
		gtypeDao.deleteEmp(req.getBody().getEid(),req.getBody().getDepCode(),req.getBody().getId());
		return resp;
	}
}
