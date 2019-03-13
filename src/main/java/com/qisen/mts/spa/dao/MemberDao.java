package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaMyInfoGains;

public interface MemberDao {

	/**
	 * 检查会员是否已存在
	 * @param spaAccount
	 * @return
	 */
	public SpaMember check(SpaMember spa);
	
	
	/**
	 * 新增或更新会员
	 * @param spa
	 * @return
	 */
	public void saveOrUpdate(SpaMember spa);
	
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
	
	public List<SpaMember> profitLevelOne(SpaMember spa);

	public List<SpaMember> levelTwo(@Param("list")List<SpaMember> list,@Param("eid")String eid);

	public List<SpaMember> levelThree(@Param("list")List<SpaMember> list,@Param("eid")String eid);

	public SpaMyInfoGains myInfoGains(SpaMyInfoGains spa);
	
	//获取小程序metaData
	public MetaData getMallMetaData(SpaMember spa);
	

}
