package com.gz.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/**
	 * 将字符串格式的时间转为另一字符串时间格式
	 * 
	 * @param orig
	 *            源时间格式
	 * @param dest
	 *            目标时间格式
	 * @param dateStr
	 *            需要转换的时间
	 * @return
	 * @throws Exception
	 */
	public static String strToStr(String orig, String dest, String dateStr) {
		DateFormat df = new SimpleDateFormat(orig);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat sdf = new SimpleDateFormat(dest);
		return sdf.format(date);
	}

	/**
	 * 将时间转为字符串格式
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param dest
	 *            目标时间格式
	 * @return
	 * @throws Exception
	 */
	public static String dateToStr(Date date, String dest) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(dest);
			return df.format(date);
		} else {
			return "";
		}

	}

	/**
	 * 将时间转为字符串格式
	 * 
	 * @param str
	 *            需要转换的时间格式
	 * @param dateStr
	 *            需要转换的时间
	 * @return
	 * @throws Exception
	 */
	public static Date strToDate(String str, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得指定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	public static Date setDayEndByDate(Date date) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	public static Date setDayBeginByDate(Date date) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static int getYearByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getMonthByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getDayByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getBeforeOrAfterDayByGivenDate(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, num);

		return cal.getTime();

	}

	public static void main(String[] args) {
		System.out.println(getYearByDate(new Date()));
	}
}
