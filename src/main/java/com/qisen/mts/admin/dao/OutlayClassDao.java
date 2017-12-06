package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.qisen.mts.admin.model.entity.OutlayClass;

public interface OutlayClassDao {
	public Integer create(OutlayClass outlayClass);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(OutlayClass outlayClass);// 更新
	
	public List<OutlayClass> list(@Param("keyword")String keyword);// 查询结果集

	public int outlayClassCheck(OutlayClass outlayClass);
}
