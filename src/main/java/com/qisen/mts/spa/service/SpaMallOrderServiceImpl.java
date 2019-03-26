package com.qisen.mts.spa.service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.wxpay.sdk.WXPayUtil;
import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.dao.GoodsShopCarDao;
import com.qisen.mts.spa.dao.MemberAddressDao;
import com.qisen.mts.spa.dao.SpaInoutDepotDetailDao;
import com.qisen.mts.spa.dao.SpaMallOrderDao;
import com.qisen.mts.spa.model.entity.MemberAddress;
import com.qisen.mts.spa.model.entity.SpaInoutDepotDetail;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.request.SpaRequest;

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

	@Override
	public CommObjResponse<SpaMallOrder> save(SpaRequest<SpaMallOrder> req) {
		CommObjResponse<SpaMallOrder> resp = new CommObjResponse<SpaMallOrder>();
		SpaMallOrder body = req.getBody();
		if (!CollectionUtils.isEmpty(body.getGoodsList())) {
			int eid = req.getEid();
			String appid = req.getAppid();
			String openid = req.getToken();
			spaMallOrderDao.create(body);// 插入订单表
			int orderId = body.getId();
//			wxPay(req,openid,orderId+"");
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
				detail.setImgUrl(ImgCos + detail.getImgUrl());
			}
		}
		response.setBody(list);
		return response;
	}
	
//	 @Value("${appid}")
//	    private String appid;
//
//	    @Value("${mchid}")
//	    private String mchId;
//
//	    @Value("${weixinKey}")
//	    private String weixinKey;
//
//	    @Value("${unifiedorderUrl}")
//	    private String unifiedorderUrl;
//
//	    /**
//	     * @param httpServletRequest
//	     * @param httpServletResponse
//	     * @param orderNo             订单号
//	     * @param money               金额
//	     * @param body                商品内容
//	     */
//	    @RequestMapping("/pay")
//	    public void pay(HttpServletRequest httpServletRequest, HttpServletResponse
//	            httpServletResponse, String orderNo, String money, String body)
//	            throws Exception {
//	        try {
//	            HashMap<String, String> dataMap = new HashMap<>();
//	            dataMap.put("appid", appid); //公众账号ID
//	            dataMap.put("mch_id", mchId); //商户号
//	            dataMap.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串，长度要求在32位以内。
//	            dataMap.put("body", body); //商品描述
//	            dataMap.put("out_trade_no", orderNo); //商品订单号
//	            dataMap.put("total_fee", money); //商品金
//	            dataMap.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress()); //客户端ip
//	            dataMap.put("notify_url", "www.baidu.com"); //通知地址(假设是百度)
//	            dataMap.put("trade_type", "NATIVE"); //交易类型
//	            dataMap.put("product_id", "1"); //trade_type=NATIVE时，此参数必传。商品ID，商户自行定义。
//	            //生成签名
//	            String signature = WXPayUtil.generateSignature(dataMap, weixinKey);
//	            dataMap.put("sign", signature);//签名
//	            //将类型为map的参数转换为xml
//	            String requestXml = WXPayUtil.mapToXml(dataMap);
//	            //发送参数,调用微信统一下单接口,返回xml
//	            String responseXml = doPost(unifiedorderUrl, requestXml);
//	            Map<String, String> map = WXPayUtil.xmlToMap(responseXml);
//	            if (map.get("return_code").toString().equals("SUCCESS") && map.get("result_code")
//	                    .toString().equals("SUCCESS")) {
////	                String urlCode = (String) map.get("code_url"); //微信二维码短链接
////	                // 生成微信二维码，输出到response流中
////
////	                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
////	                // 内容所使用编码
////	                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
////	                BitMatrix bitMatrix = new MultiFormatWriter().encode(urlCode, BarcodeFormat
////	                        .QR_CODE, 300, 300, hints);
////	                // 生成二维码
////	                MatrixToImageWriter.writeToFile(bitMatrix, "gif", new File("C:/downloads/二维码文件" +
////	                        ".gif"));
//	            } else {
//	            }
//	        } catch (Exception e) {
//
//	        }
//	    }
//
//	    @RequestMapping("/notifyUrl")
//	    public String notifyUrl(String unifiedorderUrl, String requestXml) {
//	        System.out.print("h");
//	        return "回调成功";
//
//	    }
//	    public static String doPost(String url, String requestXml) {
//	        CloseableHttpClient httpClient = null;
//	        CloseableHttpResponse httpResponse = null;
//	        //创建httpClient连接对象
//	        httpClient = HttpClients.createDefault();
//	        //创建post请求连接对象
//	        HttpPost httpPost = new HttpPost(url);
//	        //创建连接请求对象,并设置连接参数
//	        RequestConfig requestConfig = RequestConfig.custom()
//	                .setConnectTimeout(15000)   //连接服务区主机超时时间
//	                .setConnectionRequestTimeout(60000) //连接请求超时时间
//	                .setSocketTimeout(60000).build(); //设置读取响应数据超时时间
//	        //为httppost请求设置参数
//	        httpPost.setConfig(requestConfig);
//	        //将上传参数放到entity属性中
//	        httpPost.setEntity(new StringEntity(requestXml, "UTF-8"));
//	        //添加头信息
//	        httpPost.addHeader("Content-type", "text/xml");
//	        String result = "";
//	        try {
//	            //发送请求
//	            httpResponse = httpClient.execute(httpPost);
//	            //从相应对象中获取返回内容
//	            HttpEntity entity = (HttpEntity) httpResponse.getEntity();
//	            result = EntityUtils.toString((org.apache.http.HttpEntity) entity, "UTF-8");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return result;
//
//	    }
//
//	    /**
//	     * 获取IP地址
//	     *
//	     * @param request
//	     * @return
//	     */
//	    public static String getIpAddress(HttpServletRequest request) {
//	        String ip = request.getHeader("x-forwarded-for");
//	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	            ip = request.getHeader("Proxy-Client-IP");
//	        }
//	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	            ip = request.getHeader("WL-Proxy-Client-IP");
//	        }
//	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	            ip = request.getHeader("HTTP_CLIENT_IP");
//	        }
//	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//	        }
//	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//	            ip = request.getRemoteAddr();
//	        }
//	        return ip;
//	    }
}
