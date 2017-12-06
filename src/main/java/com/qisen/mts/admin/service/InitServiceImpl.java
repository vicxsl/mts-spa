package com.qisen.mts.admin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.dao.ConsumeLogDao;
import com.qisen.mts.admin.dao.GoodsDao;
import com.qisen.mts.admin.dao.InitDao;
import com.qisen.mts.admin.model.entity.ConsumeLog;
import com.qisen.mts.admin.model.entity.Goods;
import com.qisen.mts.admin.model.request.AdminRequest;
import com.qisen.mts.common.dao.sys.ShopSettingDao;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.util.JSONUtil;
import com.qisen.mts.common.util.encrypt.Encrypt;
import com.qisen.mts.gateway.ExceptionWithCode;

@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private JSONUtil jsonUtil;
	@Autowired
	private InitDao initDao;
	@Autowired
	private ConsumeLogDao consumeLogDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ShopSettingDao shopSettingDao;

	@Override
	public BaseResponse createEnterprise(AdminRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Enterprise enterprise =  req.getBody().getJSONObject("enterprise").toJavaObject(Enterprise.class);//.toJSON().toJavaObject(Shop.class)
		enterprise.setTbuser("CHALI");
		Integer result = initDao.createEnterprise(enterprise);
		if (result != null && result == 1) {
			Integer eid = enterprise.getId();
			// 初始数据
			JSONObject baseData = jsonUtil.readJSON("initData.json", req.getLang());
			//创建门店
			Shop shop = new Shop();
			shop.setAddress(enterprise.getAddress());
			shop.setAreaId(enterprise.getAreaId());
			shop.setEmpId(enterprise.getEmpId());
			shop.setTempId(enterprise.getTempId());
			shop.setInputDate(enterprise.getInputDate());
			shop.setLinkman(enterprise.getLinkman());
			shop.setMobile(enterprise.getMobile());
			shop.setName(enterprise.getName());
			shop.setOrgNo(enterprise.getOrgNo());
			shop.setPhone(enterprise.getPhone());
			JSONObject prop = new JSONObject();
			JSONArray departments = baseData.getJSONArray("departments");
			JSONArray gtypes = baseData.getJSONArray("gtypes");
			prop.put("departments", departments);
			prop.put("gtypes", gtypes);
			shop.setProp(prop);
			shop.setServorGno(enterprise.getServorGno());
			shop.setRemark(enterprise.getRemark());
			result = this.createShop(eid, eid, shop, req.getBody().getString("admin"), req.getLang());
			if (result == null || result < 1) {
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);

			}
			// 初始企业配置
			result = shopSettingDao.create(eid, null, baseData.getJSONArray("shopSetting"));
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			// 部门数据
			result = initDao.createDepartmentBatch(eid, baseData.getJSONArray("departments"));
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			// 级别类型
			result = initDao.createGtypeBatch(eid, baseData.getJSONArray("gtypes"));
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			// 默认支付类型
			result = initDao.createBaseTypeBatch(eid, null, baseData.getJSONArray("payTypes"));
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			// 初始 账户
			Account account = new Account();
			account.setEid(eid);
			account.setAccount("admin" + eid);
			account.setPwd(Encrypt.Md5("1"));
			account.setRole(1);
			result = initDao.createAccount(account);
			if (result == null || result < 1)
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			// 生成数据表
			initDao.createBusiBill(eid);
			initDao.createBusiBillDetail(eid);
			initDao.createBusiEmpFee(eid);
			initDao.createBusiPay(eid);
			initDao.createMemCardRecord(eid);
			initDao.createSysLog(eid);
		}
		return resp;
	}

	@Override
	public BaseResponse createShop(AdminRequest<JSONObject> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		Shop shop = req.getBody().getJSONObject("shop").toJavaObject(Shop.class);
		Integer result = this.createShop(shop.getEid(), shop.getEid(), shop, req.getBody().getString("admin"), req.getLang());
		if (result == null || result < 1)
			resp.setResult(ResultCode.FAILED);
		return resp;
	}

	private Integer createShop(Integer eid, Integer dataEid, Shop shop, String name, String lang) throws Exception {
		Integer result = 1;
		Integer maxShopId = initDao.getMaxShopId(eid);
		if (maxShopId == null || maxShopId == 0)
			maxShopId = Integer.valueOf(eid + "0001");
		else {
			maxShopId++;
			// 更新企业连锁标志
			result = initDao.updateShopFlag(eid);
		}
		if (result != null && result == 1) {
			shop.setId(maxShopId);
			result = initDao.createShop(eid, dataEid, shop);
			int sid = initDao.selectSid(eid, shop.getName());
			Goods goods = goodsDao.list(1).get(0);
			ConsumeLog consumeLog = new ConsumeLog();
			consumeLog.setEid(eid);
			consumeLog.setSid(sid);
			consumeLog.setClassNo(goods.getClassNo());
			consumeLog.setProductNo(goods.getNo());
			consumeLog.setNum(1);
			consumeLog.setConsumeFee(goods.getPrice());
			consumeLog.setPrice(goods.getPrice());
			consumeLog.setCost(goods.getCost());
			consumeLog.setProfit(goods.getPrice() - goods.getCost());
			consumeLog.setOrgNo(shop.getOrgNo());
			consumeLog.setAdmin(name);
			consumeLog.setClientType("0");
			consumeLog.setRemark(shop.getRemark());
			consumeLog.setByEmpId(shop.getEmpId());
			consumeLog.setAuditFlag("0");
			consumeLog.setPayFlag("0");
			consumeLog.setPayCloseFlag("0");
			consumeLog.setCloseFlag("0");
			consumeLog.setConsumeTime(new Date());
			consumeLogDao.create(consumeLog);
			// 初始数据
			JSONObject baseData = jsonUtil.readJSON("initData.json", lang);
			// 初始企业配置
			result = shopSettingDao.create(eid, sid, baseData.getJSONArray("shopSetting"));

			if (result == null || result < 1) {
				throw new ExceptionWithCode(MsgCode.COMMON_CREATE_FAILED);
			}

		}
		return result;
	}

	@Override
	public BaseResponse eidtShop(AdminRequest<Shop> req) {
		BaseResponse resp = new BaseResponse();
		Shop shop = req.getBody();
		System.out.println(req.getBody());
		System.out.println(shop);
		int result = initDao.eidtShop(shop);
		if (result == 0)
			resp.setResult(ResultCode.FAILED);
		return resp;
	}
}
