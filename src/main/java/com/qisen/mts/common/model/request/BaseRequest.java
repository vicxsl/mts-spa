package com.qisen.mts.common.model.request;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.qisen.mts.common.model.SessionAccount;
import com.qisen.mts.common.model.constant.ConfigConsts;

public class BaseRequest<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280804637908279226L;
	
	private Integer eid;
	private Integer sid;
	private String token;
	private String scope;
	private String lang;
	
	private SessionAccount sessionAccount;
	
	private T body;
	
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public SessionAccount getSessionAccount() {
		return sessionAccount;
	}

	public void setSessionAccount(SessionAccount sessionAccount) {
		this.sessionAccount = sessionAccount;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, ConfigConsts.JSON_SERIALIZER_FEATURE);
	}

}
