package com.qisen.mts.spa.model.entity;

import java.util.List;

import com.qisen.mts.common.model.entity.BaseEntity;

public class SpaMallOrder extends BaseEntity {
	
	/**
	 * 订单
	 */

	private static final long serialVersionUID = -589365615135344946L;

	private Integer id;
	private Integer eid;
	private String appid;
	private String openid;
	private double totalMoney;//订单金额
	private double preFee;//优惠金额
	private double expressCost;//运输费用
	private double realFee;//结算金额
	private double goodsCost;//商品总成本
	private double bonusCost;//新零售成本
	private double orderProfit;//订单实际利润
	private String inOutDepotId;//出库id
	private String flowNo;//物流单号
	private String createTime;
	private String status;
	private List<SpaInoutDepotDetail> goodsList;
	private MemberAddress memberAddress;
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getPreFee() {
		return preFee;
	}
	public void setPreFee(double preFee) {
		this.preFee = preFee;
	}
	public double getExpressCost() {
		return expressCost;
	}
	public void setExpressCost(double expressCost) {
		this.expressCost = expressCost;
	}
	public double getRealFee() {
		return realFee;
	}
	public void setRealFee(double realFee) {
		this.realFee = realFee;
	}
	public double getGoodsCost() {
		return goodsCost;
	}
	public void setGoodsCost(double goodsCost) {
		this.goodsCost = goodsCost;
	}
	public double getBonusCost() {
		return bonusCost;
	}
	public void setBonusCost(double bonusCost) {
		this.bonusCost = bonusCost;
	}
	public double getOrderProfit() {
		return orderProfit;
	}
	public void setOrderProfit(double orderProfit) {
		this.orderProfit = orderProfit;
	}
	public String getInOutDepotId() {
		return inOutDepotId;
	}
	public void setInOutDepotId(String inOutDepotId) {
		this.inOutDepotId = inOutDepotId;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SpaInoutDepotDetail> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<SpaInoutDepotDetail> goodsList) {
		this.goodsList = goodsList;
	}
	public MemberAddress getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(MemberAddress memberAddress) {
		this.memberAddress = memberAddress;
	}
	
}
