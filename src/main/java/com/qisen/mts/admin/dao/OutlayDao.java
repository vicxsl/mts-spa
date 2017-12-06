package com.qisen.mts.admin.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.admin.model.entity.Outlay;
import com.qisen.mts.common.model.request.PageRequest;

@Repository
public interface OutlayDao
{

	public int check(PageRequest<JSONObject> req);

	public int create(Outlay outlay);

	public int update(Outlay outlay);

	public int delete(Outlay outlay);

	public List<Outlay> list(@Param("startIndex")Integer startIndex, @Param("endIndex")Integer endIndex,@Param("body") JSONObject outlay );
   
}
