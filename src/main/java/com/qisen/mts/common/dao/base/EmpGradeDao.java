package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.EmpGrade;

public interface EmpGradeDao {
	
	/**
	 * 查询员工级别类型
	 * 
	 * @param eid
	 * @param sid 0为企业全部数据，反之查询门店自定义数据
	 * @param body.gtype 级别类型
	 * @param body.status 0删除1正常
	 * @return
	 */
	public List<EmpGrade> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 创建员工级别类型
	 * 
	 * @param empGrade
	 */
	public Integer create(EmpGrade empGrade);

	/**
	 * 修改员工级别类型
	 * 
	 * @param empGrade
	 * @return 
	 */
	public Integer update(EmpGrade empGrade);
	
	/**
	 * 修改状态
	 * 
	 * @param eid
	 * @param body.status 0删除1正常
	 * @param body.ids ID数组
	 */
	public Integer status(@Param("eid") Integer eid, @Param("body") JSONObject body);

	/**
	 * 检查编号重复
	 * 
	 * @param eid
	 * @param sid
	 * @param gtype 级别类型
	 * @param no
	 * @param id
	 * @return
	 */
	public Integer check(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("gtype") String gtype, @Param("no") String no, @Param("id") Integer id);
}
