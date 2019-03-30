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
	private double todayPeopleBalance;//今日推荐直接收益
	private double todayPeopleMoney;//今日所有推广人员购买产生的总计收益
	private Integer todayOrderNum;//今日收益金额总计多少笔（订单数）
	private double todayNoFinishMoney;//今日收益 未完成部分
	private double todayMoney;//所有推广人员今日购买产生的总计收益
	private Integer myPeople;//我的朋友 直接推广人员
	private double myPeopleMoneyFromOne;//我的朋友 直接推广人员购买产生的收益
	private double myPeopleMoney;//我的朋友 所有推广人员购买产生的收益
	private double myBalance;//我的余额 
	private double myTotalMoney;//我的全部总收益
	private double noFinishMoney;//我的待完成收益
	private String createTime;//从哪个时间到现在 如 一天，一周，一个月
	private String timeUnit;//时间单位 1当天、2本周、3本月
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
	public double getTodayPeopleBalance() {
		return todayPeopleBalance;
	}
	public void setTodayPeopleBalance(double todayPeopleBalance) {
		this.todayPeopleBalance = todayPeopleBalance;
	}
	public double getTodayPeopleMoney() {
		return todayPeopleMoney;
	}
	public void setTodayPeopleMoney(double todayPeopleMoney) {
		this.todayPeopleMoney = todayPeopleMoney;
	}
	public Integer getTodayOrderNum() {
		return todayOrderNum;
	}
	public void setTodayOrderNum(Integer todayOrderNum) {
		this.todayOrderNum = todayOrderNum;
	}
	public double getTodayNoFinishMoney() {
		return todayNoFinishMoney;
	}
	public void setTodayNoFinishMoney(double todayNoFinishMoney) {
		this.todayNoFinishMoney = todayNoFinishMoney;
	}
	public double getTodayMoney() {
		return todayMoney;
	}
	public void setTodayMoney(double todayMoney) {
		this.todayMoney = todayMoney;
	}
	public Integer getMyPeople() {
		return myPeople;
	}
	public void setMyPeople(Integer myPeople) {
		this.myPeople = myPeople;
	}
	public double getMyPeopleMoneyFromOne() {
		return myPeopleMoneyFromOne;
	}
	public void setMyPeopleMoneyFromOne(double myPeopleMoneyFromOne) {
		this.myPeopleMoneyFromOne = myPeopleMoneyFromOne;
	}
	public double getMyPeopleMoney() {
		return myPeopleMoney;
	}
	public void setMyPeopleMoney(double myPeopleMoney) {
		this.myPeopleMoney = myPeopleMoney;
	}
	public double getMyBalance() {
		return myBalance;
	}
	public void setMyBalance(double myBalance) {
		this.myBalance = myBalance;
	}
	public double getMyTotalMoney() {
		return myTotalMoney;
	}
	public void setMyTotalMoney(double myTotalMoney) {
		this.myTotalMoney = myTotalMoney;
	}
	public double getNoFinishMoney() {
		return noFinishMoney;
	}
	public void setNoFinishMoney(double noFinishMoney) {
		this.noFinishMoney = noFinishMoney;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	
}
