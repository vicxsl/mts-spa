package com.qisen.mts.common.model.entity.sys;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class Shop extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228447468689931902L;

	private Integer id;//门店id
	private Integer eid;//企业id
	private String name;//门店名称
	private String address;//门店地址
	private String linkman;//门店联系人
	private String mobile;//门店联系人手机
	private String phone;//门店电话
	private Integer dataEid;//基础数据所在企业id
	private String logo;//门店logo
	private String version;//门店系统版本
	private Date inputDate;//门店创建时间
	private Date validDate;//门店到期日期
	private String industry;//行业标志
	private String signFlag;//门店签约标志：0未签约，1已签约
	private Integer status;//门店状态:0已删除，1正常营业，2关门歇业
	private String remark;//门店备注
	private Integer regionId;//区域id
	private JSONObject prop;//其他属性
	private String areaId;//区号
	private Integer provId;//省份id
	private Integer orgNo;//开发片区编号
	private Integer servorGno;//服务片区编号
	private Integer empId;//开发员工id
	private Integer tempId;//服务员工id

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

	public Integer getDataEid() {
		return dataEid;
	}

	public void setDataEid(Integer dataEid) {
		this.dataEid = dataEid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public JSONObject getProp() {
		return prop;
	}

	public void setProp(JSONObject prop) {
		this.prop = prop;
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

}
