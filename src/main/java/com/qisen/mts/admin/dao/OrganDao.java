package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.qisen.mts.admin.model.entity.Organ;

public interface OrganDao {
	public Integer create(Organ organ);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(Organ organ);// 更新
	
	public List<Organ> list(@Param("keyword")String keyword);// 查询结果集

	public int organCount(Organ organ);//检查
}
