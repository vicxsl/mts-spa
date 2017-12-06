package com.qisen.mts.common.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qisen.mts.common.model.entity.BaseEntity;

public class CollectionUtil {

	public static JSONArray toJSONArray(List<? extends BaseEntity> list, boolean withKey, String... keys) {
		JSONArray array = new JSONArray();
		for (BaseEntity baseEntity : list) {
			if (keys == null || keys.length == 0) {
				array.add(baseEntity.toJSON());
			} else {
				JSONObject baseJ = baseEntity.toJSON();
				if (withKey) {
					JSONObject json = new JSONObject();
					for (String key : keys) {
						json.put(key, baseJ.get(key));
					}
					array.add(json);
				} else {
					for (String key : keys) {
						array.add(baseJ.get(key));
					}
				}
			}
		}
		return array;
	}
}
