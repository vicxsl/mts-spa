package com.qisen.mts.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.constant.ConfigConsts;

import net.rubyeye.xmemcached.MemcachedClient;

@Component
public class JSONUtil {

	@Autowired
	private MemcachedClient memcachedClient;

	/**
	 * 读取json文件
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public JSONObject readJSON(String name, String lang) throws Exception {

		JSONObject result = null;

		String realName = name.split(".json")[0] + "_" + (StringUtils.isNotBlank(lang) ? lang : ConfigConsts.DEFAULT_LANG) + ".json";

		String jsonStr = memcachedClient.get(ConfigConsts.JSON_DATA + realName);
		if (StringUtils.isNotBlank(jsonStr))
			result = JSON.parseObject(jsonStr);

		if (result == null) {
			BufferedReader bufferedReader = null;
			try {
				InputStream inputStream = getClass().getResourceAsStream("/json/" + jsonStr);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				bufferedReader = new BufferedReader(inputStreamReader);
				StringBuffer strBuffer = new StringBuffer();
				String tempStr = null;
				while ((tempStr = bufferedReader.readLine()) != null) {
					strBuffer.append(tempStr);
				}
				bufferedReader.close();
				if (StringUtils.isNotBlank(strBuffer)) {
					result = JSON.parseObject(strBuffer.toString());
					if (!memcachedClient.add(ConfigConsts.JSON_DATA + realName, 0, strBuffer.toString()))
						memcachedClient.replace(ConfigConsts.JSON_DATA + realName, 0, strBuffer.toString());
				}
			} finally {
				if (bufferedReader != null)
					bufferedReader.close();
			}
		}

		return result;
	}

	/**
	 * 获取JSONObject非空double
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static double getNotNullDouble(JSONObject obj, String key) {
		if (obj == null || StringUtils.isBlank(key) || !obj.containsKey(key))
			return 0;
		return obj.getDouble(key) == null ? 0 : obj.getDoubleValue(key);
	}

	/**
	 * 获取JSONObject非空int
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static int getNotNullInteger(JSONObject obj, String key) {
		if (obj == null || StringUtils.isBlank(key) || !obj.containsKey(key))
			return 0;
		return obj.getInteger(key) == null ? 0 : obj.getIntValue(key);
	}

	/**
	 * 获取JSONObject非空字符串
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static String getNotNullString(JSONObject obj, String key) {
		if (obj == null || StringUtils.isBlank(key) || !obj.containsKey(key))
			return "";
		return StringUtils.isBlank(obj.getString(key)) ? "" : obj.getString(key);
	}
}
