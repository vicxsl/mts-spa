package com.qisen.mts.beauty.gateway;

import java.io.IOException;
import java.lang.reflect.Type;

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
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.service.common.BeautyService;
import com.qisen.mts.common.dao.sys.AccountDao;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.request.BaseRequest;

import net.rubyeye.xmemcached.MemcachedClient;

@ControllerAdvice(basePackages = { "com.qisen.mts.beauty", "com.qisen.mts.common" })
public class BeautyRequestBodyAdviceChain implements RequestBodyAdvice {

	private static final Logger logger = LoggerFactory.getLogger(BeautyRequestBodyAdviceChain.class);

	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private BeautyService beautyService;

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
				String userInfoJstr = memcachedClient.get(ConfigConsts.SESSION_USER + baseReq.getToken());
				if (StringUtils.isBlank(userInfoJstr)) {
					Account account = accountDao.findByToken(baseReq.getToken());
					if (account != null) {
						if (account.getSid() == null || !account.getSid().equals(baseReq.getSid()))
							account.setSid(baseReq.getSid());
						SessionUser sessionUser = beautyService.buildSessionUser(account);
						baseReq.setSessionAccount(sessionUser);
						memcachedClient.add(ConfigConsts.SESSION_USER + baseReq.getToken(), ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionUser.toString());
					}
				} else {
					JSONObject sessionUserJ = JSON.parseObject(userInfoJstr);
					SessionUser sessionUser = sessionUserJ.toJavaObject(SessionUser.class);
					if (sessionUser.getSid() == null || !sessionUser.getSid().equals(baseReq.getSid())) {
						sessionUser.setSid(baseReq.getSid());
						sessionUser = beautyService.buildSessionUser(sessionUser);
						memcachedClient.add(ConfigConsts.SESSION_USER + baseReq.getToken(), ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionUser.toString());
					}
					baseReq.setSessionAccount(sessionUser);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return baseReq;
		}
		return body;
	}

}
