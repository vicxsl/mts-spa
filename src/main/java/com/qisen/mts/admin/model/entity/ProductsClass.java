package com.qisen.mts.admin.model.entity;

import java.io.Serializable;

public class ProductsClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5181651588800128226L;
	
	private Integer id;
	private Integer no;
	private String  name;
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
	
}
