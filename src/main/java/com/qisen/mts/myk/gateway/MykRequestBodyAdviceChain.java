package com.qisen.mts.myk.gateway;

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
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.myk.model.SessionMember;
import com.qisen.mts.myk.service.MykService;

import net.rubyeye.xmemcached.MemcachedClient;

@ControllerAdvice(basePackages = { "com.qisen.mts.myk" })
public class MykRequestBodyAdviceChain implements RequestBodyAdvice {

	private static final Logger logger = LoggerFactory.getLogger(MykRequestBodyAdviceChain.class);

	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MykService mykService;
	
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
				String userInfoJstr = memcachedClient.get(ConfigConsts.SESSION_MEMBER + baseReq.getToken());
				if (StringUtils.isBlank(userInfoJstr)) {
					Member member = memberDao.findByToken(baseReq.getToken());
					if (member != null) {
						if (member.getSid() == null || !member.getSid().equals(baseReq.getSid()))
							member.setSid(baseReq.getSid());
						SessionMember sessionUser = mykService.buildSessionMember(member);
						baseReq.setSessionAccount(sessionUser);
						memcachedClient.add(ConfigConsts.SESSION_MEMBER + baseReq.getToken(), ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionUser.toString());
					}
				} else {
					JSONObject sessionMemberJ = JSON.parseObject(userInfoJstr);
					SessionMember sessionMember = sessionMemberJ.toJavaObject(SessionMember.class);
					if (sessionMember.getSid() == null || !sessionMember.getSid().equals(baseReq.getSid())) {
						sessionMember.setSid(baseReq.getSid());
						sessionMember = mykService.buildSessionMember(sessionMember);
						memcachedClient.add(ConfigConsts.SESSION_MEMBER + baseReq.getToken(), ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionMember.toString());
					}
					baseReq.setSessionAccount(sessionMember);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return baseReq;
		}
		return body;
	}

}
