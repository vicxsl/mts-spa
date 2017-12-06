package com.qisen.mts.common.service.base;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface EmpGradeService {
	
	/**
	 * 保存员工级别类型
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse save(BaseRequest<EmpGrade> req) throws Exception;
	
	/**
	 * 修改员工级别类型状态
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception;

	/**
	 * 查询员工级别类型
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<EmpGrade>> list(BaseRequest<JSONObject> req);

}
