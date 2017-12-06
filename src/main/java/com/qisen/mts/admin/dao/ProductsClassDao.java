package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.qisen.mts.admin.model.entity.ProductsClass;

public interface ProductsClassDao {
	public Integer create(ProductsClass productsClass);// 新增

	public Integer delete(Integer id);// 删除

	public Integer update(ProductsClass productsClass);// 更新
	
	public List<ProductsClass> list(@Param("keyword")String keyword);// 查询结果集

	public int productsClassCheck(ProductsClass productsClass);//检查
}
