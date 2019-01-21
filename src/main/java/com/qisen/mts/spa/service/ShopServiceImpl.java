package com.qisen.mts.spa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class ShopServiceImpl implements ShopService{
	
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
		System.out.println(obj.isEmpty());
		System.out.println("=============================");
		if(!obj.isEmpty() && !appId.isEmpty()){
			SpaShop shop = shopDao.queryByAppId(appId);
			resp.setBody(shop);
		}else{
			resp.setCode(MsgCode.SHOP_NOT_EXIST);//商户不存在
		}
		return resp;
	}

	
}
