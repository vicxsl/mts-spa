package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsTypeDao;
import com.qisen.mts.spa.model.entity.SpaGoodsType;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{
	
	@Autowired
	private GoodsTypeDao goodsTypeDao;
	
	/**
	 * 新增spa商品类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsType>> save(SpaRequest<SpaGoodsType> req) {
		CommObjResponse<List<SpaGoodsType>> resp = new CommObjResponse<List<SpaGoodsType>>();
		SpaGoodsType spaGoodsType = req.getBody();
		SpaGoodsType queryGoodsType = new SpaGoodsType();
		queryGoodsType.setEid(spaGoodsType.getEid());
		queryGoodsType.setSid(spaGoodsType.getSid());
		int count = goodsTypeDao.check(spaGoodsType);
		if(spaGoodsType.getId() != null && spaGoodsType.getId() > 0){
			if (count == 0 ) {
				goodsTypeDao.update(spaGoodsType);
				List<SpaGoodsType>  goodsList = goodsTypeDao.list(queryGoodsType);
				resp.setBody(goodsList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				goodsTypeDao.create(spaGoodsType);
				List<SpaGoodsType>  goodsList = goodsTypeDao.list(queryGoodsType);
				resp.setBody(goodsList);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}
		return resp;
	}

	/**
	 * 删除spa商品类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsType>> delete(SpaRequest<SpaGoodsType> req) {
		CommObjResponse<List<SpaGoodsType>> resp = new CommObjResponse<List<SpaGoodsType>>();
		SpaGoodsType spaGoodsType = req.getBody();
		SpaGoodsType queryGoodsType = new SpaGoodsType();
		queryGoodsType.setEid(spaGoodsType.getEid());
		queryGoodsType.setSid(spaGoodsType.getSid());
		int count = goodsTypeDao.delete(spaGoodsType);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}else{
			List<SpaGoodsType>  spaList = goodsTypeDao.list(queryGoodsType);
			resp.setBody(spaList);
		}
		return resp;
	}

	/**
	 * 查询spa商品类型
	 */
	@Override
	public CommObjResponse<List<SpaGoodsType>> list(SpaRequest<SpaGoodsType> req) {
		CommObjResponse<List<SpaGoodsType>> resp = new CommObjResponse<List<SpaGoodsType>>();
		SpaGoodsType spaGoods = req.getBody();
		List<SpaGoodsType>  spaList = goodsTypeDao.list(spaGoods);
		resp.setBody(spaList);
		return resp;
	}
	
}
