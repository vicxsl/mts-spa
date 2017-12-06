package com.qisen.mts.common.model.entity.sys;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7852838160996369904L;

	private Integer id;
	private Integer eid;
	private String depCode;
	private String name;

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

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
