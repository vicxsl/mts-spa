package com.qisen.mts.spa.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.qisen.mts.common.model.MsgCode;
import com.qisen.mts.common.model.request.PageRequest;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.common.model.response.PageResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.dao.IncomeDetailsDao;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.MemberDao;
import com.qisen.mts.spa.dao.ShopDao;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.dao.SpaMallOrderDao;
import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.ShopBonus;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.entity.SpaMember;
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
	private MemberDao memberDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private IncomeDetailsDao incomeDetailsDao;
	
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
			if (shopStr != null) {
				shop = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), SpaShop.class);
			} else {
				shop = shopDao.queryByAppId(appid);
			}

			Integer orderId = body.getId();
			if (orderId != null) {
				order = body;
			} else {
				spaMallOrderDao.create(body);// 插入订单表
				orderId = body.getId();
				order = body;
				logger.debug(orderId+"",body,order);
				order.setStatus("1");
				List<SpaInoutDepotDetail> details = body.getGoodsList();// 生成出入库明细
				for (SpaInoutDepotDetail spaInoutDepotDetail : details) {
					spaInoutDepotDetail.setOrderId(orderId);
					spaInoutDepotDetail
							.setTotalMoney(spaInoutDepotDetail.getNum() * spaInoutDepotDetail.getPreferencePrice());
				}
				// 执行明细表的插入或修改操作
				inoutDepotDetailDao.saveList(details);
				goodsShopCarDao.deleteByOrder(body);// 更新购物车
				MemberAddress address = body.getMemberAddress();
				address.setAppid(appid);
				address.setEid(eid);
				address.setOpenid(openid);
				address.setOrderId(orderId);
				memberAddressDao.create(address);
			}
			if (order.getStatus().equals("1")) {
				JSONObject wxpayObject = wxPay(shop, order);// 生成微信预支付单
				order.setWxpayObject(wxpayObject);
			}
			resp.setBody(body);
		}
		return resp;
	}
	/**
	 * 订单发货
	 */
	@Override
	public BaseResponse sendGoods(SpaRequest<SpaMallOrder> req) throws Exception {
		BaseResponse resp = new BaseResponse();
		SpaMallOrder spa = req.getBody();
		int count = spaMallOrderDao.sendGoods(spa);
		if(count == 0){
			resp.setCode(MsgCode.ORDER_SEND_FAILD);
		}else{
			incomeDetailsDao.sendGoods(spa);
		}
		return resp;
	}
	
	/**
	 * 订单确定收货
	 */
		@Override
		public BaseResponse confirmGoods(SpaRequest<SpaMallOrder> req) throws Exception {
			BaseResponse resp = new BaseResponse();
			SpaMallOrder spa = req.getBody();
			int count = spaMallOrderDao.confirmGoods(spa);
			if(count == 0){
				resp.setCode(MsgCode.ORDER_SEND_FAILD);
				
			}else{
				incomeDetailsDao.confirmGoods(spa);
				memberDao.addBalance(spa);
			}
			return resp;
		}
		
	@Override
	public PageResponse<List<SpaMallOrder>> list(PageRequest<SpaMallOrder> req) {
		SpaMallOrder body = req.getBody();
		PageResponse<List<SpaMallOrder>> resp = new PageResponse<List<SpaMallOrder>>();
		List<SpaMallOrder> list = spaMallOrderDao.list(body,req.getStartIndex(),req.getPageSize());
		for (SpaMallOrder order : list) {
			List<SpaInoutDepotDetail> goodsList = order.getGoodsList();
			for (SpaInoutDepotDetail detail : goodsList) {
				String imgurl = detail.getImgUrl();
				detail.setImgUrl(ImgCos + imgurl);
			}
		}
		resp.setBody(list);
		return resp;
	}

	// 调用微信统一生成订单接口
	public JSONObject wxPay(SpaShop shop, SpaMallOrder body) throws Exception {
		JSONObject wxpayObject = new JSONObject();
		HashMap<String, String> dataMap = new HashMap<>();
		String orderId = body.getId() + "";
		String appid = shop.getAppid();
		String key = shop.getSecret();// 微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置,API密钥是交易过程生成签名的密钥
		dataMap.put("appid", appid); // 公众账号ID
		dataMap.put("mch_id", shop.getMchId()); // 商户号
		dataMap.put("nonce_str", WXPayUtil.generateNonceStr()); // 随机字符串，长度要求在32位以内。
		dataMap.put("body", body.getOpenid()); // 商品描述
		dataMap.put("out_trade_no", orderId); // 商品订单号
		dataMap.put("total_fee", String.valueOf(Math.round(body.getRealFee() * 100))); // 商品金
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
		if (map.get("return_code").toString().equals("SUCCESS")){
			
		if(map.get("result_code").toString().equals("SUCCESS")) {
			map.get("prepay_id");
			wxpayObject.put("package", "prepay_id=" + map.get("prepay_id"));
			wxpayObject.put("nonceStr", dataMap.get("nonce_str"));
			wxpayObject.put("key", key);
			wxpayObject.put("key", key);// paySignStr

		}else if(map.get("result_code").toString().equals("FAIL")) {
			if(map.get("err_code_des").toString().equals("该订单已支付")){
				spaMallOrderDao.updatePayStatus(orderId, appid, body.getRealFee()+"");
			}

		}
		}
		return wxpayObject;
	}

	// 向微信服务器发送请求
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
	// 支付成功后触发的回调函数，更改 订单状态，生成推广获益
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
			// 将字符串转为map
			Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
			// 判断是否支付成功
			if (resultMap.get("result_code").equals("SUCCESS")) {// 回调成功
				String appid = resultMap.get("appid");
				String realFee = new DecimalFormat("0.00").format(Double.valueOf(resultMap.get("total_fee")) / 100);// 订单总金额单位由分转为元
				String orderId = resultMap.get("out_trade_no");// 系统订单id
				SpaMallOrder order = spaMallOrderDao.getOrder(orderId, appid, realFee);
				if (order == null || order.getId() == null) {
					// 不存在此订单
					returnStr = setXML("FAIL", "不存在此订单");
				} else {
					// 校验签名
					SpaShop shop = JSONObject.toJavaObject(JSONObject.parseObject(memcachedClient.get(appid)),
							SpaShop.class);
					// 生成签名
					String signature = WXPayUtil.generateSignature(resultMap, shop.getSecret());
					logger.debug(resultMap.get("sign"), "签名对比", signature,shop);
					if (resultMap.get("sign").equals(signature)) {
						spaMallOrderDao.updatePayStatus(orderId, appid, realFee);
						String openid = resultMap.get("openid");// 用户openid
						SpaMember member = new SpaMember();
						member.setAppid(appid);
						member.setOpenid(openid);
						member = memberDao.getMember(member);
						createShopBonus(shop, member, order);// 推广收益
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

	// 推广获益
	public void createShopBonus(SpaShop shop, SpaMember member, SpaMallOrder order) {
		int num = shop.getBonusNum();
		if (0 < num) {
			//启用新零售推广机制
			List<SpaIncomeDetails> list = new ArrayList<SpaIncomeDetails>();
			List<ShopBonus> shopBonusList = shop.getShopBonusList();
			if(shopBonusList != null && shopBonusList.size() > 0){
				for (ShopBonus shopBonus : shop.getShopBonusList()) {
					int bonusLevel = shopBonus.getBonusLevel();
					String bonusType = shopBonus.getBonusType();
					double bonusValue = shopBonus.getBonusValue();
					String bonusOpenid = null;
					SpaIncomeDetails details = new SpaIncomeDetails();
					details.setBuyOpenid(member.getOpenid());
					details.setAppid(member.getAppid());
					details.setEid(member.getEid());
					details.setName(member.getName());
					details.setOrderId(order.getId());
					double money = 0;
					double realFee=order.getRealFee();//订单支付金额
					double orderProfit=order.getOrderProfit();//订单利润
					if (bonusLevel == 1) {
						// 第一层推广收益
						bonusOpenid = member.getRecommendOneId();
					} else if (bonusLevel == 2) {
						// 第二层推广收益
						bonusOpenid = member.getRecommendTwoId();
					} else if (bonusLevel == 3) {
						// 第二层推广收益
						bonusOpenid = member.getRecommendThreeId();
					}
					if(num >= bonusLevel && null != bonusOpenid){
						//启用到这一级推广并存在这一级推广人员
						details.setLevel(bonusLevel + "");
						details.setOpenid(bonusOpenid);
						if("1".equals(bonusType)){
							money = bonusValue;
						}else if ("2".equals(bonusType)) {
							money = realFee * bonusValue / 100;
						}else if ("3".equals(bonusType) && 0 < orderProfit) {
							money = orderProfit  * bonusValue / 100;
						}
						details.setMoney(money);
						list.add(details);
						logger.debug("huolishuju");
	
						logger.debug(bonusValue+"");
	
						logger.debug(money+"");
	
						logger.debug(details.toString());
						logger.debug(realFee+"");
						logger.debug(orderProfit+"");
					}
				}
				if(0 < list.size()){
					//生成推广收益单
					incomeDetailsDao.saveList(list);
					//更新收益人 推荐总金额
					memberDao.updateTotalMoney(list);
				}
			}
		}
	}
}
