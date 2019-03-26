package com.qisen.mts.spa.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.MemberDao;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaGoodsShopCar;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaMyInfoGains;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ShopDao shopDao;

	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;

	@Autowired
	private MemcachedClient memcachedClient;
	/**
	 * 商城登录
	 */
	@Override
	public CommObjResponse<MetaData> login(SpaRequest<SpaMember> req) throws Exception{
		CommObjResponse<MetaData> resp = new CommObjResponse<MetaData>();
		SpaMember query = req.getBody();
		String appid = query.getAppid();
		String js_code = query.getJs_code();// wx.login临时js_code
		String shopStr = memcachedClient.get(appid);//从缓存中取商户信息
		SpaShop shop = new SpaShop();
		String secret = "";//会话密钥
		if(shopStr!=null){
			shop = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), SpaShop.class);
			secret = shop.getSecret();
		}else{
			secret = shopDao.getSecret(appid);
		}
		JSONObject wxObject = MemberServiceImpl.getSessionKeyOropenid(js_code, appid, secret);
		String openid = wxObject.getString("openid");
		String session_key = wxObject.getString("session_key");
		query.setOpenid(openid);
		query.setSession_key(session_key);
		memberDao.saveOrUpdate(query);//新增或者更新会员
		MetaData metaData = memberDao.getMallMetaData(query);//查询metaData信息
		if(shopStr==null){
			shop=metaData.getShop();
//			添加到缓存中,两小时内有效
			memcachedClient.add(appid, ConfigConsts.MAX_META_DATA_INTERVAL, JSON.toJSONString(shop));
			String string = memcachedClient.get(appid);
			System.out.println("--------------------------------");
			System.out.println(string);
			
			System.out.println("--------------------------------");
		}
		List<SpaGoodsShopCar> carList =metaData.getShopCarList();
		if(carList != null && carList.size() > 0){
			for(SpaGoodsShopCar car:carList){
				car.setImgUrl(ImgCos+car.getImgUrl());
			}
		}
		resp.setBody(metaData);
		return resp;
	}

	/**
	 * 获取微信小程序 session_key 和 openid
	 *
	 * @param code 调用微信登陆返回的Code
	 * @return
	 */
	public static JSONObject getSessionKeyOropenid(String js_code, String appid, String secret) {
		// 微信端登录code值
		String requestUrl = "https://api.weixin.qq.com/sns/jscode2session"; // 请求地址
																			// https://api.weixin.qq.com/sns/jscode2session
		Map<String, String> requestUrlParam = new HashMap<String, String>();
		requestUrlParam.put("appid", appid); // 开发者设置中的appid
		requestUrlParam.put("secret", secret); // 开发者设置中的appSecret
		requestUrlParam.put("js_code", js_code); // 小程序调用wx.login返回的code
		requestUrlParam.put("grant_type", "authorization_code"); // 默认参数 authorization_code

		// 发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
		JSONObject jsonObject = JSON.parseObject(sendPost(requestUrl, requestUrlParam));
		return jsonObject;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url 发送请求的 URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, Map<String, ?> paramMap) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		String param = "";
		Iterator<String> it = paramMap.keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			param += key + "=" + paramMap.get(key) + "&";
		}

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public CommObjResponse<List<SpaMember>> memberIncomeDetailsList(SpaRequest<SpaMember> req) {
		CommObjResponse<List<SpaMember>> resp = new CommObjResponse<List<SpaMember>>();
		Map<String, Double> totalMap = new HashMap<String,Double>();
		List<SpaMember> levelOne = memberDao.profitLevelOne(req.getBody());//一级获利
		if(CollectionUtils.isEmpty(levelOne)){
			return resp;
		}
		List<SpaMember> levelTwo = memberDao.levelTwo(levelOne,levelOne.get(0).getEid().toString());//二级
		List<SpaMember> levelThree = memberDao.levelThree(levelOne,levelOne.get(0).getEid().toString());//三级
		
		for(SpaMember spaMember :levelTwo){
			totalMap.put(spaMember.getOpenid(), spaMember.getBalance());
		}
		
		for(SpaMember spaMember :levelThree){
			if(null!=totalMap.get(spaMember.getOpenid())){
				totalMap.put(spaMember.getOpenid(), 
						totalMap.get(spaMember.getOpenid())+spaMember.getBalance());
			}
		}
		
		for(SpaMember spaMember :levelOne){
			if(null!=totalMap.get(spaMember.getOpenid())){
				spaMember.setTotalMoney(spaMember.getBalance()+
						totalMap.get(spaMember.getOpenid()));
			}
		}
		
		resp.setBody(levelOne);
		
		return resp;
	}

	@Override
	public CommObjResponse<SpaMyInfoGains> myInfoGains(SpaRequest<SpaMyInfoGains> req) {
		CommObjResponse<SpaMyInfoGains> resp = new CommObjResponse<SpaMyInfoGains>();
		SpaMyInfoGains spa = memberDao.myInfoGains(req.getBody());//一级获利
		
		resp.setBody(spa);
		
		return resp;
	}
}
