package com.login.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	private static SimpleDateFormat sdfymd = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdfymdhor = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfymd2hor = new SimpleDateFormat("yyyy-MM-dd HH");
	public static String getDayOfWeek(long i){
		String day = "";
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		if(dayofweek == Calendar.MONDAY) day = "周一";
		if(dayofweek == Calendar.TUESDAY) day = "周二";
		if(dayofweek == Calendar.WEDNESDAY) day = "周三";
		if(dayofweek == Calendar.THURSDAY) day = "周四";
		if(dayofweek == Calendar.FRIDAY) day = "周五";
		if(dayofweek == Calendar.SATURDAY) day = "周六";
		if(dayofweek == Calendar.MONDAY) day = "周日";
		return day;
	}
	
	public static int getHourOfDay(long i){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public int stringtoint_date(String str) throws Exception{
		if(str.substring(0,1).equals("0")) return Integer.parseInt(str.substring(1));
		return Integer.parseInt(str);
	}
	public long getdateforstring(String date) throws Exception{
		String y = date.substring(0,4);
		int year = this.stringtoint_date(y);
		String m = date.substring(4,6);
		int month = this.stringtoint_date(m);
		String d = date.substring(6,8);
		int day = this.stringtoint_date(d);
		String h = date.substring(8);
		int hour = this.stringtoint_date(h);
		Calendar c = Calendar.getInstance();
		
		c.set(year, month - 1, day , hour, 0, 0);
		
		c.set(Calendar.MILLISECOND, 0);
//		System.out.println(c.getTime().toString()+c.getTimeInMillis());
		return c.getTimeInMillis();
	}
	public static long convertString2long(String date, int dayoff) {
		int year = converString2intArray(date)[0];
		int month = converString2intArray(date)[1];
		int day = converString2intArray(date)[2];
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day + dayoff, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	public static long convertString2longwithhour(String date, int hour, int dayoff) {
		int year = converString2intArray(date)[0];
		int month = converString2intArray(date)[1];
		int day = converString2intArray(date)[2];
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day + dayoff, hour + dayoff, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	public static int[] converString2intArray(String date) {
		int[] d = new int[3];
		d[0] = Integer.parseInt(date.split("-")[0]);
		d[1] = Integer.parseInt(date.split("-")[1]);
		d[2] = Integer.parseInt(date.split("-")[2]);
		return d;
	}

	public static String convertDouble2StringPer(double value) {
		return convertDouble2StringPer(value, 2);
	}

	public static String convertDouble2StringPer(double value, int places) {
		if (value == 0.0D) return "0%";
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(places);
		return nt.format(value);
	}

	public static String convertDouble2String(double value){
		return convertDouble2String(value, 2);
	}
	
	public static String convertDouble2String(double value, int places){
		DecimalFormat f = new DecimalFormat();
		f.setGroupingUsed(false);
		f.setMaximumFractionDigits(places);
		f.setMinimumFractionDigits(places);
		return f.format(value);
	}
	
	public static double convertStringPer2Double(String per) {
		double value = 0.0D;
		per = per.substring(0, per.length() - 1);
		value = Double.parseDouble(per);
		value /= 100.0D;
		return value;
	}

	public static String convertLong2String(long i) {
		return sdfymd.format(new Date(i));
	}

	public static String convertLong2StringHor(long i) {
		return sdfymdhor.format(Long.valueOf(i));
	}
	
	public static String convertLong2String2Hor(long i) {
		return sdfymd2hor.format(Long.valueOf(i));
	}
	
	public static long convertStringHour2Long(String time) {
		try {
			return sdfymd2hor.parse(time).getTime() ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String convertLong2YMD(long i) {
		return "";
	}

	/**
	 * 得到下一个时间段的开始点
	 * @param i
	 * @param type  0-无 1-日 2-月 3-年 4-周 5-小时
	 * @return
	 */
	public static long getNextPeriodTime(long i, int type){
		long t = i;
		if(type==TableType.DAY) t = getNextDayFirstSec(i);
		if(type==TableType.MONTH) t = getNextMonthFirstDay(i);
		if(type==TableType.YEAR) t = getNextYearDay(i);
		return t;
	}
	public static long getNextPeriodTime(long i, int type, long limit){
		long t = i;
		if(type==TableType.DAY) t = getNextDayFirstSec(i);
		if(type==TableType.MONTH) t = getNextMonthFirstDay(i);
		if(type==TableType.YEAR) t = getNextYearDay(i);
		t = t>limit? limit:t;
		return t;
	}
	
	public static long getNextHourFirstSec(long i){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int year = c.get(1);
		int month = c.get(2);
		int day = c.get(5);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		c.set(year, month, day, hour+1, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static long getNextDayFirstSec(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int year = c.get(1);
		int month = c.get(2);
		int day = c.get(5);
		c.set(year, month, day + 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	public static long getTodayLastSec(long i){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		c.set(Calendar.HOUR_OF_DAY, 23); c.set(Calendar.MINUTE, 59); c.set(Calendar.SECOND, 59); c.set(Calendar.MILLISECOND, 999);
		return c.getTimeInMillis();
	}

	public static long getNextMonthFirstDay(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
//		if (month == 0) {
//			month = 11;
//			year--;
//		} else {
//			month++;
//		}
		c.set(year, month+1, day, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	public static long getNextYearFirstDay(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int year = c.get(1);
		int month = 0;
		int day = 1;
		c.set(year + 1, month, day, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static long getNextYearDay(long i){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH);
//		int day = c.get(Calendar.DAY_OF_MONTH);
		c.set(year + 1, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	public static long getNextWeekFirstDay(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setFirstDayOfWeek(2);
		c.setTimeInMillis(i + 604800000L);
		c.set(7, 2);
		return c.getTimeInMillis();
	}

	public static long getWeekStartSec(long i){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static long getWeekEndSec(long i){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static long getPreWeekEndSec(long i) {
		i -= 7*86400000L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	public static long getPreWeekStartSec(long i) {
		i -= 7*86400000L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static long getPreDayLastSec(long i) {
		i -= 86400000L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTimeInMillis();
	}
	
	public static long getPreDayFirstSec(long i) {
		i -= 86400000L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		c.set(10, 0);
		c.set(12, 0);
		c.set(13, 0);
		c.set(14, 0);
		return c.getTimeInMillis();
	}

	public static boolean isDifferentYear(long f, long t) {
		Calendar cf = Calendar.getInstance(Locale.CHINA);
		Calendar ct = Calendar.getInstance(Locale.CHINA);
		int yearf = cf.get(1);
		int yeart = ct.get(1);
		return yearf != yeart;
	}

	public static boolean isDifferentDay(long f, long t){
		Calendar cf = Calendar.getInstance(Locale.CHINA);
		Calendar ct = Calendar.getInstance(Locale.CHINA);
		int yearf = cf.get(1);
		int monthf = cf.get(2);
		int dayf = cf.get(Calendar.DAY_OF_MONTH); 
		int yeart = ct.get(1);
		int montht = ct.get(2);
		int dayt = ct.get(Calendar.DAY_OF_MONTH);
		return (yearf != yeart) || (monthf != montht) || (dayf != dayt);
	}
	
	public static boolean isDifferentMonth(long f, long t) {
		Calendar cf = Calendar.getInstance(Locale.CHINA);
		Calendar ct = Calendar.getInstance(Locale.CHINA);
		int yearf = cf.get(1);
		int monthf = cf.get(2);
		int yeart = ct.get(1);
		int montht = ct.get(2);
		return (yearf != yeart) || (monthf != montht);
	}

	public static String getDate(long i, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(i));
	}
	
	public static int getDay(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getWeek(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		return c.get(3);
	}

	public static int getMinute(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		return c.get(Calendar.MINUTE);
	}

	public static int getMonth(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		return c.get(Calendar.MONTH)+1;
	}

	public static int getYear(long i){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		return c.get(Calendar.YEAR);
	}
	
	public static long getTodayStart(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	public static long getTodayEnd(long i){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTimeInMillis();
	}

	public static long keepHourMinute(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int hour = c.get(11);
		int minute = c.get(12);
		c.set(2012, 0, 1, hour, minute, 0);
		return c.getTimeInMillis();
	}

	//i之后最近的10分钟
	public static long getNextTenMinute(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int minute = c.get(Calendar.MINUTE);
		minute = (minute + 10) / 10 * 10;
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	
	//i之前最近的10分钟
	public static long getPreTenMinute(long i) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTimeInMillis(i);
		int minute = c.get(Calendar.MINUTE);
		minute = minute / 10 * 10;
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}

	public static int betweenDays(long f, long t) {
//		Calendar c = Calendar.getInstance();  
//		c.setTimeInMillis(t);
//		int td = c.get(Calendar.DAY_OF_YEAR);  //这种写法跨年的时候就悲剧了
//		c.setTimeInMillis(f);
//		int fd = c.get(Calendar.DAY_OF_YEAR);
		long fstart = DateUtil.getTodayStart(f);
		long tstart = DateUtil.getTodayStart(t);
		long bet = tstart - fstart;
		int bewday = (int)(bet/(24*3600*1000L));
		return bewday;
	}

	public static long getNextServalMinute(long i, int minute){
		long targettime = i+60000L*minute;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(targettime);
		c.set(Calendar.SECOND, 0); c.set(Calendar.MILLISECOND, 0);
		targettime = c.getTimeInMillis();
		return targettime;
	}
	
	public static long getNextServalDayFirstSec(long i, int day){
		long targettime = i+24*3600000L*day;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(targettime);
		c.set(Calendar.HOUR_OF_DAY, 0); c.set(Calendar.MINUTE, 0); c.set(Calendar.SECOND, 0); c.set(Calendar.MILLISECOND, 0);
		targettime = c.getTimeInMillis();
		return targettime;
	}
	
	public static String getNowTime(String dateformat) {
	     Date now = new Date();
	     SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
	     String nowTime = dateFormat.format(now);
	     return nowTime;
   }
	
	public static String convertLong2StringInModel(Object time, String model) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat(model);
		 String nowTime = dateFormat.format(time);
		 return nowTime;
	}
}
