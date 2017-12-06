package com.qisen.mts.myk.gateway;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.response.BaseResponse;
import com.qisen.mts.common.util.JSONUtil;

@ControllerAdvice(basePackages = { "com.qisen.mts.myk" })
public class MykResponseBodyAdviceChain implements ResponseBodyAdvice<BaseResponse> {

	private static final Logger logger = LoggerFactory.getLogger(MykResponseBodyAdviceChain.class);

	@Autowired
	private JSONUtil jsonUtil;

	@Override
	public BaseResponse beforeBodyWrite(BaseResponse baseResp, MethodParameter parameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		if (baseResp != null) {
			try {
				String lang = servletRequest.getAttribute("lang").toString();
				JSONObject msgCode = jsonUtil.readJSON("msgCode.json", lang);
				if (StringUtils.isNotBlank(baseResp.getCode())) {
					if (msgCode.containsKey(baseResp.getCode())) {
						baseResp.setMsg(msgCode.getString(baseResp.getCode()));
					}
				} else {
					if (msgCode.containsKey(baseResp.getResult().toString())) {
						baseResp.setMsg(msgCode.getString(baseResp.getResult().toString()));
					}
				}
			} catch (Exception e) {
				logger.error("read msg code error, code: " + baseResp.getCode(), e);
			}
			
			Long lastUpdateTs4e = (Long) servletRequest.getAttribute("lastUpdateTs4e");
			Long lastUpdateTs4s = (Long) servletRequest.getAttribute("lastUpdateTs4s");
			logger.debug("lastUpdateTs4e:" + lastUpdateTs4e + ";lastUpdateTs4s:" + lastUpdateTs4s);
			if (lastUpdateTs4e != null && lastUpdateTs4e > 0)
				baseResp.setLastUpdateTs4e(lastUpdateTs4e);
			if (lastUpdateTs4s != null && lastUpdateTs4s > 0)
				baseResp.setLastUpdateTs4s(lastUpdateTs4s);
		}
		return baseResp;
	}

	@Override
	public boolean supports(MethodParameter parameter, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

}
