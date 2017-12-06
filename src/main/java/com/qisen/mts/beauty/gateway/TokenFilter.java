package com.qisen.mts.beauty.gateway;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.SessionUser;
import com.qisen.mts.beauty.service.common.BeautyService;
import com.qisen.mts.common.dao.sys.AccountDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.gateway.BodyReaderHttpServletRequestWrapper;
import com.qisen.mts.gateway.HttpHelper;
import com.qisen.mts.gateway.SpringContextUtil;

import net.rubyeye.xmemcached.MemcachedClient;

@Component("beautyTokenFilter")
public class TokenFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	
	private MultipartResolver multipartResolver;
	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private BeautyService beautyService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		ServletRequest requestWrapper = null;
		JSONObject baseReq = null;
		String token = null;
		String scope = null;
		boolean isUpload = false;
		if (request.getRequestURI().contains("/photo/upload")) {
			if (multipartResolver == null)
				multipartResolver = (MultipartResolver) SpringContextUtil.getBean("multipartResolver");
			String lang = null;
			if (multipartResolver.isMultipart(request)) {
				isUpload = true;
				// 防止流读取一次后就没有了
				requestWrapper = multipartResolver.resolveMultipart(request);
				token = requestWrapper.getParameter("token");
				scope = requestWrapper.getParameter("scope");
				lang = requestWrapper.getParameter("lang");
			} else {
				token = request.getParameter("token");
				scope = request.getParameter("scope");
				lang = request.getParameter("lang");
			}
			requestWrapper.setAttribute("lang", lang);
		} else {
			// 防止流读取一次后就没有了
			requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
			String body = HttpHelper.getBodyString(requestWrapper);
			baseReq = JSON.parseObject(body);
			token = baseReq.getString("token");
			scope = baseReq.getString("scope");
			String lang = baseReq.getString("lang");
			requestWrapper.setAttribute("lang", lang);
			logger.debug(baseReq.toJSONString());
		}
		
		if (!request.getRequestURI().contains("/beauty/login")) {
			BaseResponse resp = new BaseResponse();
			if (StringUtils.isNotBlank(token)) {
				try {
					String sessionUserKey = ConfigConsts.SESSION_USER + token;
					String userInfoJstr = memcachedClient.get(sessionUserKey);
					SessionUser sessionUser = null;
					if (StringUtils.isBlank(userInfoJstr)) {
						Account account = accountDao.findByToken(token);
						if (account != null) {
							sessionUser = beautyService.buildSessionUser(account);
							memcachedClient.add(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, sessionUser.toString());
						}
					} else {
						sessionUser = JSON.parseObject(userInfoJstr, SessionUser.class);
						memcachedClient.replace(sessionUserKey, ConfigConsts.MAX_SESSION_USER_INTERVAL, userInfoJstr);
					}
					if (sessionUser != null) {
						JSONObject authScope = sessionUser.getAuthScope();
						if (authScope == null || (authScope != null && !authScope.containsKey(scope))) // 验证scope
							resp.setResult(ResultCode.INVALID_TOKEN_SCOPE);
						else if (baseReq != null && baseReq.getIntValue("eid") != sessionUser.getEid()) // 验证是否同企业用户
							resp.setResult(ResultCode.NO_RIGHTS);
						else if (!isUpload && baseReq != null) { // 非上传不处理metadata更新逻辑
							// metadata更新逻辑
							Long lastUpdateTs4e = memcachedClient.get(ConfigConsts.METADATA_E + baseReq.getInteger("eid"));
							Long lastUpdateTs4s = memcachedClient.get(ConfigConsts.METADATA_S + baseReq.getInteger("sid"));
							if (lastUpdateTs4e != null && lastUpdateTs4e > 0)
								requestWrapper.setAttribute("lastUpdateTs4e", lastUpdateTs4e);
							if (lastUpdateTs4s != null && lastUpdateTs4s > 0)
								requestWrapper.setAttribute("lastUpdateTs4s", lastUpdateTs4s);
							chain.doFilter(requestWrapper, response);
							return;
						} else {
							chain.doFilter(requestWrapper, response);
							return;
						}
					} else
						resp.setResult(ResultCode.INVALID_TOKEN);
				} catch (Exception e) {
					logger.error("filter error:", e);
					resp.setResult(ResultCode.FAILED);
				}
			} else
				resp.setResult(ResultCode.INVALID_PARAMETERS);

			if (resp.getResult() != ResultCode.SUCCESS.getCode()) {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				// 未登录
				PrintWriter out = response.getWriter();
				out.print(resp.toString());
				out.close();
				return;
			}
		}
		chain.doFilter(requestWrapper, response);
	}

}
