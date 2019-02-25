package com.qisen.mts.spa.dao;

import com.qisen.mts.spa.model.entity.SpaMember;

public interface MemberDao {

	/**
	 * 检查会员是否已存在
	 * @param spaAccount
	 * @return
	 */
	public SpaMember check(SpaMember spa);
	
	/**
	 * 删除spa账号
	 * @param spaAccount
	 * @return
	 */
	public int delete(SpaMember spa);

	/**
	 * 新增spa账号 
	 * @param spaAccount
	 */
	public void create(SpaMember spa);
	
	/**
	 * 编辑spa账号 
	 * @param spaAccount
	 */
	public void update(SpaMember spa);
	

}
