package com.qisen.mts.common.model.entity.sys;

import java.io.Serializable;

public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer eid;
	private String name;
	private Integer parentid;
	
	public Region(){
		
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", eid=" + eid + ", name=" + name
				+ ", parentid=" + parentid + "]";
	}
}
