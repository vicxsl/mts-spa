package com.qisen.mts.common.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.common.model.entity.sys.Department;

public interface DepartmentDao {

	/**
	 * 查询部门
	 * 
	 * @param eid
	 * @return
	 */
	public List<Department> list(@Param("eid") Integer eid);

	/**
	 * 检查企业的部门编号或名称是否已存在
	 * @param department
	 * @return
	 */
	public int count(Department department);

	/**
	 * 新增企业部门
	 * @param department
	 */
	public void saveDep(Department department);

	/**
	 * 修改企业部门
	 * @param body
	 * @return
	 */
	public int edit(Department department);

	/**
	 * 删除企业部门
	 * @param department
	 */
	public void deleteDep(Department department);

}
