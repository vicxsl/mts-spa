package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaShopPreferential extends BaseEntity {

	/**
	 * spa商户
	 */
	private static final long serialVersionUID = -589365615135087946L;

	private Integer id;
	private Integer eid;
	private String appid;//小程序id
	private String name;//门店优惠名称
	private String startTime;//接入日期
	private String endTime;//结束日期
	private double maxMoney;//满多少金额达成优惠条件
	private double preFee;//优惠金额
	private double discount;//优惠折扣
	private Integer num;//优惠达成商品数量
	private String type;//门店优惠类型
	private String describeText;//优惠描述
	private String status;//状态
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
	}
	public double getPreFee() {
		return preFee;
	}
	public void setPreFee(double preFee) {
		this.preFee = preFee;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescribeText() {
		return describeText;
	}
	public void setDescribeText(String describeText) {
		this.describeText = describeText;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
