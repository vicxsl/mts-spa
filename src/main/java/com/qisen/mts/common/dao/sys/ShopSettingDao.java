package com.qisen.mts.common.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.sys.ShopSetting;

public interface ShopSettingDao {
	
	/**
	 * 更新门店配置
	 * @param eid
	 * @param sid
	 * @param body
	 * @return
	 */
	public int update(@Param("eid")Integer eid, @Param("sid") Integer sid,@Param("body") JSONArray body);
	
	/**
	 * 查询门店配置
	 * @param eid
	 * @param sid
	 * @param body
	 * @return
	 */
	public List<ShopSetting> list(@Param("eid") Integer eid, @Param("sid") Integer sid, @Param("body") JSONObject body);

	/**
	 * 创建门店配置
	 * @param eid
	 * @param sid
	 * @param body
	 * @return
	 */
	public int create(@Param("eid")Integer eid, @Param("sid") Integer sid,@Param("body") JSONArray body);

	/**
	 * 删除门店记录
	 * @param eid
	 * @param sid
	 * @return
	 */
	public int delete(@Param("eid")Integer eid,@Param("sid") Integer sid,@Param("mid") Object mid);
	
}
