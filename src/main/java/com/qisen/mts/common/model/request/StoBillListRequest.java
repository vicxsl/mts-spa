/**
 * 
 */
package com.qisen.mts.common.model.request;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author forbr
 *
 */
public class StoBillListRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218384839863975367L;
	private Date startDate;
	private Date endDate;
	private Integer status;
	private String btype;
	private String stype;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
}
