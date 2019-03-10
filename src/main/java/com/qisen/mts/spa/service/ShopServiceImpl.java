package com.qisen.mts.spa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.ShopBonusDao;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.ShopBonus;
import com.qisen.mts.spa.model.entity.SpaImg;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

@Service
public class ShopServiceImpl implements ShopService{
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
	
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private MemberAddressDao memberAddressDao;
	@Autowired
	private ShopBonusDao shopBonusDao;
	
	/**
	 * 查询spa商户
	 */
	@Override
	public CommObjResponse<SpaShop> queryByAppId(SpaRequest<JSONObject> req) {
		CommObjResponse<SpaShop> resp = new CommObjResponse<SpaShop>();
		JSONObject obj = req.getBody();
		String appId = obj.getString("appid");
		if(!obj.isEmpty() && !appId.isEmpty()){
			SpaShop shop = shopDao.queryByAppId(appId);
			resp.setBody(shop);
		}else{
			resp.setCode(MsgCode.SHOP_NOT_EXIST);//商户不存在
		}
		return resp;
	}

	/**
	 * 查询spa商户列表
	 */
	@Override
	public CommObjResponse<List<SpaShop>> list(SpaRequest<SpaShop> req) {
		CommObjResponse<List<SpaShop>> resp = new CommObjResponse<List<SpaShop>>();
		SpaShop query = new SpaShop();
		query.setEid(req.getEid());
		List<SpaShop> list = shopDao.list(query);
		resp.setBody(list);
		return resp;
	}

	/**
	 * 编辑商户后再查询列表
	 */
	@Override
	public CommObjResponse<List<SpaShop>> edit(SpaRequest<SpaShop> req) {
		CommObjResponse<List<SpaShop>> resp = new CommObjResponse<List<SpaShop>>();
		SpaShop query = new SpaShop();
		query.setEid(req.getEid());
		SpaShop spa =  req.getBody();
		shopDao.edit(spa);//编辑商品名称，提成层级
		List<MemberAddress> memberAddressList = new ArrayList<MemberAddress>();
		memberAddressList.add(spa.getAddress());
		memberAddressList.add(spa.getDepotAddress());
		memberAddressDao.updateList(memberAddressList);
		List<ShopBonus> shopBonusList = spa.getShopBonusList();
		shopBonusDao.updateList(shopBonusList);
		List<SpaShop> list = shopDao.list(query);
		resp.setBody(list);
		return resp;
	}
	/**
	 * 查询轮播图urls,按顺序返回
	 */
	@Override
	public CommObjResponse<List<SpaImg>> shopsImgList(SpaRequest<SpaImg> req) {
		CommObjResponse<List<SpaImg>> resp = new CommObjResponse<List<SpaImg>>();
		List<SpaImg> imgList = shopDao.shopsImgList(req.getBody());
		resp.setBody(imgList);
		return resp;
	}
}
