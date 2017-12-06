package com.qisen.mts.common.service.base;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Type;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

public interface TypeService {

	/**
	 * 保存基础分类
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse save(BaseRequest<Type> req) throws Exception;
	
	/**
	 * 修改基础分类状态
	 * 
	 * @param req
	 * @return
	 */
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception;

	/**
	 * 查询基础分类
	 * 
	 * @param req
	 * @return
	 */
	public CommObjResponse<List<Type>> list(BaseRequest<JSONObject> req);

}
