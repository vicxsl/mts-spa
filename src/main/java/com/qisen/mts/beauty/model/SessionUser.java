package com.qisen.mts.beauty.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.beauty.model.entity.rule.RuleEmpAchi;
import com.qisen.mts.common.model.SessionAccount;
import com.qisen.mts.common.model.entity.base.EmpGrade;
import com.qisen.mts.common.model.entity.sys.ShopSetting;

public class SessionUser extends SessionAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8669339848966740889L;

	private Integer id;
	private Integer eid;
	private Integer sid;
	private String name;
	private String mobile;
	private String account;
	private String role;
	private JSONObject rights;
	private Date lastLogin;
	private Date inputDate;
	private Integer status;
	private JSONObject authScope;
	private Integer regionId;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public JSONObject getRights() {
		return rights;
	}

	public void setRights(JSONObject rights) {
		this.rights = rights;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JSONObject getAuthScope() {
		return authScope;
	}

	public void setAuthScope(JSONObject authScope) {
		this.authScope = authScope;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * tbuser
	 * 
	 * @return
	 */
	public String getTbuser() {
		return this.getMetaString("tbuser");
	}

	/**
	 * 获取业绩/提成规则
	 * 
	 * @return
	 */
	public List<RuleEmpAchi> getEmpAchiRules() {
		return this.getMetaJavaList("empAchiRules", RuleEmpAchi.class);
	}

	/**
	 * 获取员工级别
	 * 
	 * @return
	 */
	public List<EmpGrade> getEmpGrades() {
		return this.getMetaJavaList("empGrades", EmpGrade.class);
	}
	
	/**
	 * 获取基础分类
	 * 
	 * @param key serviceTypes/productTypes/memberTypes/dailyTypes/payTypes
	 * @return
	 */
	public JSONArray getBasicType(String key) {
		JSONObject resultJ = getBasicTypes();
		if (resultJ != null && !resultJ.isEmpty())
			return resultJ.getJSONArray(key);
		else
			return null;

	}

	/**
	 * 获取基础分类
	 * 
	 * @return
	 */
	public JSONObject getBasicTypes() {
		return this.getMetaJSONObject("basicTypes");
	}

	/**
	 * 获取参数配置值
	 * 
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		JSONArray resultJArr = this.getMetaJSONArray("configs");
		if (key != null && resultJArr != null && !resultJArr.isEmpty()) {
			List<ShopSetting> configs = resultJArr.toJavaList(ShopSetting.class);
			if (configs != null && !configs.isEmpty()) {
				for (ShopSetting config : configs) {
					JSONObject configJ = config.toJSON();
					if (StringUtils.isNotBlank(configJ.getString("key")) && key.equals(configJ.getString("key"))) {
						return configJ.getString("value");
					}
				}
			}
		}
		return null;
	}
}
