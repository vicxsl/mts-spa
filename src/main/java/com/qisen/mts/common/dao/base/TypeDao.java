package com.qisen.mts.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.base.Type;

public interface TypeDao {

	/**
	 * 查询基础分类
	 * 
	 * @param eid
	 * @param sid 0或空为企业全部数据，反之查询门店自定义数据
	 * @param body.type 0全部1项目2商品3会员4日常开支
	 * @param body.status 0删除1正常
	 * @return
	 */
	public List<Type> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 创建基础分类
	 * 
	 * @param baseType
	 * @return
	 */
	public Integer create(Type baseType);

	/**
	 * 修改基础分类
	 * 
	 * @param baseType
	 * @return 
	 */
	public Integer update(Type baseType);
	
	/**
	 * 修改状态
	 * 
	 * @param eid
	 * @param body.status 0删除1正常
	 * @param body.ids ID数组
	 * @return
	 */
	public Integer status(@Param("eid") Integer eid, @Param("body") JSONObject body);

	/**
	 * 检查编号重复
	 * 
	 * @param eid
	 * @param sid
	 * @param type 1项目2商品3会员4日常开支
	 * @param no
	 * @param id
	 * @return
	 */
	public Integer check(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("type") Integer type, @Param("no") String no, @Param("id") Integer id);

}
