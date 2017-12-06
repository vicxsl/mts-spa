package com.qisen.mts.beauty.model.entity.busi;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class Pay extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971708663808694429L;

	private Long id;
	private Integer sid;
	private Long billId;
	private String depCode;
	private Double totalPay;
	private JSONObject pay;
	private String btype;
	private Date bday;
	private Integer status;
	
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

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public Double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Double totalPay) {
		this.totalPay = totalPay;
	}

	public JSONObject getPay() {
		return pay;
	}

	public void setPay(JSONObject pay) {
		this.pay = pay;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
