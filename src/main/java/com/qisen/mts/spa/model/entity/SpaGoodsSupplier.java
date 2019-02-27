package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;
/**
 * 产品供货商
 * @author Administrator
 *
 */
public class SpaGoodsSupplier extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String address;

    //联系人
    private String linkman;

    private String linkmobile;

    private String otherlinkman;

    private String otherlinkmobile;

    private String status;

    private String appid;

    private Integer eid;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmobile() {
		return linkmobile;
	}

	public void setLinkmobile(String linkmobile) {
		this.linkmobile = linkmobile;
	}

	public String getOtherlinkman() {
		return otherlinkman;
	}

	public void setOtherlinkman(String otherlinkman) {
		this.otherlinkman = otherlinkman;
	}

	public String getOtherlinkmobile() {
		return otherlinkmobile;
	}

	public void setOtherlinkmobile(String otherlinkmobile) {
		this.otherlinkmobile = otherlinkmobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}


    
}
