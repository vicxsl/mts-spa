package com.qisen.mts.common.model.entity.sys;

import com.qisen.mts.common.model.entity.BaseEntity;

public class ShopSetting extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917584876915607501L;
	
	private Integer id;
	private Integer eid;
	private String appid;
	private Integer mid;
	private String key;
	private String value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
