package com.qisen.mts.admin.model;

import java.util.Date;

import com.qisen.mts.common.model.SessionAccount;

public class SessionAdmin extends SessionAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3920174407288686753L;

	private Integer id;
	private String name;
	private String mobile;// 手机号码
	private Integer orgNo;// 片区编号
	private Date createDate;// 创建日期
	private String menuStr;// 菜单权限
	private String optStr;// 操作权限
	private String allowFlag;// 是否允许登录:0不允许,1允许

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public String getOptStr() {
		return optStr;
	}

	public void setOptStr(String optStr) {
		this.optStr = optStr;
	}

	public String getAllowFlag() {
		return allowFlag;
	}

	public void setAllowFlag(String allowFlag) {
		this.allowFlag = allowFlag;
	}

}
