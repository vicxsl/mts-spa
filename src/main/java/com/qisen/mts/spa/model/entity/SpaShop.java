package com.qisen.mts.spa.model.entity;

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
	private String no;//门店编号
	private String name;//门店名称
	private String createDate;//接入日期
	private String endDate;//结束日期
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
	private String secret;//小程序id
	private String shopBonus;//新零售提成机制
	public String getShopBonus() {
		return shopBonus;
	}
	public void setShopBonus(String shopBonus) {
		this.shopBonus = shopBonus;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
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
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
