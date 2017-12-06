package com.qisen.mts.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.entity.sys.Department;
import com.qisen.mts.common.model.entity.sys.Gtype;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface DepartAndGTypeService {

	public CommObjResponse<JSONObject> list(BaseRequest<JSONObject> req);

	public BaseResponse saveDep(AdminRequest<Department> req);

	public BaseResponse saveEmp(AdminRequest<Gtype> req);

	public BaseResponse editDep(AdminRequest<Department> req);

	public BaseResponse deleteDep(AdminRequest<Department> req);

	public BaseResponse editEmp(AdminRequest<Gtype> req);

	public BaseResponse deleteEmp(AdminRequest<Gtype> req);
}
