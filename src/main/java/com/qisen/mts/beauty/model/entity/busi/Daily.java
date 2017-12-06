package com.qisen.mts.beauty.model.entity.busi;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Daily extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -511607294443244299L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private Date day;
	private String type;// 鏀舵敮绫诲瀷锛�0鏀寔锛�1鏀跺叆
	private String typeNo;// 鍒嗙被缂栧彿
	private Integer way;// 鏀寔閫斿緞
	private Double money;// 閲戦
	private Double balance;//浣欓
	private String remark;// 澶囨敞
	private Integer empId;// 鎻愭垚鍛樺伐
	private Integer optId;// 鎿嶄綔浜篿d
	private String optName;// 鎿嶄綔浜哄鍚�
	private Integer status;// 鐘舵�侊細1姝ｅ父锛�0鎾ら攢
	private Date firstDay;// 鐘舵�侊細1姝ｅ父锛�0鎾ら攢
	private Date lastDay;// 鐘舵�侊細1姝ｅ父锛�0鎾ら攢
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
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public Integer getWay() {
		return way;
	}
	public void setWay(Integer way) {
		this.way = way;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}
	public Date getLastDay() {
		return lastDay;
	}
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}
	
}
