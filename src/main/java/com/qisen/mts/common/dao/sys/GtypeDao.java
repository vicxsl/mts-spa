package com.qisen.mts.common.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.common.model.entity.sys.Gtype;

public interface GtypeDao {

	/**
	 * 查询级别类型
	 * 
	 * @param eid
	 * @return
	 */
	public List<Gtype> list(@Param("eid") Integer eid);
	
	/**
	 * 新增员工级别类型
	 * 
	 * @param eid
	 * @return
	 */
	public void save(Gtype gtype);

	/**
	 * 检查员工级别类型是否重复
	 * @param gtype
	 * @return
	 */
	public int count(Gtype gtype);

	/**
	 * 删除员工级别类型
	 * @param eid
	 * @return
	 */
	public void deleteEmp(@Param("eid")Integer eid, @Param("depCode")String depCode, @Param("id")int id);

	/**
	 * 修改员工级别类型
	 * @param eid
	 * @return
	 */
	public void editEmp(Gtype gtype);

}
