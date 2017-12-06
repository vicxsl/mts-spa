package com.qisen.mts.admin.gateway;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.SessionAdmin;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.request.BaseRequest;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@ControllerAdvice(basePackages = { "com.qisen.mts.admin" })
public class AdminRequestBodyAdviceChain implements RequestBodyAdvice {

	private static final Logger logger = LoggerFactory.getLogger(AdminRequestBodyAdviceChain.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	public boolean supports(MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		if (!parameter.getMethod().getName().equals("login")) {
			BaseRequest<?> baseReq = (BaseRequest<?>) body;
			try {
				String adminInfoJstr = memcachedClient.get(ConfigConsts.SESSION_ADMIN + baseReq.getToken());
				if (StringUtils.isNotBlank(adminInfoJstr)) {
					JSONObject sessionAdminJ = JSON.parseObject(adminInfoJstr);
					SessionAdmin sessionAdmin = sessionAdminJ.toJavaObject(SessionAdmin.class);
					memcachedClient.add(ConfigConsts.SESSION_ADMIN + baseReq.getToken(), ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionAdmin.toString());
					baseReq.setSessionAccount(sessionAdmin);
				}
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				logger.error(e.getMessage(), e);
			}
			return baseReq;
		}
		return body;
	}

}
