package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.admin.model.entity.Employee;

public interface EmployeeDao {
	public Integer create(Employee employee);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(Employee employee);// 更新
	
	public List<Employee> list(@Param("keyword")String keyword, @Param("orgNo")int orgNo);// 查询结果集

	public int check(Employee employee);//检查
}
