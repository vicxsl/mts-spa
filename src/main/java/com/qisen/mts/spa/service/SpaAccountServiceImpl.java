package com.qisen.mts.spa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsSubTypeDao;
import com.qisen.mts.spa.dao.GoodsTypeDao;
import com.qisen.mts.spa.dao.SpaAccountDao;
import com.qisen.mts.spa.dao.SpaInoutDepotTypeDao;
import com.qisen.mts.spa.model.SessionSpa;
import com.qisen.mts.spa.model.entity.SpaAccount;
import com.qisen.mts.spa.model.entity.SpaGoodsSubType;
import com.qisen.mts.spa.model.entity.SpaGoodsType;
import com.qisen.mts.spa.model.entity.SpaInoutDepotType;
import com.qisen.mts.spa.model.request.SpaRequest;

import net.rubyeye.xmemcached.MemcachedClient;
@Service
public class SpaAccountServiceImpl implements SpaAccountService{
	
	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private SpaAccountDao spaAccountDao;
	@Autowired
	private GoodsTypeDao goodsTypeDao;
	@Autowired
	private GoodsSubTypeDao goodsSubTypeDao;
	@Autowired
	private SpaInoutDepotTypeDao spaInoutDepotTypeDao;
	//七牛云图片路径PHOTO_PATH
	@Value("#{configProperties['PHOTO_PATH']}")
	private String PHOTO_PATH;
	//七牛云AccessKey
	@Value("#{configProperties['AccessKey']}")
	private String AccessKey;
	//七牛云SecretKey
	@Value("#{configProperties['SecretKey']}")
	private String SecretKey;
	//七牛云BucketName
	@Value("#{configProperties['BucketName']}")
	private String BucketName;
	//七牛云QINIU_IMAGE_DOMAIN
	@Value("#{configProperties['QINIU_IMAGE_DOMAIN']}")
	private String QINIU_IMAGE_DOMAIN;
	
	/**
	 * 新增spa账号
	 */
	@Override
	public BaseResponse save(SpaRequest<SpaAccount> req) {
		BaseResponse resp = new BaseResponse();
		SpaAccount spaAccount = req.getBody();
		int count = spaAccountDao.check(spaAccount);
		if(spaAccount.getId() != null && spaAccount.getId() > 0){
			if (count == 0 ) {
				spaAccountDao.update(spaAccount);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}else{
			if (count == 0 ) {
				spaAccountDao.create(spaAccount);
			}else {
				resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
			}
		}
		return resp;
	}

	/**
	 * 删除spa账号
	 */
	@Override
	public BaseResponse delete(SpaRequest<SpaAccount> req) {
		BaseResponse resp = new BaseResponse();
		SpaAccount spaAccount = req.getBody();
		int count = spaAccountDao.delete(spaAccount);
		if (count == 0 ) {
			resp.setCode(MsgCode.COMMON_MOBILE_EXIST);
		}
		
		return resp;
	}

	@Override
	public CommObjResponse<SessionSpa> login(SpaRequest<SpaAccount> req) throws Exception {
		CommObjResponse<SessionSpa> resp = new CommObjResponse<>();
		SpaAccount spaAccount = spaAccountDao.findSpaAccount(req.getBody().getMobile());
		if (spaAccount != null) {
			if (spaAccount.getPassword().equals(req.getBody().getPassword().trim())) {
				if (spaAccount.getStatus().equals("0")) {
					SessionSpa sessionSpa = spaAccount.toJSON().toJavaObject(SessionSpa.class);
					Integer eid = spaAccount.getEid();
					String appid = spaAccount.getAppid();
					//出入库类型
					SpaInoutDepotType inoutDepotType = new SpaInoutDepotType();
					SpaGoodsType goodsType = new SpaGoodsType();//商品类型
					SpaGoodsSubType goodsSubType = new SpaGoodsSubType();//商品子类型
					goodsType.setEid(eid);
					goodsType.setAppid(appid);
					goodsSubType.setEid(eid);
					goodsSubType.setAppid(appid);
					inoutDepotType.setEid(eid);
					inoutDepotType.setAppid(appid);
					List<SpaGoodsType> goodsTypeList= goodsTypeDao.list(goodsType);
					List<SpaGoodsSubType> goodsSubTypeList= goodsSubTypeDao.list(goodsSubType);
					List<SpaInoutDepotType> inOutDepotTypeList =  spaInoutDepotTypeDao.list(inoutDepotType);
//					List<SpaGoodsSupplier> goodsSupplierList= spaGoodsSupplierDao.list(goodsSupplier);
//					List<SpaGoodsCompany> goodsCompanyList= spaGoodsCompanyDao.list(goodsCompany);
					sessionSpa.setMeta("goodsTypeList", goodsTypeList);
					sessionSpa.setMeta("goodsSubTypeList", goodsSubTypeList);
					sessionSpa.setMeta("inOutDepotTypeList", inOutDepotTypeList);
					JSONObject imgObject = new JSONObject();
					imgObject.put("PHOTO_PATH", PHOTO_PATH);
					imgObject.put("AccessKey", AccessKey);
					imgObject.put("SecretKey", SecretKey);
					imgObject.put("BucketName", BucketName);
					imgObject.put("QINIU_IMAGE_DOMAIN", QINIU_IMAGE_DOMAIN);
					sessionSpa.setMeta("imgObject", imgObject);//设置图片对象
					String token = UUID.randomUUID().toString();
					sessionSpa.setToken(token);
					String sessionSpaKey = ConfigConsts.SESSION_SPA + token;
					String spaInfoJsonStr = sessionSpa.toString();
					if (!memcachedClient.add(sessionSpaKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, spaInfoJsonStr)) {
						memcachedClient.replace(sessionSpaKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, spaInfoJsonStr);
					}
					resp.setBody(sessionSpa);
				}else{
					resp.setCode(MsgCode.ACCOUNT_FORBIDDEN);
				}
			}else{
				resp.setCode(MsgCode.ACCOUNT_PWD_NOT_MATCHED);
			}
		}else{
			resp.setCode(MsgCode.ACCOUNT_NOT_EXIST);
		}
		return resp;
	}

	/*
	 * 退出登录
	 */
	@Override
	public BaseResponse loginOut(SpaRequest<SpaAccount> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		String sessionSpaKey = ConfigConsts.SESSION_SPA + req.getToken();
		memcachedClient.delete(sessionSpaKey);
		return resp;
	}
	
	/**
	 * 查询spa账号
	 */
	@Override
	public CommObjResponse<List<SpaAccount>> list(SpaRequest<SpaAccount> req) {
		CommObjResponse<List<SpaAccount>> resp = new CommObjResponse<List<SpaAccount>>();
		SpaAccount spaAccount = req.getBody();
		List<SpaAccount>  spaList = spaAccountDao.list(spaAccount);
		resp.setBody(spaList);
		return resp;
	}
	
}
