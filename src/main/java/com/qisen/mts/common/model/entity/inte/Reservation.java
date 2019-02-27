package com.qisen.mts.common.model.entity.inte;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Reservation extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7035203632472989347L;
	private Integer id;
	private Integer eid;
	private String appid;
	private Integer memId; // 客户ID
	private String memName; // 客户姓名
	private String memMobile; 
	private Integer empId; // 手艺人ID
	private String empName; // 手艺人姓名
	private Date reservationTime;
	private Integer reservationSource;
	private Boolean occupancy;
	private Integer num;
	private Integer reservationType; 
	private Integer itemId; 
	private String itemName; 
	private Integer status;
	private String comment;
	private Date createTime;
	
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
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}
	public Integer getReservationSource() {
		return reservationSource;
	}
	public void setReservationSource(Integer reservationSource) {
		this.reservationSource = reservationSource;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 0预约，1占用
	 * @return
	 */
	public Boolean getOccupancy() {
		return occupancy;
	}
	/**
	 * 0预约，1占用
	 * @param occupancy
	 */
	public void setOccupancy(Boolean occupancy) {
		this.occupancy = occupancy;
	}
	/**
	 * 0预约 1反预约
	 * @return
	 */
	public Integer getReservationType() {
		return reservationType;
	}
	/**
	 * 0预约 1反预约
	 * @param reservationType
	 */
	public void setReservationType(Integer reservationType) {
		this.reservationType = reservationType;
	} 
}
