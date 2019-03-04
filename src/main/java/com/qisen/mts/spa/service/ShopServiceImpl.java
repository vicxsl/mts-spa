package com.qisen.mts.spa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class ShopServiceImpl implements ShopService{
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
	
	@Autowired
	private ShopDao shopDao;
	
	/**
	 * 查询spa商户
	 */
	@Override
	public CommObjResponse<SpaShop> queryByAppId(SpaRequest<JSONObject> req) {
		CommObjResponse<SpaShop> resp = new CommObjResponse<SpaShop>();
		JSONObject obj = req.getBody();
		String appId = obj.getString("appId");
		if(!obj.isEmpty() && !appId.isEmpty()){
			SpaShop shop = shopDao.queryByAppId(appId);
			resp.setBody(shop);
		}else{
			resp.setCode(MsgCode.SHOP_NOT_EXIST);//商户不存在
		}
		return resp;
	}

	/**
	 * 查询轮播图urls,按顺序返回
	 */
	@Override
	public CommObjResponse<JSONArray> queryRotateImg(SpaRequest<JSONObject> req) {
		logger.info("查询轮播图urls开始req:{}",JSON.toJSON(req));
		return null;
	}

	/**
	 * 查询查询商品列表
	 */
	@Override
	public CommObjResponse<JSONArray> queryGoodList(SpaRequest<JSONObject> req) {
		logger.info("查询商品列表开始req:{}",JSON.toJSON(req));
		return null;
	}




	
}
