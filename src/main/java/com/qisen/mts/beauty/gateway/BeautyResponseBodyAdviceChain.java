package com.qisen.mts.beauty.gateway;

import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

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

import com.qisen.mts.common.model.ResultCode;
import com.qisen.mts.common.model.constant.ConfigConsts;
import com.qisen.mts.common.model.response.BaseResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@ControllerAdvice(basePackages = { "com.qisen.mts.beauty" })
public class BeautyResponseBodyAdviceChain implements ResponseBodyAdvice<BaseResponse> {

	private static final Logger logger = LoggerFactory.getLogger(BeautyResponseBodyAdviceChain.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	public BaseResponse beforeBodyWrite(BaseResponse baseResp, MethodParameter parameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		if (baseResp != null) {
			if (baseResp.getResult() == ResultCode.UPDATE_METADATA.getCode()) {
				try {
					if (baseResp.getLastUpdateTs4s() != null && baseResp.getLastUpdateTs4s() > 0) {
						Integer sid = baseResp.getLastUpdateTs4s().intValue();
						baseResp.setLastUpdateTs4s(System.currentTimeMillis());
						servletRequest.setAttribute("lastUpdateTs4s", baseResp.getLastUpdateTs4s());
						if (!memcachedClient.add(ConfigConsts.METADATA_S + sid, ConfigConsts.MAX_META_DATA_INTERVAL, baseResp.getLastUpdateTs4s()))
							memcachedClient.replace(ConfigConsts.METADATA_S + sid, ConfigConsts.MAX_META_DATA_INTERVAL, baseResp.getLastUpdateTs4s());
					} else if (baseResp.getLastUpdateTs4e() != null && baseResp.getLastUpdateTs4e() > 0) {
						Integer eid = baseResp.getLastUpdateTs4e().intValue();
						baseResp.setLastUpdateTs4e(System.currentTimeMillis());
						servletRequest.setAttribute("lastUpdateTs4e", baseResp.getLastUpdateTs4e());
						if (!memcachedClient.add(ConfigConsts.METADATA_E + eid, ConfigConsts.MAX_META_DATA_INTERVAL, baseResp.getLastUpdateTs4e()))
							memcachedClient.replace(ConfigConsts.METADATA_E + eid, ConfigConsts.MAX_META_DATA_INTERVAL, baseResp.getLastUpdateTs4e());
					}
					baseResp.setResult(ResultCode.SUCCESS);
				} catch (TimeoutException | InterruptedException | MemcachedException e) {
					logger.error("update memcached metadata ts error", e);
				}
			}
		}
		Long lastUpdateTs4e = (Long) servletRequest.getAttribute("lastUpdateTs4e");
		Long lastUpdateTs4s = (Long) servletRequest.getAttribute("lastUpdateTs4s");
		logger.debug("lastUpdateTs4e:" + lastUpdateTs4e + ";lastUpdateTs4s:" + lastUpdateTs4s);
		if (lastUpdateTs4e != null && lastUpdateTs4e > 0)
			baseResp.setLastUpdateTs4e(lastUpdateTs4e);
		if (lastUpdateTs4s != null && lastUpdateTs4s > 0)
			baseResp.setLastUpdateTs4s(lastUpdateTs4s);
		return baseResp;
	}

	@Override
	public boolean supports(MethodParameter parameter, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

}
