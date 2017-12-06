package com.qisen.mts.common.util;

public class DBContextHolder {
	/**
	 * 线程threadlocal
	 */
	private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public DBContextHolder(){
		throw new AssertionError();
	}

	public static final String DB_TYPE_RW = "dataSourceKeyRW";
	public static final String DB_TYPE_R = "dataSourceKeyR";

	public static String getDbType() {
		String db = contextHolder.get();
		if (db == null) {
			db = DB_TYPE_RW;// 默认是读写库
		}
		return db;
	}

	/**
	 * 设置本线程的dbtype
	 * @param str 默认读写不需要配置，在确认只读的时候才需要指定
	 */
	public static void setDbType(String str) {
		contextHolder.set(str);
	}

	/**
	 * clearDBType
	 * 
	 * @Title: clearDBType
	 * @Description: 清理连接类型
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
