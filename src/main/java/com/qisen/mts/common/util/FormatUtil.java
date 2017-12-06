package com.qisen.mts.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class FormatUtil {

	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @param pattern
	 * @return
	 */
	public static Double formatNum(Object number, String decimal) {
		int scale = 2;
		if (StringUtils.isNoneBlank(decimal))
			scale = Integer.valueOf(decimal);
		BigDecimal result = new BigDecimal(number.toString()).setScale(scale, BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();
	}
}
