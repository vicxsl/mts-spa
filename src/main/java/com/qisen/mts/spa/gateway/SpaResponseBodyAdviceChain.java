package com.qisen.mts.spa.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.qisen.mts.common.model.response.BaseResponse;

@ControllerAdvice(basePackages = { "com.qisen.mts.spa" })
public class SpaResponseBodyAdviceChain implements ResponseBodyAdvice<BaseResponse> {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SpaResponseBodyAdviceChain.class);

	@Override
	public BaseResponse beforeBodyWrite(BaseResponse baseResp, MethodParameter parameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
		return baseResp;
	}

	@Override
	public boolean supports(MethodParameter parameter, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

}
