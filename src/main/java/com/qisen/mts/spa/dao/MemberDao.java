package com.qisen.mts.spa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.spa.model.entity.MetaData;
import com.qisen.mts.spa.model.entity.SpaIncomeDetails;
import com.qisen.mts.spa.model.entity.SpaMallOrder;
import com.qisen.mts.spa.model.entity.SpaMember;
import com.qisen.mts.spa.model.entity.SpaMyInfoGains;

public interface MemberDao {

	/**
	 * 检查会员是否已存在
	 * @param spaAccount
	 * @return
	 */
	public int check(SpaMember spa);
	
	
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
	 * 增加推荐总金额 
	 * @param spaAccount
	 */
	public void updateTotalMoney(List<SpaIncomeDetails> list);
	
	/**
	 * 被推广人员收货增加推广人余额
	 * @param spaAccount
	 */
	public void addBalance(SpaMallOrder spa);
	
	/**
	 * 提现减少余额
	 * @param spaAccount
	 */
	public int reduceBalance(SpaMember spa);
	
	/**
	 * 编辑spa账号 
	 * @param spaAccount
	 */
	public void update(SpaMember spa);
	
	public List<SpaMember> profitLevelOne(SpaMember spa);

	/**
	 * 获取推荐会员所带来的收益
	 * @param spa
	 * @return
	 */
	public List<SpaMember> memberIncomeDetailsList(SpaMember spa);
	

	public List<SpaMember> levelTwo(@Param("list")List<SpaMember> list,@Param("eid")String eid);

	public List<SpaMember> levelThree(@Param("list")List<SpaMember> list,@Param("eid")String eid);

	public SpaMyInfoGains myInfoGains(SpaMyInfoGains spa);
	
	//获取小程序metaData
	public MetaData getMallMetaData(SpaMember spa);
	//获取会员
	public SpaMember getMember(SpaMember spa);
	
}
