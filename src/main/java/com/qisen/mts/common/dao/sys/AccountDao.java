package com.qisen.mts.common.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.Account;
import com.qisen.mts.common.model.request.PageRequest;

public interface AccountDao {
	
	/**
	 * 创建账号
	 * 
	 * @param account
	 * @return
	 */
	public Integer create(Account account);
	
	/**
	 * 更新账号
	 * 
	 * @param account
	 * @return
	 */
	public Integer update(Account account);
	
	/**
	 * 修改账号状态
	 * 
	 * @param eid
	 * @param body.status 0删除1正常2停用
	 * @param body.ids ID数组
	 */
	public int status(@Param("eid") Integer eid, @Param("body") JSONObject body);
	
	/**
	 * 获取账号列表
	 * 
	 * @param eid
	 * @param sid
	 * @param body.status 0删除1正常2停用 
	 * @return
	 */
	public List<Account> list(PageRequest<JSONObject> req);
	
	/**
	 * 获取账号总数
	 * 
	 * @param eid
	 * @param sid
	 * @param body.status 0删除1正常2停用 
	 * @return
	 */
	public Integer count(PageRequest<JSONObject> req);
	
	/**
	 * 检查账号是否存在
	 * 
	 * @param account
	 * @return
	 */
	public Integer check(@Param("account") String account, @Param("id") Integer id);
	
	/**
	 * 查询账号登录用
	 * 
	 * @param account
	 * @return
	 */
	public Account find4Login(String account);
	
	/**
	 * 查询账号验证TOKEN用
	 * 
	 * @param token
	 * @return
	 */
	public Account findByToken(String token);
	
	/**
	 * 更新登录状态
	 * 
	 * @param id
	 * @param token
	 * @param scope
	 * @return
	 */
	public Integer update4Login(@Param("id") Integer id, @Param("token") String token, @Param("scope") String scope);

	/**
	 * 更新登录状态
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	public int rights(@Param("eid") Integer eid, @Param("body") Account body);

	public Account findAccount(@Param("eid")Integer eid, @Param("id")Integer id);
}
