package com.qisen.mts.common.model;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class SessionAccount extends BaseEntity {

	/**
	 * 公共的账号
	 * 带有token
	 */
	private static final long serialVersionUID = 4403566503925103117L;

	protected String authToken;
	protected JSONObject meta = new JSONObject();
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public JSONObject getMeta() {
		return meta;
	}
	
	public String getMetaString(String key) {
		return meta.getString(key);
	}
	
	public JSONObject getMetaJSONObject(String key) {
		return meta.getJSONObject(key);
	}
	
	public JSONArray getMetaJSONArray(String key) {
		return meta.getJSONArray(key);
	}
	
	public <T> T getMetaJavaObject(String key, Class<T> clazz) {
		JSONObject objJ = meta.getJSONObject(key);
		if (objJ != null)
			return objJ.toJavaObject(clazz);
		return null;
	}
	
	public <T> List<T> getMetaJavaList(String key, Class<T> clazz) {
		JSONArray objJ = meta.getJSONArray(key);
		if (objJ != null)
			return objJ.toJavaList(clazz);
		return null;
	}
	
	public void setMeta(String key, Object value) {
		this.meta.put(key, value);
	}

}
