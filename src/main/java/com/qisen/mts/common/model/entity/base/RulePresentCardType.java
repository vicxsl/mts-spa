package com.qisen.mts.common.model.entity.base;

import com.qisen.mts.common.model.entity.BaseEntity;

public class RulePresentCardType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2580129042920348133L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String cardTypeNo;
	private Double money;
	private Integer way;
	private Double present;

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

	public String getCardTypeNo() {
		return cardTypeNo;
	}

	public void setCardTypeNo(String cardTypeNo) {
		this.cardTypeNo = cardTypeNo;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}

	public Double getPresent() {
		return present;
	}

	public void setPresent(Double present) {
		this.present = present;
	}

}
