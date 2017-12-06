package com.qisen.mts.common.model.entity.sys;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Gtype extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4276640983634279343L;

	private Integer id;
	private Integer eid;
	private String no;
	private String name;
	private String depCode;
	private String station;

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
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
