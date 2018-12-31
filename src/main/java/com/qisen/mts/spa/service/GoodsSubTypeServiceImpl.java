package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsSubTypeDao;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class GoodsSubTypeServiceImpl implements GoodsSubTypeService{
	
	@Autowired
	private GoodsSubTypeDao goodsSubTypeDao;
	
	/**
	 * 新增spa商品子类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsSubType>> save(SpaRequest<SpaGoodsSubType> req) {
		CommObjResponse<List<SpaGoodsSubType>> resp = new CommObjResponse<List<SpaGoodsSubType>>();
		SpaGoodsSubType spaGoodsSubType = req.getBody();
		SpaGoodsSubType queryGoodsType = new SpaGoodsSubType();
		queryGoodsType.setEid(spaGoodsSubType.getEid());
		queryGoodsType.setSid(spaGoodsSubType.getSid());
		int count = goodsSubTypeDao.check(spaGoodsSubType);
		if(spaGoodsSubType.getId() != null && spaGoodsSubType.getId() > 0){
			if (count == 0 ) {
				goodsSubTypeDao.update(spaGoodsSubType);
				List<SpaGoodsSubType>  spaList = goodsSubTypeDao.list(queryGoodsType);
				resp.setBody(spaList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				goodsSubTypeDao.create(spaGoodsSubType);
				List<SpaGoodsSubType>  spaList = goodsSubTypeDao.list(queryGoodsType);
				resp.setBody(spaList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}
		return resp;
	}

	/**
	 * 删除spa商品子类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsSubType>> delete(SpaRequest<SpaGoodsSubType> req) {
		CommObjResponse<List<SpaGoodsSubType>> resp = new CommObjResponse<List<SpaGoodsSubType>>();
		SpaGoodsSubType spaGoodsSubType = req.getBody();
		SpaGoodsSubType queryGoodsType = new SpaGoodsSubType();
		queryGoodsType.setEid(spaGoodsSubType.getEid());
		queryGoodsType.setSid(spaGoodsSubType.getSid());
		int count = goodsSubTypeDao.delete(spaGoodsSubType);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}else{
			List<SpaGoodsSubType>  spaList = goodsSubTypeDao.list(queryGoodsType);
			resp.setBody(spaList);
		}
		return resp;
	}

	/**
	 * 查询spa商品子类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsSubType>> list(SpaRequest<SpaGoodsSubType> req) {
		CommObjResponse<List<SpaGoodsSubType>> resp = new CommObjResponse<List<SpaGoodsSubType>>();
		SpaGoodsSubType spaGoodsSubType = req.getBody();
		List<SpaGoodsSubType>  spaList = goodsSubTypeDao.list(spaGoodsSubType);
		resp.setBody(spaList);
		return resp;
	}
	
}
