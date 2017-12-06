package com.qisen.mts.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.ConsumeLogDao;
import com.qisen.mts.admin.dao.EmployeeDao;
import com.qisen.mts.admin.dao.GoodsDao;
import com.qisen.mts.admin.dao.OrganDao;
import com.qisen.mts.admin.dao.ProductsClassDao;
import com.qisen.mts.admin.model.entity.ConsumeLog;
import com.qisen.mts.admin.model.entity.Employee;
import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.admin.model.entity.Organ;
import com.qisen.mts.admin.model.entity.ProductsClass;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Service
public class ConsumeLogServiceImpl implements ConsumeLogService {
	@Autowired
	private ConsumeLogDao consumeLogDao;

	@Autowired
	private OrganDao organDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ProductsClassDao productsClassDao;

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req) {
		PageResponse<JSONObject> resp = new PageResponse<JSONObject>();
		JSONObject obj = new JSONObject();
		JSONObject body = req.getBody();
		Integer count = 0;
		List<ConsumeLog> consumeLogList = new ArrayList<ConsumeLog>();

		List<Organ> organList = this.organDao.list(null);
		List<Employee> employeeList = this.employeeDao.list(null,0);
		List<ProductsClass> gClassList = this.productsClassDao.list(null);
		List<Goods> goodsList = this.goodsDao.list(0);
		if (body.getDate("beginConsumeTime") != null) {
			body.put("beginConsumeTime", body.getDate("beginConsumeTime"));
		}
		if (body.getDate("endConsumeTime") != null ) {
			body.put("endConsumeTime", body.getDate("endConsumeTime"));
		}
		if (body.getDate("beginPayTime") != null ) {
			body.put("beginPayTime", body.getDate("beginPayTime"));
		}
		if (body.getDate("endPayTime") != null ) {
			body.put("endPayTime", body.getDate("endPayTime"));
		}

		count = this.consumeLogDao.count(body, req.getEid(), req.getSid());
		if (count > 0) {
			Integer startIndex = req.getStartIndex();
			Integer endIndex = req.getEndIndex();
			consumeLogList = this.consumeLogDao.list(body, startIndex, endIndex, req.getEid(), req.getSid());
		}
		resp.setCount(count);
		resp.setPageSize(req.getPageSize());
		resp.setPageNum(req.getPageNum());
		obj.put("consumeLogList", consumeLogList);
		obj.put("organList", organList);
		obj.put("employeeList", employeeList);
		obj.put("gClassList", gClassList);
		obj.put("goodsList", goodsList);
		resp.setBody(obj);
		return resp;
	}

	@Override
	public BaseResponse update(AdminRequest<ConsumeLog> req) {
		BaseResponse resp = new BaseResponse();
		this.consumeLogDao.update(req.getBody());
		return resp;
	}

	@Override
	public BaseResponse updateAuditFlag(AdminRequest<JSONObject> req) {
		BaseResponse resp = new BaseResponse();
		Integer id = req.getBody().getInteger("id");
		String auditFlag = req.getBody().getString("auditFlag");
		this.consumeLogDao.updateAuditFlag(auditFlag, id);
		return resp;
	}

	@Override
	public BaseResponse create(AdminRequest<ConsumeLog> req) {
		BaseResponse resp = new BaseResponse();
		ConsumeLog consumeLog = req.getBody();
		consumeLog.setEid(req.getEid());
		consumeLog.setSid(req.getSid());
		// consumeLog.setAdmin(req.getSessionUser().getName());
		int count = consumeLogDao.create(consumeLog);
		if (count == 0) {
			resp.setCode(MsgCode.COMMON_CREATE_FAILED);
		}
		return resp;
	}

}
