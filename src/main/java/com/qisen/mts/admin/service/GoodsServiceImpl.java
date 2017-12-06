package com.qisen.mts.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.admin.dao.GoodsDao;
import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;


@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public CommObjResponse<List<Goods>> List(AdminRequest<Goods> req) {
		CommObjResponse<List<Goods>> resp = new CommObjResponse<List<Goods>>();
		List<Goods> goodList = goodsDao.list(0);
		resp.setBody(goodList);
		return resp;
	}

	@Override
	public BaseResponse create(AdminRequest<Goods> req) {
		BaseResponse resp = new BaseResponse();
		int reccount = goodsDao.check(req.getBody());
		if (reccount == 0) {
			goodsDao.create(req.getBody());
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse update(AdminRequest<Goods> req) {
		BaseResponse resp = new BaseResponse();
		int reccount = goodsDao.check(req.getBody());
		if (reccount == 0) {
			goodsDao.update(req.getBody());
		}else{
			resp.setCode(MsgCode.COMMON_NO_EXIST);
		}
		return resp;
	}

	@Override
	public BaseResponse delete(AdminRequest<Goods> req) {
		BaseResponse resp = new BaseResponse();
		int reccount =goodsDao.delete(req.getBody());
		if (reccount == 0) {
			resp.setCode(MsgCode.COMMON_DELETE_FAILED);
		}
		return resp;
	}

}
