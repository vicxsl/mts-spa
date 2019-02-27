package com.qisen.mts.spa.model.entity;

import java.util.Date;
import java.util.List;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * 出入库单表
 * 
 * @author Administrator
 *
 */
public class SpaInoutDepot extends BaseEntity {

	private Integer id;

	private String no;

	private Integer inOutDepotTypeId;
	
	private String type;

	private String remark;

	private Date createDate;

	private Date auditingDate;

	private String accountId;

	private Double money;

	private String status;

	private String appid;

	private Integer eid;
	
	private List<SpaInoutDepotDetail> goodsList;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getInOutDepotTypeId() {
		return inOutDepotTypeId;
	}

	public void setInOutDepotTypeId(Integer inOutDepotTypeId) {
		this.inOutDepotTypeId = inOutDepotTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public List<SpaInoutDepotDetail> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<SpaInoutDepotDetail> goodsList) {
		this.goodsList = goodsList;
	}

}
