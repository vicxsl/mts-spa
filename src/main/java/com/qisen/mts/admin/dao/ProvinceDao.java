package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.admin.model.entity.Province;

public interface ProvinceDao {
	public Integer create(Province province);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(Province province);// 更新
	
	public List<Province> list(@Param("keyword")String keyword);// 查询结果集

	public int checkProvince(Province province);//检查是否已存在
}
