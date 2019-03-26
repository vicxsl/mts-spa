package com.qisen.mts.spa.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.dao.SpaMallOrderDao;
import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.entity.SpaShop;
import com.qisen.mts.spa.model.request.SpaRequest;

import net.rubyeye.xmemcached.MemcachedClient;

@Service
public class SpaMallOrderServiceImpl implements SpaMallOrderService {

	@Autowired
	private SpaMallOrderDao spaMallOrderDao;

	@Autowired
	private SpaInoutDepotDetailDao inoutDepotDetailDao;

	@Autowired
	private MemberAddressDao memberAddressDao;

	@Autowired
	private GoodsShopCarDao goodsShopCarDao;
	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;

	@Value("#{configProperties['unifiedorderUrl']}")
	private String unifiedorderUrl;
	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	public CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) throws Exception {
		CommObjResponse<SpaMallOrder> resp = new CommObjResponse<SpaMallOrder>();
		SpaMallOrder body = req.getBody();
		if (!CollectionUtils.isEmpty(body.getGoodsList())) {
			int eid = req.getEid();
			String appid = req.getAppid();
			String openid = req.getToken();
			SpaShop shop = JSONObject.toJavaObject(JSONObject.parseObject(memcachedClient.get(appid)), SpaShop.class);
			;
			spaMallOrderDao.create(body);// 插入订单表
			int orderId = body.getId();
			JSONObject wxpayObject = wxPay(shop, body);//生成微信预支付单
			body.setStatus("0");
			List<SpaInoutDepotDetail> details = body.getGoodsList();
			for (SpaInoutDepotDetail spaInoutDepotDetail : details) {
				spaInoutDepotDetail.setOrderId(orderId);
				spaInoutDepotDetail
						.setTotalMoney(spaInoutDepotDetail.getNum() * spaInoutDepotDetail.getPreferencePrice());
			}
			// 执行明细表的插入或修改操作
			inoutDepotDetailDao.saveList(details);
			goodsShopCarDao.deleteByOrder(body);
			MemberAddress address = body.getMemberAddress();
			address.setAppid(appid);
			address.setEid(eid);
			address.setOpenid(openid);
			address.setOrderId(orderId);
			memberAddressDao.create(address);
			body.setWxpayObject(wxpayObject);
			resp.setBody(body);
		}
		return resp;
	}

	@Override
	public CommObjResponse<List<SpaMallOrder>> list(SpaRequest<SpaMallOrder> req) {
		SpaMallOrder body = req.getBody();
		CommObjResponse<List<SpaMallOrder>> response = new CommObjResponse<List<SpaMallOrder>>();
		List<SpaMallOrder> list = spaMallOrderDao.list(body);
		for (SpaMallOrder order : list) {
			List<SpaInoutDepotDetail> goodsList = order.getGoodsList();
			for (SpaInoutDepotDetail detail : goodsList) {
				String imgurl = detail.getImgUrl();
				detail.setImgUrl(ImgCos + imgurl);
			}
		}
		response.setBody(list);
		return response;
	}


	public JSONObject wxPay(SpaShop shop, SpaMallOrder body) throws Exception {
		JSONObject wxpayObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		String key = shop.getSecret();//微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置   API密钥是交易过程生成签名的密钥
		dataMap.put("appid", shop.getAppid()); // 公众账号ID
		dataMap.put("mch_id", shop.getMchId()); // 商户号
		dataMap.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串，长度要求在32位以内。
		dataMap.put("body", body.getOpenid()); // 商品描述
		dataMap.put("out_trade_no", body.getId() + ""); // 商品订单号
		Math.round(body.getTotalMoney() * 100);
		dataMap.put("total_fee", String.valueOf(Math.round(body.getTotalMoney() * 100))); // 商品金
		dataMap.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress()); // 客户端ip
		dataMap.put("notify_url", "www.baidu.com"); // 通知地址(假设是百度)
		dataMap.put("trade_type", "JSAPI"); // 交易类型
		dataMap.put("openid", body.getOpenid());// 用户openid
		// 生成签名
		String signature = WXPayUtil.generateSignature(dataMap, key);
		dataMap.put("sign", signature);// 签名
		// 将类型为map的参数转换为xml
		String requestXml = WXPayUtil.mapToXml(dataMap);
		// 发送参数,调用微信统一下单接口,返回xml
		String responseXml = doPost(unifiedorderUrl, requestXml);
		Map<String, String> map = WXPayUtil.xmlToMap(responseXml);
		if (map.get("return_code").toString().equals("SUCCESS")
				&& map.get("result_code").toString().equals("SUCCESS")) {
			map.get("prepay_id");
			wxpayObject.put("package", "prepay_id="+map.get("prepay_id"));
			wxpayObject.put("nonceStr", dataMap.get("nonce_str"));

			wxpayObject.put("key", key);
			
		}
		return wxpayObject;
	}

	//
	// @RequestMapping("/notifyUrl")
	// public String notifyUrl(String unifiedorderUrl, String requestXml) {
	// System.out.print("h");
	// return "回调成功";
	//
	// }
	public static String doPost(String url, String requestXml) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		// 创建httpClient连接对象
		httpClient = HttpClients.createDefault();
		// 创建post请求连接对象
		HttpPost httpPost = new HttpPost(url);
		// 创建连接请求对象,并设置连接参数
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000) // 连接服务区主机超时时间
				.setConnectionRequestTimeout(60000) // 连接请求超时时间
				.setSocketTimeout(60000).build(); // 设置读取响应数据超时时间
		// 为httppost请求设置参数
		httpPost.setConfig(requestConfig);
		// 将上传参数放到entity属性中
		httpPost.setEntity(new StringEntity(requestXml, "UTF-8"));
		// 添加头信息
		httpPost.addHeader("Content-type", "text/xml");
		String result = "";
		try {
			// 发送请求
			httpResponse = httpClient.execute(httpPost);
			// 从相应对象中获取返回内容
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString((org.apache.http.HttpEntity) entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
}
