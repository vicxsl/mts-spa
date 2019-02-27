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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.MemberDao;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ShopDao shopDao;
	
	/**
	 * 商城登录
	 */
	@Override
	public CommObjResponse<List<SpaMember>> login(SpaRequest<SpaMember> req) {
		CommObjResponse<List<SpaMember>> resp = new CommObjResponse<List<SpaMember>>();
		SpaMember body = req.getBody();
		String appid = body.getAppid();
		String js_code = body.getJs_code();
		SpaMember query = new SpaMember();
		MetaData metaData = new MetaData();
		SpaShop shop = shopDao.queryByAppId(appid);

		String secret = shop.getSecret();
		JSONObject wxObject = this.getSessionKeyOropenid(js_code,appid,secret);
		metaData.setShop(shop);//储存门店信息
		query.setEid(body.getEid());
//		query.setAppid(body.getAppid());
		query.setOpenid(body.getOpenid());
		SpaMember checkMember = memberDao.check(query);
		if (checkMember != null && checkMember.getId() > 0 ) {
			if((body.getMobile() != null && checkMember.getMobile() != body.getMobile()) || (body.getName() != null && checkMember.getName() != body.getName())){
				memberDao.update(body);
			}
		}else {
			memberDao.create(body);
		}
		return resp;
	}
	
	/**
	 * 获取微信小程序 session_key 和 openid
	 *
	 * @param code 调用微信登陆返回的Code
	 * @return
	 */
	public static JSONObject getSessionKeyOropenid(String js_code,String appid,String secret) {
	    //微信端登录code值
	    String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";  //请求地址 https://api.weixin.qq.com/sns/jscode2session
	    Map<String, String> requestUrlParam = new HashMap<String, String>();
	    requestUrlParam.put("appid", appid);  //开发者设置中的appId
	    requestUrlParam.put("secret", secret); //开发者设置中的appSecret
	    requestUrlParam.put("js_code", js_code); //小程序调用wx.login返回的code
	    requestUrlParam.put("grant_type", "authorization_code");    //默认参数 authorization_code
	 
	    //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
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
	    //使用finally块来关闭输出流、输入流
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
	
	
}
