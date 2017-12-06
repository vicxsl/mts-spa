package com.qisen.mts.admin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.AdminEnterpriseDao;
import com.qisen.mts.admin.dao.ConsumeLogDao;
import com.qisen.mts.admin.dao.GoodsDao;
import com.qisen.mts.admin.dao.InitDao;
import com.qisen.mts.admin.model.entity.ConsumeLog;
import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private AdminEnterpriseDao enterpriseDao;
	@Autowired
	private InitDao initDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ConsumeLogDao consumeLogDao;

	@Override
	public PageResponse<JSONObject> list(PageRequest<JSONObject> req) {
		JSONObject body = req.getBody();
		Integer count = enterpriseDao.count(body);
		PageResponse<JSONObject> resp = new PageResponse<>(req.getPageNum(), req.getPageSize(), count);
		if (count > 0) {
			List<Enterprise> enterprises = enterpriseDao.list(body, req.getStartIndex(), req.getEndIndex());
			System.out.println(enterprises.get(0).getId());
			List<Shop> shops = enterpriseDao.listShop(enterprises,body);
			JSONObject ob = new JSONObject();
			ob.put("enterpriseList", enterprises);
			ob.put("shopList", shops);
			resp.setBody(ob);
		}
		
		return resp;
	}

	@Override
	public CommObjResponse<Shop> shop(PageRequest<JSONObject> req) {
		CommObjResponse<Shop> resp = new CommObjResponse<>();
		Shop shops = enterpriseDao.shop(req.getBody());
		resp.setBody(shops);
		return resp;
	}

	@Override
	public BaseResponse set(AdminRequest<Enterprise> req) {
		BaseResponse resp = new BaseResponse();
		int count = enterpriseDao.set(req.getBody());
		if (count == 0) {
			resp.setCode(MsgCode.COMMON_UPDATE_FAILED);
		}
		return resp;
	}

	@Override
	public BaseResponse shopValidSet(AdminRequest<JSONObject> req) {
		BaseResponse resp = new BaseResponse();
		Shop shop = new Shop();
		shop.setEid(req.getEid());
		shop.setId(req.getSid());
		shop.setValidDate(req.getBody().getDate("validDate"));
		
		Goods goods = goodsDao.list(req.getBody().getIntValue("productNo")).get(0);
		
		ConsumeLog con = new ConsumeLog();
		con.setEid(req.getEid());
		con.setSid(req.getSid());
		con.setClassNo(goods.getClassNo());
		con.setProductNo(goods.getNo());
		con.setPrice(goods.getPrice());
		con.setNum(1);
		con.setConsumeFee(goods.getPrice()*con.getNum());
		con.setCost(goods.getCost()*con.getNum());
		con.setProfit((con.getConsumeFee()-con.getCost())*con.getNum());
		con.setConsumeTime(new Date());
		con.setOrgNo(req.getBody().getIntValue("orgNo"));
		con.setAdmin("");
		con.setByEmpId(req.getBody().getInteger("byEmpId"));
		con.setPayFlag("0");
		con.setClientType("0");
		con.setPayCloseFlag("0");
		con.setCloseFlag("0");
		con.setAuditFlag("0");
		int inCount =consumeLogDao.create(con);
		if (inCount == 0) {
			resp.setCode(MsgCode.COMMON_CREATE_FAILED);
		}else{
			int count = initDao.eidtShop(shop);
		}
		return resp;
	}

}
