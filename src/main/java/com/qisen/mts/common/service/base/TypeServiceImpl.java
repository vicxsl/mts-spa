package com.qisen.mts.common.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.dao.base.TypeDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.CommonDoConsts;
import com.qisen.mts.common.model.entity.base.Type;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDao typeDao;

	@Override
	public BaseResponse save(BaseRequest<Type> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Type type = req.getBody();
		type.setEid(req.getEid());
		Integer result = null;
		if (type.getId() != null && type.getId() > 0) { // update
			result = typeDao.update(type);
		} else { // create
			// 会员和日常开支类型门店可以创建自己的
			if (type.getType().equals(CommonDoConsts.BASE_TYPE_MEMBER) || type.getType().equals(CommonDoConsts.BASE_TYPE_DAILY_OUT) || type.getType().equals(CommonDoConsts.BASE_TYPE_DAILY_IN) || type.getType().equals(CommonDoConsts.BASE_TYPE_PAYMENT_CASH) || type.getType().equals(CommonDoConsts.BASE_TYPE_PAYMENT_NONCASH))
				type.setSid(null);// type.setSid(req.getSid());
			else
				type.setSid(null);
			// check no
			int count = typeDao.check(req.getEid(), type.getSid(), type.getType(), type.getNo(), 0);
			if (count == 0)
				result = typeDao.create(type);
			else
				resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public BaseResponse status(BaseRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Integer result = typeDao.status(req.getEid(), req.getBody());
		if (result != null && result > 0)
			resp.notify4Metadata(req.getEid(), req.getSid());
		else
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	@Override
	public CommObjResponse<List<Type>> list(BaseRequest<JSONObject> req) {
		CommObjResponse<List<Type>> resp = new CommObjResponse<>();
		List<Type> types = typeDao.list(req.getEid(), req.getSid(), req.getBody());
		resp.setBody(types);
		return resp;
	}

}
