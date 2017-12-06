package com.qisen.mts.admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.OutlayDao;
import com.qisen.mts.admin.model.entity.Outlay;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;


@Service
public class OutlayServiceImpl implements OutlayService {
	
	@Autowired
	private OutlayDao outlayDao;

	@Override
	public PageResponse<List<Outlay>> List(PageRequest<JSONObject> req) {
		PageResponse<List<Outlay>> resp = new PageResponse<List<Outlay>>();
		if (req.getBody().getJSONArray("inputDate").size() != 0) {
			Date startDate =     req.getBody().getJSONArray("inputDate").getDate(0);
			Date endDate =     req.getBody().getJSONArray("inputDate").getDate(1);
			req.getBody().put("startDate", startDate);
			req.getBody().put("endDate", endDate);
		}
		
		int count =outlayDao.check(req);
		resp.setCount(count);
		resp.setPageSize(req.getPageSize());
		List<Outlay> goodList = outlayDao.list(req.getStartIndex(),req.getEndIndex(),req.getBody());
	
		resp.setBody(goodList);
		return resp;
	}

	@Override
	public BaseResponse create(AdminRequest<Outlay> req) {
		BaseResponse resp = new BaseResponse();
		int reccount = outlayDao.create(req.getBody());
		if (reccount == 0) {
			resp.setCode(MsgCode.COMMON_CREATE_FAILED);
		}
		return resp;
	}

	@Override
	public BaseResponse update(AdminRequest<Outlay> req) {
		BaseResponse resp = new BaseResponse();
		int reccount =outlayDao.update(req.getBody());
		if (reccount == 0) {
			resp.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return resp;
	}

	@Override
	public BaseResponse delete(AdminRequest<Outlay> req) {
		BaseResponse resp = new BaseResponse();
		int reccount =outlayDao.delete(req.getBody());
		if (reccount == 0) {
			resp.setCode(MsgCode.COMMON_DELETE_FAILED);
		}
		return resp;
	}

}
