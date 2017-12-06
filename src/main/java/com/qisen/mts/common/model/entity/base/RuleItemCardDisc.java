package com.qisen.mts.common.model.entity.base;

import com.qisen.mts.common.model.entity.BaseEntity;

public class RuleItemCardDisc extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5983762784669135591L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String itemNo;
	private String cardTypeNo;
	private Integer way;
	private Double disc;

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

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getCardTypeNo() {
		return cardTypeNo;
	}

	public void setCardTypeNo(String cardTypeNo) {
		this.cardTypeNo = cardTypeNo;
	}

	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}

	public Double getDisc() {
		return disc;
	}

	public void setDisc(Double disc) {
		this.disc = disc;
	}

}
