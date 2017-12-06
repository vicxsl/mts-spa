package com.qisen.mts.admin.model.entity;

import java.io.Serializable;

public class Province implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387302289627094765L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
