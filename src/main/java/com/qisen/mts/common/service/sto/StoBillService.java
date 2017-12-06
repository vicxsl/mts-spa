package com.qisen.mts.common.service.sto;

import com.qisen.mts.common.model.entity.sto.BillWithDetails;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.BulkChangeStatusRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.request.StoBillListRequest;
import com.qisen.mts.common.model.response.BaseResponse;
public interface StoBillService {

	public BaseResponse create(BaseRequest<BillWithDetails> request);
	
	public BaseResponse list(PageRequest<StoBillListRequest> request);

	public BaseResponse bulkChangeStatus(BaseRequest<BulkChangeStatusRequest> req)throws Exception;

}
