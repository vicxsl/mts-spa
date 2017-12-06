package com.qisen.mts.common.model.constant;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class ConfigConsts {

	/**
	 * 默认语言
	 */
	public static final String DEFAULT_LANG = "zh-CN";
	
	/**
	 * 默认分页大小
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * memcached sessionUser key前缀
	 */
	public static final String SESSION_USER = "MTS_USER_";
	
	/**
	 * memcached sessionMember key前缀
	 */
	public static final String SESSION_MEMBER = "MTS_MEMBER_";
	
	/**
	 * memcached sessionAdmin key前缀
	 */
	public static final String SESSION_ADMIN = "MTS_ADMIN_";
	
	/**
	 * memcached metaData key前缀
	 */
	public static final String METADATA_E = "MTS_META_DATA_E_";
	
	/**
	 * memcached metaData key前缀
	 */
	public static final String METADATA_S = "MTS_META_DATA_S_";
	
	/**
	 * memcached jsonData key前缀
	 */
	public static final String JSON_DATA = "MTS_JSON_DATA";
	
	/**
	 * sessionUser实效2小时
	 */
	public static final int MAX_SESSION_USER_INTERVAL = 7200;
	
	/**
	 * metaData状态实效24小时
	 */
	public static final int MAX_META_DATA_INTERVAL = 7200 * 12;
	
	/**
	 * JSON格式化
	 */
	public static final SerializerFeature[] JSON_SERIALIZER_FEATURE = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty };
}
