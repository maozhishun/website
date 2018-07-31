package com.gz.utils;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeValue {
	/**
	 * 判断一个字符串是否为空 或者是空字符串 如果是则返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOfString(String str) {
		if (null == str || str.replaceAll(" ", "").length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个long类型的数字是否为空或者0
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isNullOr0OfLong(Long l) {
		if (null == l || l == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个int类型的数字是否为空或者0
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isNullOr0OfInteger(Integer i) {
		if (null == i || i == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个int类型的数字是否为空或者小于0
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isNullOrL0OfInteger(Integer i) {
		if (null == i || i < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个list是否为null或者长度为0
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrS0OfList(List list) {
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}
	
}
