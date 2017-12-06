package com.qisen.mts.common.service.base;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Company;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface CompanyService {

	/**
	 * 保存产品公司
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse save(BaseRequest<Company> req) throws Exception;
	
	/**
	 * 修改产品公司状态
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception;

	/**
	 * 查询产品公司
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<Company>> list(BaseRequest<JSONObject> req);

}
