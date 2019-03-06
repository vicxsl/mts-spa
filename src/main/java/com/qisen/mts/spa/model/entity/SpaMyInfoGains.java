package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro 我的页面4个数据
 *
 */
public class SpaMyInfoGains extends BaseEntity {

	/**
	 * 会员资料
	 */
	private static final long serialVersionUID = -589365615135344946L;

	private Integer eid;
	private String appid;
	private String openid;
	private Integer todayPeople;//今日推荐人数
	private Integer todayMoney;//今日收益金额
	private Integer myPeople;//我的朋友
	private Integer myMoney;//我的余额
	private String createTime;//从哪个时间到现在 如 一天，一周，一个月
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
	public Integer getTodayPeople() {
		return todayPeople;
	}
	public void setTodayPeople(Integer todayPeople) {
		this.todayPeople = todayPeople;
	}
	public Integer getTodayMoney() {
		return todayMoney;
	}
	public void setTodayMoney(Integer todayMoney) {
		this.todayMoney = todayMoney;
	}
	public Integer getMyPeople() {
		return myPeople;
	}
	public void setMyPeople(Integer myPeople) {
		this.myPeople = myPeople;
	}
	public Integer getMyMoney() {
		return myMoney;
	}
	public void setMyMoney(Integer myMoney) {
		this.myMoney = myMoney;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
