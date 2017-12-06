package com.qisen.mts.common.service.mem;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
public interface MemberService {
	public BaseResponse create(Member memMember);

	public BaseResponse update(Member memMember);

	public PageResponse<List<Member>> list(PageRequest<JSONObject> req) throws Exception;
	
	public BaseResponse updateStatus(BaseRequest<JSONObject> req);
	
	public BaseResponse edit(BaseRequest<Member> req);
	
	public CommObjResponse<JSONObject> cardAndItem(BaseRequest<JSONObject> req);
	
	public PageResponse<List<JSONObject>> cosumeDetail(PageRequest<JSONObject> req);
	
	public PageResponse<JSONObject> memCardRecord(PageRequest<JSONObject> req);

	
}
