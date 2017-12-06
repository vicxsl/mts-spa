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
public class StoItemListRequest extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218384839863975367L;
	private String keyword;
	private String typeNo;
	private Date validTo;
	private Integer minSto;
	private Boolean stoAlert;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public Integer getMinSto() {
		return minSto;
	}
	public void setMinSto(Integer minSto) {
		this.minSto = minSto;
	}
	public Boolean getStoAlert() {
		return stoAlert;
	}
	public void setStoAlert(Boolean stoAlert) {
		this.stoAlert = stoAlert;
	}
}
