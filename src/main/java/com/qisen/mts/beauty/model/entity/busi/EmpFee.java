package com.qisen.mts.beauty.model.entity.busi;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class EmpFee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 385893198928222111L;

	private Long id;
	private Long billId;
	private Long detailId;
	private Integer sid;
	private Integer empId;
	private Double totalFee;
	private JSONObject fee;
	private Double totalGain;
	private JSONObject gain;
	private Integer pointType;
	private Integer gainType;
	private Integer ctype;
	private Integer itemType;
	private Integer station;
	private String gtype;
	private String gno;
	private Date bday;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public JSONObject getFee() {
		return fee;
	}

	public void setFee(JSONObject fee) {
		this.fee = fee;
	}

	public Double getTotalGain() {
		return totalGain;
	}

	public void setTotalGain(Double totalGain) {
		this.totalGain = totalGain;
	}

	public JSONObject getGain() {
		return gain;
	}

	public void setGain(JSONObject gain) {
		this.gain = gain;
	}

	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

	public Integer getGainType() {
		return gainType;
	}

	public void setGainType(Integer gainType) {
		this.gainType = gainType;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public String getGno() {
		return gno;
	}

	public void setGno(String gno) {
		this.gno = gno;
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
