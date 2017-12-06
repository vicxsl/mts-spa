package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Company;

public interface CompanyDao {

	/**
	 * 查询产品公司
	 * 
	 * @param eid
	 * @param sid 0或空为企业全部数据，反之查询门店自定义数据
	 * @param body.status 0删除1正常
	 * @return
	 */
	public List<Company> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 创建产品公司
	 * 
	 * @param company
	 * @return
	 */
	public Integer create(Company company);

	/**
	 * 修改产品公司
	 * 
	 * @param company
	 * @return 
	 */
	public Integer update(Company company);
	
	/**
	 * 修改状态
	 * 
	 * @param eid
	 * @param body.status 0删除1正常
	 * @param body.ids ID数组
	 * @return
	 */
	public Integer status(@Param("eid") Integer eid, @Param("body") JSONObject body);

	/**
	 * 检查编号重复
	 * 
	 * @param company
	 * @return
	 */
	public Integer check(Company company);

}
