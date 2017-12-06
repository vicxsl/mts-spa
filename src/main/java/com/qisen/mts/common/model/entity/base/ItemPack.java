package com.qisen.mts.common.model.entity.base;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class ItemPack extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8787397374250646301L;

	private Integer id;
	private Integer eid;
	private String no;
	private String name;
	private Integer validMode;
	private String validTo;
	private Integer status;
	private String shopBlock;
	private String depAuth;
	private Date inputDate;
	

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
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

	public Integer getValidMode() {
		return validMode;
	}

	public void setValidMode(Integer validMode) {
		this.validMode = validMode;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getShopBlock() {
		return shopBlock;
	}

	public void setShopBlock(String shopBlock) {
		this.shopBlock = shopBlock;
	}

	public String getDepAuth() {
		return depAuth;
	}

	public void setDepAuth(String depAuth) {
		this.depAuth = depAuth;
	}

}
