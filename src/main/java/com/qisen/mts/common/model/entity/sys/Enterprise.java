package com.qisen.mts.common.model.entity.sys;

import java.util.Date;

import com.qisen.mts.common.model.entity.BaseEntity;

public class Enterprise extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019401448241862956L;

	private Integer id;//企业id
	private String name;//企业名称
	private String address;//企业地址
	private String linkman;//企业联系人
	private String mobile;//联系人手机
	private String phone;//企业电话
	private String logo;//企业logo
	private Integer flag;//连锁标志
	private Date inputDate;//创建日期
	private String remark;//备注
	private String areaId;//区号
	private Integer provId;//省份id
	private Integer orgNo;//开发片区编号
	private Integer servorGno;//服务片区编号
	private Integer empId;//经手人id
	private Integer tempId;//培训人id
	private String tbuser;//数据表用户

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
			this.inputDate = inputDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

	public Integer getServorGno() {
		return servorGno;
	}

	public void setServorGno(Integer servorGno) {
		this.servorGno = servorGno;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getTempId() {
		return tempId;
	}

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}

	public String getTbuser() {
		return tbuser;
	}

	public void setTbuser(String tbuser) {
		this.tbuser = tbuser;
	}

}
