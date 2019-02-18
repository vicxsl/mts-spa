package com.qisen.mts.spa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class GoodsShopCarServiceImpl implements GoodsShopCarService {

	@Autowired
	private GoodsShopCarDao goodsShopCarDao;

	/**
	 * 新增spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoodsShopCar>> edit(SpaRequest<SpaGoodsShopCar> req) {
		CommObjResponse<List<SpaGoodsShopCar>> resp = new CommObjResponse<List<SpaGoodsShopCar>>();
		SpaGoodsShopCar body = req.getBody();
		SpaGoodsShopCar query = new SpaGoodsShopCar();
		query.setEid(body.getEid());
		query.setSid(body.getSid());
		if (body.getNum() == 0) {
			goodsShopCarDao.delete(body);
		} else {
			if (body.getId() != null && body.getId() > 0) {
				goodsShopCarDao.update(body);
				
			} else {
				goodsShopCarDao.add(body);
			}
		}
		List<SpaGoodsShopCar> spaList = goodsShopCarDao.list(query);
		resp.setBody(spaList);
		return resp;
	}

	/**
	 * 查询spa账号
	 */
	@Override
	public CommObjResponse<List<SpaGoodsShopCar>> list(SpaRequest<SpaGoodsShopCar> req) {
		CommObjResponse<List<SpaGoodsShopCar>> resp = new CommObjResponse<List<SpaGoodsShopCar>>();
		SpaGoodsShopCar body = req.getBody();
		List<SpaGoodsShopCar> spaList = goodsShopCarDao.list(body);
		resp.setBody(spaList);
		return resp;
	}

}
