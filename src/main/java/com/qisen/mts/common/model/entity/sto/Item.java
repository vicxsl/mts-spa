package com.qisen.mts.common.model.entity.sto;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Item extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7578009394509332826L;
	
	private Integer id;
	private Integer eid;
	private String appid;
	private Integer itemId;
	private Integer initNum;
	private Integer inNum;
	private Integer outNum;
	private Double inMoney;
	private Double outMoney;
	private Integer stoNum;
	private Double stoMoney;
	private Integer salesNum;
	private Double salesMoney;
	private Integer useNum;
	private Double useMoney;
	private Integer selfBuyNum;
	private Double selfBuyMoney;
	private Date validTo;
	private Double inPrice;
	private Double cost;
	private Integer version;
	private Date lastInStoDate;

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getInitNum() {
		return initNum;
	}

	public void setInitNum(Integer initNum) {
		this.initNum = initNum;
	}

	public Integer getInNum() {
		return inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public Integer getOutNum() {
		return outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public Double getInMoney() {
		return inMoney;
	}

	public void setInMoney(Double inMoney) {
		this.inMoney = inMoney;
	}

	public Double getOutMoney() {
		return outMoney;
	}

	public void setOutMoney(Double outMoney) {
		this.outMoney = outMoney;
	}

	public Integer getStoNum() {
		return stoNum;
	}

	public void setStoNum(Integer stoNum) {
		this.stoNum = stoNum;
	}

	public Double getStoMoney() {
		return stoMoney;
	}

	public void setStoMoney(Double stoMoney) {
		this.stoMoney = stoMoney;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Double salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Double getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(Double useMoney) {
		this.useMoney = useMoney;
	}

	public Integer getSelfBuyNum() {
		return selfBuyNum;
	}

	public void setSelfBuyNum(Integer selfBuyNum) {
		this.selfBuyNum = selfBuyNum;
	}

	public Double getSelfBuyMoney() {
		return selfBuyMoney;
	}

	public void setSelfBuyMoney(Double selfBuyMoney) {
		this.selfBuyMoney = selfBuyMoney;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getLastInStoDate() {
		return lastInStoDate;
	}

	public void setLastInStoDate(Date lastInStoDate) {
		this.lastInStoDate = lastInStoDate;
	}

}
