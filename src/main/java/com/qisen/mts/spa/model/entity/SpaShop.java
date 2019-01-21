package com.qisen.mts.spa.model.entity;

import java.sql.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaShop extends BaseEntity {

	/**
	 * spa商户
	 */
	private static final long serialVersionUID = -589365615135087946L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String name;//门店名称
	private Date createDate;//接入日期
	private Date endDate;//结束日期
	private String version;//版本
	private String status;//状态
	private String address;//地址
	private String depotAddress;//仓库地址
	private String linkMan;//联系人
	private String linkMobile;//联系人电话
	private String depotPhone;//仓库电话
	private String telePhone;//门店电话
	private String shopImg;//门店logo
	private String appId;//小程序id
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepotAddress() {
		return depotAddress;
	}
	public void setDepotAddress(String depotAddress) {
		this.depotAddress = depotAddress;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkMobile() {
		return linkMobile;
	}
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	public String getDepotPhone() {
		return depotPhone;
	}
	public void setDepotPhone(String depotPhone) {
		this.depotPhone = depotPhone;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
