package com.qisen.mts.common.dao.sys;

import com.qisen.mts.common.model.entity.sys.Enterprise;

public interface EnterpriseDao {
	
	public Enterprise find(Integer id);
	
	public String findTbuser(Integer eid);
}
