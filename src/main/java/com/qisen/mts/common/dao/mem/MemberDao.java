package com.qisen.mts.common.dao.mem;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.mem.Member;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;

public interface MemberDao {
	
	public void create(Member memMember);// 新增

	public void update(Member memMember);// 更新字段
	
	/**
	 * 查询会员登录用
	 * 
	 * @param mobile
	 * @return
	 */
	public List<Member> find4Login(@Param("mobile")String mobile,@Param("loginPwd")String loginPwd);
	
	/**
	 * 查询会员验证TOKEN用
	 * 
	 * @param token
	 * @return
	 */
	public Member findByToken(String token);
	
	/**
	 * 更新登录状态
	 * 
	 * @param id
	 * @param token
	 * @return
	 */
	public Integer update4Login(@Param("id") Integer id, @Param("token") String token);
	
	/**
	 * 消费更新
	 * 
	 * @param eid
	 * @param id
	 * @param bday
	 * @return
	 */
	public Integer update4FirstConsume(@Param("eid") Integer eid, @Param("id") Integer id, @Param("bday") Date bday);

	/**
	 * 销单回滚
	 * 
	 * @param eid
	 * @param id
	 * @return
	 */
	public Integer rollback4FirstConsume(@Param("eid") Integer eid, @Param("id") Integer id);
	
	/**
	 * 更新消费信息
	 * 
	 * @param eid
	 * @param id
	 * @param bday
	 * @param fee
	 * @param rollback
	 * @param lastEmp
	 * @return
	 */
	public Integer updateConsumeInfo(@Param("eid") Integer eid, @Param("id") Integer id, @Param("bday") Date bday, @Param("fee") Double fee, @Param("rollback") int rollback, @Param("lastEmp") String lastEmp);
	
	public Integer count(PageRequest<JSONObject> req);// 查询总个数

	public List<Member> list(PageRequest<JSONObject> req);// 查询结果集

	public Member find(@Param("eid") Integer eid, @Param("id") Integer id);// 根据id查询单个对象

	public Integer check(Member memMember);// 查询个数
	
	public void updatestatus(BaseRequest<JSONObject> req);// 更新字段
	
	public Integer importMember(Member member);//导入会员返回id

}
