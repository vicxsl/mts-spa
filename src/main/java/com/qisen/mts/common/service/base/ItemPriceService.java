package com.qisen.mts.common.service.base;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.ItemPrice;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
public interface ItemPriceService {
	
	public BaseResponse save(BaseRequest<ItemPrice> req) throws Exception;

	public BaseResponse delete(BaseRequest<JSONObject> req) throws Exception;

}
