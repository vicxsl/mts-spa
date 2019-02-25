package com.qisen.mts.spa.model.entity;

import com.qisen.mts.common.model.entity.BaseEntity;

/**
 * @author chali_win_pro
 *
 */
public class SpaMember extends BaseEntity {

	/**
	 * 会员资料
	 */
	private static final long serialVersionUID = -589365615135344946L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String name;
	private String mobile;
	private double totalMoney;//总金额
	private double balance;//余额
	private Integer recommendOneId;//第一推荐人
	private Integer recommendTwoId;//第二推荐人
	private Integer recommendThreeId;//第三推荐人
	private String createDate;//加入时间
	private String unionid;//加入时间
	private String session_key;//加入时间
	private String openid;//加入时间
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Integer getRecommendOneId() {
		return recommendOneId;
	}
	public void setRecommendOneId(Integer recommendOneId) {
		this.recommendOneId = recommendOneId;
	}
	public Integer getRecommendTwoId() {
		return recommendTwoId;
	}
	public void setRecommendTwoId(Integer recommendTwoId) {
		this.recommendTwoId = recommendTwoId;
	}
	public Integer getRecommendThreeId() {
		return recommendThreeId;
	}
	public void setRecommendThreeId(Integer recommendThreeId) {
		this.recommendThreeId = recommendThreeId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
