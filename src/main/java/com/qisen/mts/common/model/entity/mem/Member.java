package com.qisen.mts.common.model.entity.mem;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Member extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924961017295162895L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String name;
	private String mobile;
	private String sex;
	private String birthDay;
	private String img;
	private Integer consumeTimes;
	private Double consumeFee;
	private String remark;
	private String typeNo;
	private String cashPwd;
	private String loginPwd;
	private String authToken;
	private Date lastLogin;
	private Date firstConsumeTime;
	private Date lastConsumeTime;
	private Double cycle;
	private Integer status;
	private Date inputDate;
	private Double unitPrice;
	private Double sumConsumeFee;
	private String lastEmp;


	public String getLastEmp() {
		return lastEmp;
	}

	public void setLastEmp(String lastEmp) {
		this.lastEmp = lastEmp;
	}

	public Double getCycle() {
		return cycle;
	}

	public void setCycle(Double cycle) {
		this.cycle = cycle;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getSumConsumeFee() {
		return sumConsumeFee;
	}

	public void setSumConsumeFee(Double sumConsumeFee) {
		this.sumConsumeFee = sumConsumeFee;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getCashPwd() {
		return cashPwd;
	}

	public void setCashPwd(String cashPwd) {
		this.cashPwd = cashPwd;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getFirstConsumeTime() {
		return firstConsumeTime;
	}

	public void setFirstConsumeTime(Date firstConsumeTime) {
		this.firstConsumeTime = firstConsumeTime;
	}

	public Date getLastConsumeTime() {
		return lastConsumeTime;
	}

	public void setLastConsumeTime(Date lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}


}
