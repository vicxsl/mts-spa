package com.qisen.mts.common.model.entity.sys;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Log extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4714120073167109710L;

	private Integer id;
	private Integer eid;
	private String appid;
	private String type;
	private String remark;
	private Integer optId;
	private String optName;
	private Date inputDate;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

}
