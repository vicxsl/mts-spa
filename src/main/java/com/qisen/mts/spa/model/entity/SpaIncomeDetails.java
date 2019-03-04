package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaIncomeDetails extends BaseEntity {

	/**
	 * 获利详情实例
	 */
	private static final long serialVersionUID = -589365615135344946L;

	private Integer id;
	private Integer eid;
	private String appid;
	private String buyOpenid;//购买人openid
	private String name;//购买人名称
	private double money;//获利金额
	private String openid;//获利人openid
	private Integer orderId;//订单id
	private String level;//推荐等级
	private String createTime;//创建时间
	private String status;//状态;
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
	public String getBuyOpenid() {
		return buyOpenid;
	}
	public void setBuyOpenid(String buyOpenid) {
		this.buyOpenid = buyOpenid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	
}
