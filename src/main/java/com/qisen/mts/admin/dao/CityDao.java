package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qisen.mts.admin.model.entity.City;

public interface CityDao {
	public Integer create(City city);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(City city);// 更新
	
	public List<City> list(@Param("keyword")String keyword);// 查询结果集

	public int checkCity(City city);//检查city 是否已存在
}
