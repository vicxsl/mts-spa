package com.qisen.mts.beauty.model.entity.busi;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class Bill extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5222183724988181418L;

	private Long id;
	private Integer sid;
	private Integer memId;
	private Integer memSid;
	private Integer memCardId;
	private String memName;
	private String memMobile;
	private String sex;
	private String cardNo;
	private String cardTypeNo;
	private String cardName;
	private JSONObject balanceProp;
	private Integer btype;
	private String no;
	private Double fee;
	private Date bday;
	private String remark;
	private Integer status;
	private Integer optId;
	private String optName;
	private String inputDate;
	private String cancelDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getMemSid() {
		return memSid;
	}

	public void setMemSid(Integer memSid) {
		this.memSid = memSid;
	}

	public Integer getMemCardId() {
		return memCardId;
	}

	public void setMemCardId(Integer memCardId) {
		this.memCardId = memCardId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemMobile() {
		return memMobile;
	}

	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardTypeNo() {
		return cardTypeNo;
	}

	public void setCardTypeNo(String cardTypeNo) {
		this.cardTypeNo = cardTypeNo;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public JSONObject getBalanceProp() {
		return balanceProp;
	}

	public void setBalanceProp(JSONObject balanceProp) {
		this.balanceProp = balanceProp;
	}

	public Integer getBtype() {
		return btype;
	}

	public void setBtype(Integer btype) {
		this.btype = btype;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

}
