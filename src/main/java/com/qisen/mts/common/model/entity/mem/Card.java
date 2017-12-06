package com.qisen.mts.common.model.entity.mem;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Card extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5975214812757074126L;
	
	private Integer id;
	private Integer eid;
	private Integer sid;
	private Integer memId;
	private Double discount;
	private Double cardFee;
	private Double sumCardFee;
	private String cardTypeNo;
	private String cardNo;
	private Integer consumeTimes;
	private Double consumeFee;
	private Double presentFee;
	private Double sumPresentFee;
	private Double initCardFee;
	private Double initPresentFee;
	private Integer crossFlag;
	private String validDate;
	private Date inputDate;
	private Integer status;
	
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

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getCardFee() {
		return cardFee;
	}

	public void setCardFee(Double cardFee) {
		this.cardFee = cardFee;
	}

	public Double getSumCardFee() {
		return sumCardFee;
	}

	public void setSumCardFee(Double sumCardFee) {
		this.sumCardFee = sumCardFee;
	}

	public String getCardTypeNo() {
		return cardTypeNo;
	}

	public void setCardTypeNo(String cardTypeNo) {
		this.cardTypeNo = cardTypeNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getConsumeTimes() {
		return consumeTimes;
	}

	public void setConsumeTimes(Integer consumeTimes) {
		this.consumeTimes = consumeTimes;
	}

	public Double getConsumeFee() {
		return consumeFee;
	}

	public void setConsumeFee(Double consumeFee) {
		this.consumeFee = consumeFee;
	}

	public Double getPresentFee() {
		return presentFee;
	}

	public void setPresentFee(Double presentFee) {
		this.presentFee = presentFee;
	}

	public Double getSumPresentFee() {
		return sumPresentFee;
	}

	public void setSumPresentFee(Double sumPresentFee) {
		this.sumPresentFee = sumPresentFee;
	}

	public Double getInitCardFee() {
		return initCardFee;
	}

	public void setInitCardFee(Double initCardFee) {
		this.initCardFee = initCardFee;
	}

	public Double getInitPresentFee() {
		return initPresentFee;
	}

	public void setInitPresentFee(Double initPresentFee) {
		this.initPresentFee = initPresentFee;
	}

	public Integer getCrossFlag() {
		return crossFlag;
	}

	public void setCrossFlag(Integer crossFlag) {
		this.crossFlag = crossFlag;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}


	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
