package com.qisen.mts.common.dao.sys;

import java.util.List;

import com.qisen.mts.common.model.entity.sys.Shop;

public interface ShopDao {
	
	/**
	 * 查询企业门店（metaData用）
	 * 
	 * @param sid
	 * @return
	 */
	public List<Shop> list4MetaData(Integer eid);
	
}
