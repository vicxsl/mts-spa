package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Admin;

public interface AdminDao {

	/**
	 * 查询运营系统账号列表
	 * @param body。keyword
	 * @return
	 */
	public List<Admin> list(@Param("body") JSONObject body);
	
	/**
	 * 检查账号是否已存在
	 * @param admin
	 * @return
	 */
	public int check(Admin admin);

	/**
	 * 新增后台账号 
	 * @param admin
	 */
	public void create(Admin admin);
	
	/**
	 * 删除后台账号
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") Integer id);

	/**
	 * 编辑后台账号
	 * @param body
	 * @return
	 */
	public int edit(Admin body);
	/**
	 * 通过手机号码查询账号
	 * @param mobile
	 * @return
	 */
	public Admin findAdmin(@Param("mobile")String mobile);

	public Admin fingById(@Param("id")int id);
	
}
