package com.qisen.mts.common.model.entity.base;

import com.qisen.mts.common.model.entity.BaseEntity;

public class ItemPackDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2519037798993105839L;
	
	private Integer id;
	private Integer eid;
	private Integer packId;
	private String itemNo;
	private Integer times;
	private Double money;

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

	public Integer getPackId() {
		return packId;
	}

	public void setPackId(Integer packId) {
		this.packId = packId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
