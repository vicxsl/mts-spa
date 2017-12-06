package com.qisen.mts.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qisen.mts.admin.model.entity.Goods;

@Repository
public interface GoodsDao {

	public int check(Goods goods);

	public void create(Goods goods);

	public int update(Goods goods);

	public int delete(Goods goods);

	public List<Goods> list(@Param("no") int i);

}
