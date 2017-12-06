package com.qisen.mts.admin.model.entity;

import java.io.Serializable;

public class OutlayClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7303437013823458922L;
	private Integer id;
	private Integer no;
	private String name;
	private Integer type;// 收支类型：0支出，1收入
	private Integer orgFlag;// 0--非片区支出类别；1--片区支出类别
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrgFlag() {
		return orgFlag;
	}
	public void setOrgFlag(Integer orgFlag) {
		this.orgFlag = orgFlag;
	}

	
}
