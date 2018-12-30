package com.qisen.mts.spa.model.entity;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * 出入库单表
 * 
 * @author Administrator
 *
 */
public class SpaInoutDepot extends BaseEntity {

	private Integer id;

	private String inoutdepottypename;

	private String no;

	private String inoutdepottypeno;

	private String inoutdepottype;

	private String remark;

	private Date createdate;

	private Date auditingdate;

	private String accountname;

	private String accountid;

	private Double money;

	private String status;

	private Integer sid;

	private Integer eid;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInoutdepottypename() {
		return inoutdepottypename;
	}

	public void setInoutdepottypename(String inoutdepottypename) {
		this.inoutdepottypename = inoutdepottypename;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getInoutdepottypeno() {
		return inoutdepottypeno;
	}

	public void setInoutdepottypeno(String inoutdepottypeno) {
		this.inoutdepottypeno = inoutdepottypeno;
	}

	public String getInoutdepottype() {
		return inoutdepottype;
	}

	public void setInoutdepottype(String inoutdepottype) {
		this.inoutdepottype = inoutdepottype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getAuditingdate() {
		return auditingdate;
	}

	public void setAuditingdate(Date auditingdate) {
		this.auditingdate = auditingdate;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
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

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

}
