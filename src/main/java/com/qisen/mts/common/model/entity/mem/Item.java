package com.qisen.mts.common.model.entity.mem;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Item extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3856178029763848137L;

	private Integer id;
	private Integer eid;
	private String appid;
	private Integer memId;
	private Integer memCardId;
	private String itemNo;
	private Integer sumTimes;
	private Integer leaveTimes;
	private Double sumMoney;
	private Double onceMoney;
	private Double leaveMoney;
	private Date validDate;
	private Date inputDate;
	private Long billId;
	
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

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getMemCardId() {
		return memCardId;
	}

	public void setMemCardId(Integer memCardId) {
		this.memCardId = memCardId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getSumTimes() {
		return sumTimes;
	}

	public void setSumTimes(Integer sumTimes) {
		this.sumTimes = sumTimes;
	}

	public Integer getLeaveTimes() {
		return leaveTimes;
	}

	public void setLeaveTimes(Integer leaveTimes) {
		this.leaveTimes = leaveTimes;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Double getOnceMoney() {
		return onceMoney;
	}

	public void setOnceMoney(Double onceMoney) {
		this.onceMoney = onceMoney;
	}

	public Double getLeaveMoney() {
		return leaveMoney;
	}

	public void setLeaveMoney(Double leaveMoney) {
		this.leaveMoney = leaveMoney;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

}
