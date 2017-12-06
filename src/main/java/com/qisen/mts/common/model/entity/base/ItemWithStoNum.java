package com.qisen.mts.common.model.entity.base;

public class ItemWithStoNum extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -301196126161406254L;
	private Integer stoNum;
	private Double inPrice;
	private Double stoMoney;
	public Integer getStoNum() {
		return stoNum;
	}
	public void setStoNum(Integer stoNum) {
		this.stoNum = stoNum;
	}
	public Double getInPrice() {
		return inPrice;
	}
	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}
	public Double getStoMoney() {
		return stoMoney;
	}
	public void setStoMoney(Double stoMoney) {
		this.stoMoney = stoMoney;
	}
}
