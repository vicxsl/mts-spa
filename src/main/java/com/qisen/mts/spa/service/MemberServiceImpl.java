package com.qisen.mts.spa.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.qisen.mts.common.model.MsgCode;
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

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

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
		if(shopStr!=null){
			shop = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), SpaShop.class);
		}else{
			shop = shopDao.queryByAppId(appid);
		}
		String secret = shop.getSecret();//会话密钥
		JSONObject wxObject = MemberServiceImpl.getSessionKeyOropenid(js_code, appid, secret);
		String openid = wxObject.getString("openid");
		String session_key = wxObject.getString("session_key");
		query.setOpenid(openid);
		query.setEid(shop.getEid());
		int count = memberDao.check(query);
		if(count == 0 ){
			memberDao.create(query);//新增或者更新会员
		}
		MetaData metaData = memberDao.getMallMetaData(query);//查询metaData信息
		if(shopStr==null){
			shop=metaData.getShop();
//			添加到缓存中,两小时内有效
			memcachedClient.delete(appid);
			memcachedClient.add(appid, ConfigConsts.MAX_META_DATA_INTERVAL, JSON.toJSONString(shop));
		}
		SpaMember member = metaData.getMember();
		member.setSession_key(session_key);
		memcachedClient.delete(openid);
		memcachedClient.add(openid, ConfigConsts.MAX_META_DATA_INTERVAL, JSON.toJSONString(member));
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

	/**
	 * 推广收益列表
	 */
	@Override
	public CommObjResponse<List<SpaMember>> memberIncomeDetailsList(SpaRequest<SpaMember> req) {
		CommObjResponse<List<SpaMember>> resp = new CommObjResponse<List<SpaMember>>();
		List<SpaMember> list =  memberDao.memberIncomeDetailsList(req.getBody());
		resp.setBody(list);
		
		return resp;
	}

	/**
	 * 获取我的信息页面数据
	 */
	@Override
	public CommObjResponse<SpaMyInfoGains> myInfoGains(SpaRequest<SpaMyInfoGains> req) {
		CommObjResponse<SpaMyInfoGains> resp = new CommObjResponse<SpaMyInfoGains>();
		SpaMyInfoGains query = req.getBody();
		SpaMyInfoGains spa = memberDao.myInfoGains(query);
		resp.setBody(spa);
		return resp;
	}

	/**
	 * 更新用户手机号码
	 */
	@SuppressWarnings("deprecation")
	@Override
	public CommObjResponse<String> addMobile(SpaRequest<JSONObject> req) throws Exception {
		// TODO Auto-generated method stub
		CommObjResponse<String> res = new CommObjResponse<String>();
		JSONObject body = req.getBody();
		String encryptedData = body.getString("encryptedData");
		String iv = body.getString("iv");
		String openid = body.getString("openid");
		String appid = body.getString("appid");
		String memberStr = memcachedClient.get(openid);
		SpaMember member;
		if(null != memberStr){
			member = JSONObject.toJavaObject(JSONObject.parseObject(memberStr), SpaMember.class);
		}else{
			res.setCode(MsgCode.MEMBER_RELOGIN);
			return res;
		}
		String session_key = member.getSession_key(); 
		byte[] dataByte = Base64.decodeFast(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decodeFast(session_key);
        // 偏移量
        byte[] ivByte = Base64.decodeFast(iv);
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(resultByte, "UTF-8");
            JSONObject resultObj = JSONObject.parseObject(result);
            String phoneNumber = resultObj.getString("phoneNumber");
            member.setMobile(phoneNumber);
            memberDao.update(member);
            logger.debug("打印出来手机号码了么",result);
            res.setBody(phoneNumber);
        }else{
        	res.setCode(MsgCode.MEMBER_RELOGIN);
        }

		return res;
	}
}
