package com.qisen.mts.common.model.entity.mem;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class CardRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747040749018448374L;

	private Integer id;
	private String appid;
	private Integer memId;
	private Integer memAppid;
	private Integer memCardId;
	private Double cardFee;
	private Double payCard;
	private Double presentFee;
	private Double payPresent;
	private String cardTypeNo;
	private String cardNo;
	private String cardName;
	private Long billId;
	private Integer type;
	private Date inputDate;
	private Integer stype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getMemAppid() {
		return memAppid;
	}

	public void setMemAppid(Integer memAppid) {
		this.memAppid = memAppid;
	}

	public Integer getMemCardId() {
		return memCardId;
	}

	public void setMemCardId(Integer memCardId) {
		this.memCardId = memCardId;
	}

	public Double getCardFee() {
		return cardFee;
	}

	public void setCardFee(Double cardFee) {
		this.cardFee = cardFee;
	}

	public Double getPayCard() {
		return payCard;
	}

	public void setPayCard(Double payCard) {
		this.payCard = payCard;
	}

	public Double getPayPresent() {
		return payPresent;
	}

	public void setPayPresent(Double payPresent) {
		this.payPresent = payPresent;
	}

	public Double getPresentFee() {
		return presentFee;
	}

	public void setPresentFee(Double presentFee) {
		this.presentFee = presentFee;
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

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getStype() {
		return stype;
	}

	public void setStype(Integer stype) {
		this.stype = stype;
	}

}
