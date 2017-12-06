package com.qisen.mts.common.dao.base;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Emp;
import com.qisen.mts.common.model.request.BaseRequest;
import com.qisen.mts.common.model.request.PageRequest;

public interface EmpDao {
	
	/**
	 * 查询门店员工（metaData用）
	 * 
	 * @param sid
	 * @return
	 */
	public List<Emp> list4MetaData(Integer sid);
	
	/**
	 * 查询可预约的员工（美一客用）
	 * 
	 * @param sid
	 * @return
	 */
	public List<Emp> listBoookingEmp(Integer sid);
	
	/**
	 * 查询门店员工
	 * 
	 * @param eid
	 * @param body.sids 门店ID数组
	 * @return
	 */
	public List<Emp> list4Shop(@Param("eid") Integer eid, @Param("body") JSONObject body);
	
	public Integer create(Emp baseEmp) throws Exception;// 新增

	public Integer delete(@Param("id") Integer id,@Param("eid") Integer eid) throws Exception;// 根据id删除

	public Integer update(Emp baseEmp) throws Exception;// 更新字段
	
	public Integer count(PageRequest<JSONObject> req);// 查询总个数
	
	public List<Emp> list(PageRequest<JSONObject> req);// 查询结果集

	public Emp find(Integer id);// 根据id查询单个对象

	public Integer check(Emp baseEmp);// 查询个数
	
	public Integer updatestatus(BaseRequest<JSONObject> req) throws Exception;// 更新字段

}
