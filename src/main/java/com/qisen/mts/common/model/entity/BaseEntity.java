package com.qisen.mts.common.model.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.constant.ConfigConsts;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JSON.toJSONString(this, ConfigConsts.JSON_SERIALIZER_FEATURE);
	}
	
	public JSONObject toJSON() {
		return JSON.parseObject(this.toString());
	}
}
