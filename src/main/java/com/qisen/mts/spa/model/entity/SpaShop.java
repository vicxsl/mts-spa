package com.qisen.mts.spa.model.entity;

import java.util.List;

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
	private String appid;//小程序id
	private String secret;//小程序id
	private String name;//门店名称
	private String createDate;//接入日期
	private String endDate;//结束日期
	private double openMoney;//新开费用
	private double reNewMoney;//接入费用
	private String status;//状态
	private Integer addressId;//办公地址id
	private Integer depotAddressId;//仓库地址id
	private MemberAddress address;//地址
	private MemberAddress depotAddress;//仓库地址
	private Integer bonusNum;//提成层级：0、1、2、3
	private String shopImg;//门店logo
	private List<ShopBonus> shopBonusList;//新零售提成机制
	private List<SpaImg> shopImgs;//店铺轮播图
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
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
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
	public double getOpenMoney() {
		return openMoney;
	}
	public void setOpenMoney(double openMoney) {
		this.openMoney = openMoney;
	}
	public double getReNewMoney() {
		return reNewMoney;
	}
	public void setReNewMoney(double reNewMoney) {
		this.reNewMoney = reNewMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getDepotAddressId() {
		return depotAddressId;
	}
	public void setDepotAddressId(Integer depotAddressId) {
		this.depotAddressId = depotAddressId;
	}
	public MemberAddress getAddress() {
		return address;
	}
	public void setAddress(MemberAddress address) {
		this.address = address;
	}
	public MemberAddress getDepotAddress() {
		return depotAddress;
	}
	public void setDepotAddress(MemberAddress depotAddress) {
		this.depotAddress = depotAddress;
	}
	public Integer getBonusNum() {
		return bonusNum;
	}
	public void setBonusNum(Integer bonusNum) {
		this.bonusNum = bonusNum;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public List<ShopBonus> getShopBonusList() {
		return shopBonusList;
	}
	public void setShopBonusList(List<ShopBonus> shopBonusList) {
		this.shopBonusList = shopBonusList;
	}
	public List<SpaImg> getShopImgs() {
		return shopImgs;
	}
	public void setShopImgs(List<SpaImg> shopImgs) {
		this.shopImgs = shopImgs;
	}
	
}
