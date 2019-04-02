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
	private String appid;
	private String name;
	private String avatarUrl;//会员头像
	private String mobile;
	private double totalMoney;//总金额
	private double balance;//余额
	private String recommendOneId;//第一推荐人
	private String recommendTwoId;//第二推荐人
	private String recommendThreeId;//第三推荐人
	private String createTime;//加入时间
	private String unionid;//开发者平台id
	private String js_code;//临时会话码
	private String session_key;//登录后的会话码
	private String openid;//会员微信小程序openid
	private MemberAddress memberAddress;//默认地址
	private String status;// 状态:0正常,1停用,-1删除
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
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getRecommendOneId() {
		return recommendOneId;
	}
	public void setRecommendOneId(String recommendOneId) {
		this.recommendOneId = recommendOneId;
	}
	public String getRecommendTwoId() {
		return recommendTwoId;
	}
	public void setRecommendTwoId(String recommendTwoId) {
		this.recommendTwoId = recommendTwoId;
	}
	public String getRecommendThreeId() {
		return recommendThreeId;
	}
	public void setRecommendThreeId(String recommendThreeId) {
		this.recommendThreeId = recommendThreeId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getJs_code() {
		return js_code;
	}
	public void setJs_code(String js_code) {
		this.js_code = js_code;
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
	public MemberAddress getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(MemberAddress memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
