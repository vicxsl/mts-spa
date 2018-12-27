package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaGoodsType extends BaseEntity {

	/**
	 * spa商品资料
	 */
	private static final long serialVersionUID = -589365615135389046L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String typeName;
	private String typeNo;//商品编号
	private String status;// 状态:0正常,1停售,2删除
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
