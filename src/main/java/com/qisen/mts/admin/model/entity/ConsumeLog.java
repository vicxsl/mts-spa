package com.qisen.mts.admin.model.entity;

import java.io.Serializable;
import java.util.Date;

public class ConsumeLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4944114897974561865L;
	private Integer id;
	private Integer eid;
	private Integer sid;
	private String eName;// 企业名称
	private String sName;// 商户名称
	private Integer classNo;// 产品类型编号
	private Integer productNo;// 产品编号
	private Double consumeFee;// 消费金额
	private Integer num;// 数量
	private Double price;// 产品价格
	private Double cost;// 产品成本
	private Double profit;// 产品利润
	private Date consumeTime;// 消费时间
	private Integer orgNo;// 片区编号
	private String admin;// 操作人
	private String payFlag;// 是否付款
	private Date payTime;// 付款时间
	private String clientType;// 0--新开；1--续费
	// private Integer chargeLogId;
	private Integer byEmpId;// 经手人
	private Integer soEmpId;// 收款人
	private String auditFlag;// 0--未审核；1--已审核
	private Integer cashNo;// 收款方式
	private String payCloseFlag;// 提成结算标志：0--未结算；1--已结算
	private String closeFlag;// 结算标志：0--未结算；1--已结算
	private Date closeDate;// 结算日期
	private String remark;// 备注

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

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Integer getClassNo() {
		return classNo;
	}

	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
	}

	public Integer getProductNo() {
		return productNo;
	}

	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}

	public Double getConsumeFee() {
		return consumeFee;
	}

	public void setConsumeFee(Double consumeFee) {
		this.consumeFee = consumeFee;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public Integer getByEmpId() {
		return byEmpId;
	}

	public void setByEmpId(Integer byEmpId) {
		this.byEmpId = byEmpId;
	}

	public Integer getSoEmpId() {
		return soEmpId;
	}

	public void setSoEmpId(Integer soEmpId) {
		this.soEmpId = soEmpId;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public Integer getCashNo() {
		return cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public String getPayCloseFlag() {
		return payCloseFlag;
	}

	public void setPayCloseFlag(String payCloseFlag) {
		this.payCloseFlag = payCloseFlag;
	}

	public String getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(String closeFlag) {
		this.closeFlag = closeFlag;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

}
