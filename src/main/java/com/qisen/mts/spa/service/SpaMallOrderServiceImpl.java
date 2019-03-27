package com.qisen.mts.spa.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.ShopDao;
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
	private ShopDao shopDao;
	
	@Autowired
	private GoodsShopCarDao goodsShopCarDao;
	@Value("#{configProperties['ImgCos']}")
	private String ImgCos;

	@Value("#{configProperties['unifiedorderUrl']}")
	private String unifiedorderUrl;

	@Value("#{configProperties['notify_url']}")
	private String notify_url;
	@Autowired
	private MemcachedClient memcachedClient;

	private static final Logger logger = LoggerFactory.getLogger(SpaMallOrderServiceImpl.class);

	@Override
	public CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) throws Exception {
		CommObjResponse<SpaMallOrder> resp = new CommObjResponse<SpaMallOrder>();
		SpaMallOrder body = req.getBody();
		SpaMallOrder order = new SpaMallOrder();
		if (!CollectionUtils.isEmpty(body.getGoodsList())) {
			Integer eid = req.getEid();
			String appid = req.getAppid();
			String openid = req.getToken();
			SpaShop shop = new SpaShop();
			String shopStr = memcachedClient.get(appid);
			if(shopStr !=null){
				shop = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), SpaShop.class);
			}else{
				shop = shopDao.queryByAppId(appid);
			}
					
			Integer orderId = body.getId();
			if(orderId != null){
				order = body;
			}else{
				spaMallOrderDao.create(body);// 插入订单表
				orderId = body.getId();
				order = body;
				order.setStatus("0");
				List<SpaInoutDepotDetail> details = body.getGoodsList();//生成出入库明细
				for (SpaInoutDepotDetail spaInoutDepotDetail : details) {
					spaInoutDepotDetail.setOrderId(orderId);
					spaInoutDepotDetail
							.setTotalMoney(spaInoutDepotDetail.getNum() * spaInoutDepotDetail.getPreferencePrice());
				}
				// 执行明细表的插入或修改操作
				inoutDepotDetailDao.saveList(details);
				goodsShopCarDao.deleteByOrder(body);//更新购物车
				MemberAddress address = body.getMemberAddress();
				address.setAppid(appid);
				address.setEid(eid);
				address.setOpenid(openid);
				address.setOrderId(orderId);
				memberAddressDao.create(address);
			}
			if(order.getStatus().equals("0")){
				JSONObject wxpayObject = wxPay(shop, order);// 生成微信预支付单
				order.setWxpayObject(wxpayObject);
			}
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
		String key = shop.getSecret();// 微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置,API密钥是交易过程生成签名的密钥
		dataMap.put("appid", shop.getAppid()); // 公众账号ID
		dataMap.put("mch_id", shop.getMchId()); // 商户号
		dataMap.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串，长度要求在32位以内。
		dataMap.put("body", body.getOpenid()); // 商品描述
		dataMap.put("out_trade_no", body.getId() + ""); // 商品订单号
		dataMap.put("total_fee", String.valueOf(Math.round(body.getTotalMoney() * 100))); // 商品金
		dataMap.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress()); // 客户端ip
		dataMap.put("notify_url", notify_url); // 通知地址(假设是百度)
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
			wxpayObject.put("package", "prepay_id=" + map.get("prepay_id"));
			wxpayObject.put("nonceStr", dataMap.get("nonce_str"));
			wxpayObject.put("key", key);
			wxpayObject.put("key", key);// paySignStr

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

	@Override
	public void changePayStatus(ServletRequest req, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		InputStream inStream = req.getInputStream();
		PrintWriter writer = response.getWriter();
		String returnStr = setXML("FAIL", "");
		int _buffer_size = 1024;
		if (inStream != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] tempBytes = new byte[_buffer_size];
			int count = -1;
			while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
				outStream.write(tempBytes, 0, count);
			}
			tempBytes = null;
			outStream.flush();
			// 将流转换成字符串
			String result = new String(outStream.toByteArray(), "UTF-8");
			logger.debug("--------------接收到的信息---------------------");

			logger.debug(result, "");

			logger.debug("-----------------------------------");
			Map<String, String> resultMap = WXPayUtil.xmlToMap(result);

			logger.debug("-----------------------------------");
			// 将XML格式转化成MAP格式数据
			// 后续具体自己实现
			// if(){
			//
			// }
			if (resultMap.get("result_code").equals("SUCCESS")) {// 回调成功
				String appid = resultMap.get("appid");
				String totalMoney = new DecimalFormat("0.00").format(Double.valueOf(resultMap.get("total_fee")) / 100);
				String orderId = resultMap.get("out_trade_no");
				logger.debug(appid, "查询数据", totalMoney, orderId);
				SpaMallOrder order = spaMallOrderDao.getOrder(orderId, appid, totalMoney);
				logger.debug(order.toString(), "存在此订单");
				if (order == null || order.getId() == null) {
					// 不存在此订单
					returnStr = setXML("FAIL", "不存在此订单");
				} else {
					// 校验签名
					SpaShop shop = JSONObject.toJavaObject(JSONObject.parseObject(memcachedClient.get(appid)),
							SpaShop.class);
					// 生成签名
					String signature = WXPayUtil.generateSignature(resultMap, shop.getSecret());
					logger.debug(resultMap.get("sign"), "签名对比", signature);
					if (resultMap.get("sign").equals(signature)) {
						spaMallOrderDao.updatePayStatus(orderId, appid, totalMoney);
						returnStr = setXML("SUCCESS", "OK");
					} else {
						// 签名错误
						returnStr = setXML("FAIL", "签名错误");
					}
				}

			} else {// 回调失败
				returnStr = setXML("FAIL", resultMap.get("return_msg"));

			}
		}

		writer.write(returnStr);
		writer.flush();
		writer.close();
	}

	// 设置通知微信回调内容
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";

	}
}
