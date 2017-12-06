package com.qisen.mts.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取日期
	 */
	public static String getDate(int month, int day_of_month) {
		String date = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day_of_month);
			date = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回不为NULL值的字符值
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null)
			return "";
		return str.trim();
	}
	 /**
     * 判断一个字符串是否全为数字
     * 
     * @param String inputString 输入字符串
     * @author infomobile
     * @return boolean 是否全为数字
     */
    public static boolean isNumberic(String inputString)
    {
        if (inputString == null)
        {
            return false;
        }
        if (inputString.length() > 0)
        {
            for (int i = 0; i < inputString.length(); i++)
            {
                if (inputString.charAt(i) < '0' || inputString.charAt(i) > '9')
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
	/**
	 * 判断是否为yyyy-mm-dd格式的日期数据
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date, String split) {
		date = DateUtil.isNull(date);
		if (date.length() != 10)
			return false;
		else {
			String year = date.substring(0, 4);
			String split1 = date.substring(4, 5);
			String month = date.substring(5, 7);
			String split2 = date.substring(7, 8);
			String day = date.substring(8, 10);

			if (isNumberic(year) == false || isNumberic(month) == false || isNumberic(day) == false)// 判断年月日是否为数字
				return false;
			if (Integer.parseInt(year) < 1900 || Integer.parseInt(month) > 12
					|| Integer.parseInt(day) > getDays(year, month))// 判断年分月分与天数是否正确
				return false;
			if (!split1.equals(split) || !split2.equals(split))// 判断分隔符是否是指定的分隔符
				return false;
		}
		return true;
	}

	/**
	 * 返回两个string类型日期之间相差的天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @param isContain
	 * @param true:包含第一天
	 * @return
	 */
	public static int daysBetween(String smdate, String bdate, boolean isContain) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		long time1 = 0;
		long time2 = 0;

		cal.setTime(sdf.parse(smdate));
		time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		int day = Integer.parseInt(String.valueOf(between_days));
		if (isContain) {
			day = day + 1;
		}
		return day;
	}

	/**
	 * 将date转成日期格式的字符串 Date==>String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	/**
	 * 将date转成日期格式的字符串 Date==>String
	 * 
	 * @param date
	 * @return dd/MM/yyyy日期格式
	 */
	public static String dateToLangString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获得当前年
	 * 
	 * @return String 当前年的字符串，格式：yyyy
	 */
	public static String getThisYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}

	/**
	 * 获得当前月份
	 * 
	 * @return String 当前年的字符串，格式：MM
	 */
	public static String getThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(new Date());
	}

	/**
	 * 获取某个月的天数
	 */
	public static int getDays(String year, String month) {
		int days = 0;
		String day1 = ",01,03,05,07,08,10,12,", day2 = ",02,", day3 = ",04,06,09,11,";

		month = "," + month + ",";
		if (day1.indexOf(month) > -1)
			days = 31;
		else if (day2.indexOf(month) > -1) {
			if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
					|| Integer.parseInt(year) % 400 == 0)
				days = 29;
			else
				days = 28;

		} else if (day3.indexOf(month) > -1)
			days = 30;
		return days;
	}

	/**
	 * 得到当前日期字符串，格式为yyyy-mm-dd
	 * 
	 * @return 当前时间字符串，格式为yyyy-mm-dd
	 */
	public static String getCurDateStr() {
		long ms = System.currentTimeMillis();
		java.sql.Date curDate = new java.sql.Date(ms);
		return curDate.toString();
	}

	public static void main(String args[]) {
		try {
			System.out.println(getDate(-1, 1));
			System.out.println(getDate(0, 1));
		} catch (Exception e) {

		}
	}
}
