package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;
/**
 * 产品子类型
 * @author Administrator
 *
 */
public class SpaGoodsSubType extends BaseEntity {

	private Integer id;

	private String subtypename;

	private String subtypeno;

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

	public String getSubtypename() {
		return subtypename;
	}

	public void setSubtypename(String subtypename) {
		this.subtypename = subtypename;
	}

	public String getSubtypeno() {
		return subtypeno;
	}

	public void setSubtypeno(String subtypeno) {
		this.subtypeno = subtypeno;
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
