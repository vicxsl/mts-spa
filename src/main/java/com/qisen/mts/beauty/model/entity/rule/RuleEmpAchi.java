package com.qisen.mts.beauty.model.entity.rule;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class RuleEmpAchi extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8336114453562254785L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private Integer no;
	private Integer rtype;
	private String itemNo;
	private String typeNo;
	private String gtype;
	private String gno;
	private String eno;
	private String ctype;
	private String remark;
	private Integer status;
	private JSONObject prop;

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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
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

	public String getEno() {
		return eno;
	}

	public void setEno(String eno) {
		this.eno = eno;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JSONObject getProp() {
		return prop;
	}

	public void setProp(JSONObject prop) {
		this.prop = prop;
	}

}
