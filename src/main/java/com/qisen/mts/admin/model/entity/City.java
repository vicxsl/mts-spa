package com.qisen.mts.admin.model.entity;

import java.io.Serializable;

public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893656151121644946L;
	
	private Integer id;
	
	private Integer provId;//省份id
	
	private String areaId;//区号
	
	private String name;//
	
	private Integer orgNo;//片区编号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

}
