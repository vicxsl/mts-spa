package com.qisen.mts.myk.gateway;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;

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
import com.qisen.mts.common.dao.mem.MemberDao;
import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.gateway.BodyReaderHttpServletRequestWrapper;
import com.qisen.mts.gateway.HttpHelper;
import com.qisen.mts.gateway.SpringContextUtil;
import com.qisen.mts.myk.model.SessionMember;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Component("mykTokenFilter")
public class TokenFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	private MultipartResolver multipartResolver;
	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private MemberDao memberDao;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ServletRequest requestWrapper = null;
		JSONObject baseReq = null;
		String token = null;
		if (request.getRequestURI().contains("/photo/upload")) {
			if (multipartResolver == null)
				multipartResolver = (MultipartResolver) SpringContextUtil.getBean("multipartResolver");
			String lang = null;
			if (multipartResolver.isMultipart(request)) {
				// 防止流读取一次后就没有了
				requestWrapper = multipartResolver.resolveMultipart(request);
				token = requestWrapper.getParameter("token");
				lang = requestWrapper.getParameter("lang");
			} else {
				token = request.getParameter("token");
				lang = request.getParameter("lang");
			}
			requestWrapper.setAttribute("lang", lang);
		} else {
			// 防止流读取一次后就没有了
			requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
			String body = HttpHelper.getBodyString(requestWrapper);
			baseReq = JSON.parseObject(body);
			token = baseReq.getString("token");
			String lang = baseReq.getString("lang");
			requestWrapper.setAttribute("lang", lang);
			logger.debug(baseReq.toJSONString());
		}

		if (!request.getRequestURI().contains("/myk/login")) {
			BaseResponse resp = new BaseResponse();
			if (StringUtils.isNotBlank(token)) {
				try {
					String sessionMemberKey = ConfigConsts.SESSION_MEMBER + token;
					String memberInfoJstr = memcachedClient.get(sessionMemberKey);
					SessionMember sessionMember = null;
					if (StringUtils.isBlank(memberInfoJstr)) {
						Member member = memberDao.findByToken(token);
						if (member != null) {
							sessionMember = member.toJSON().toJavaObject(SessionMember.class);
							memcachedClient.add(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL,sessionMember.toString());
						}
					} else {
						sessionMember = JSON.parseObject(memberInfoJstr, SessionMember.class);
						memcachedClient.replace(sessionMemberKey, ConfigConsts.MAX_SESSION_USER_INTERVAL,memberInfoJstr);
					}
					if (sessionMember != null) {
						// metadata更新逻辑
						Long lastUpdateTs4e = memcachedClient.get(ConfigConsts.METADATA_E + baseReq.getInteger("eid"));
						Long lastUpdateTs4s = memcachedClient.get(ConfigConsts.METADATA_S + baseReq.getInteger("sid"));
						if (lastUpdateTs4e != null && lastUpdateTs4e > 0)
							requestWrapper.setAttribute("lastUpdateTs4e", lastUpdateTs4e);
						if (lastUpdateTs4s != null && lastUpdateTs4s > 0)
							requestWrapper.setAttribute("lastUpdateTs4s", lastUpdateTs4s);
						chain.doFilter(requestWrapper, response);
						return;
					} else
						resp.setResult(ResultCode.INVALID_TOKEN);
				} catch (TimeoutException | InterruptedException | MemcachedException e) {
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
