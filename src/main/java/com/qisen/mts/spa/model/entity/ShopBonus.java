package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;


public class ShopBonus extends BaseEntity{
   
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer eid;
	private String appId;
	private Integer bonusLevel;//提成层级
	private String bonusType;//提成类型
	private double bonusValue;//提成数字
	private String status;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Integer getBonusLevel() {
		return bonusLevel;
	}
	public void setBonusLevel(Integer bonusLevel) {
		this.bonusLevel = bonusLevel;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}
	public double getBonusValue() {
		return bonusValue;
	}
	public void setBonusValue(double bonusValue) {
		this.bonusValue = bonusValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
