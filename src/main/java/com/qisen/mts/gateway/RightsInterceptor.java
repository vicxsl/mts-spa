package com.qisen.mts.gateway;

//import java.util.HashMap;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.google.gson.Gson;


public class RightsInterceptor extends HandlerInterceptorAdapter {

	//private static Log logger = LogFactory.getLog(TokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		return true;
	}
}
