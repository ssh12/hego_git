package cn.gzsxt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat year = new SimpleDateFormat("yyyy");
	private static DateFormat month = new SimpleDateFormat("MM");
	private static DateFormat day = new SimpleDateFormat("dd");
	
	/**
	 * 生成 Date   yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public static Date getDate(String dateStr){
		Date date1 = null;
		if(dateStr != null){
			try {
				date1 = date.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date1;
	}
	
	/**
	 * 生成 Date   yyyy-MM-dd HH:mm:ss
	 * @param dateTimeStr
	 * @return
	 */
	public static Date getDateTime(String dateTimeStr){
		Date date1 = null;
		if(dateTimeStr != null){
			try {
				date1 = dateTime.parse(dateTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date1;
	}
	
	
	/**
	 * 生成 Date字符串   yyyy-MM-dd
	 * @param date1
	 * @return
	 */
	public static String getDateStr(Date date1){
		String dateStr = null;
		if(date1 instanceof Date){
			dateStr = date.format(date1);
		}
		return dateStr;
	}
	
	/**
	 * 生成 Date字符串   yyyy-MM-dd HH:mm:ss
	 * @param date1
	 * @return
	 */
	public static String getDateTimeStr(Date date1){
		String dateStr = null;
		if(date1 instanceof Date){
			dateStr = dateTime.format(date1);
		}
		return dateStr;
	}
	/**
	 * 生成 Date字符串   yyyy
	 * @param date1
	 * @return
	 */
	public static String getYearStr(Date date1){
		String yearStr = null;
		if(date1 instanceof Date){
			yearStr = year.format(date1);
		}
		return yearStr;
	}
	/**
	 * 生成 Date字符串   MM
	 * @param date
	 * @return
	 */
	public static String getMonthStr(Date date) {
		String monthStr = null;
		if(date instanceof Date) {
			monthStr = month.format(date);
		}
		return monthStr;
	}
	/**
	 * 生成 Date字符串   dd
	 * @param date
	 * @return
	 */
	public static String getDayStr(Date date) {
		String dayStr = null;
		if(date instanceof Date) {
			dayStr = day.format(date);
		}
		return dayStr;
	}
}
