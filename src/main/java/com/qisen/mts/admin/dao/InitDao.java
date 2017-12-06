package com.qisen.mts.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.entity.sys.Enterprise;
import com.qisen.mts.common.model.entity.sys.Shop;

public interface InitDao {

	/**
	 * 创建企业
	 * 
	 * @param enterprise
	 * @return
	 */
	public Integer createEnterprise(Enterprise enterprise);

	/**
	 * 创建单据表
	 * 
	 * @param eid
	 */
	public void createBusiBill(@Param("eid") Integer eid);

	/**
	 * 创建单据明细表
	 * 
	 * @param eid
	 */
	public void createBusiBillDetail(@Param("eid") Integer eid);

	/**
	 * 创建单据服务、提成员工表
	 * 
	 * @param eid
	 */
	public void createBusiEmpFee(@Param("eid") Integer eid);

	/**
	 * 创建单据支付流水表
	 * 
	 * @param eid
	 */
	public void createBusiPay(@Param("eid") Integer eid);

	/**
	 * 创建会员卡账户流水表
	 * 
	 * @param eid
	 */
	public void createMemCardRecord(@Param("eid") Integer eid);

	/**
	 * 创建系统日志表
	 * 
	 * @param eid
	 */
	public void createSysLog(@Param("eid") Integer eid);

	/**
	 * 创建门店
	 * 
	 * @param eid
	 * @param dataEid
	 * @param shop
	 * @return
	 */
	public Integer createShop(@Param("eid") Integer eid, @Param("dataEid") Integer dataEid, @Param("shop") Shop shop);
	
	/**
	 * 创建账号
	 * 
	 * @param account
	 * @return
	 */
	public Integer createAccount(Account account);
	
	/**
	 * 创建基础分类
	 * 
	 * @param eid
	 * @param list
	 * @return
	 */
	public Integer createDepartmentBatch(@Param("eid") Integer eid, @Param("list") JSONArray list);
	
	/**
	 * 创建基础分类
	 * 
	 * @param eid
	 * @param list
	 * @return
	 */
	public Integer createGtypeBatch(@Param("eid") Integer eid, @Param("list") JSONArray list);
	
	/**
	 * 创建基础分类
	 * 
	 * @param eid
	 * @param sid
	 * @param list
	 * @return
	 */
	public Integer createBaseTypeBatch(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("list") JSONArray list);
	
	/**
	 * 获取企业门店最大ID
	 * 
	 * @param eid
	 * @return
	 */
	public Integer getMaxShopId(@Param("eid") Integer eid);
	
	/**
	 * 更新总部连锁flag
	 * 
	 * @param eid
	 * @return
	 */
	public Integer updateShopFlag(@Param("eid") Integer eid);
	
	/**
	 * 修改门店资料
	 * @param shop
	 * @return
	 */
	public int eidtShop(Shop shop);

	public int selectSid(@Param("eid")Integer eid, @Param("name")String name);
}
