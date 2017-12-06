package com.qisen.mts.common.model.entity.base;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Type extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6470623749364559920L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String no;
	private String name;
	/**
	 * 类型:1项目,2商品 ,3会员,4日常开支
	 */
	private Integer type;
	private Integer status;

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
