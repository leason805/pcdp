package com.boxun.estms.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.getTime();
	}
	
	/**
	 * mid night of date
	 * @return
	 */
	public static Date getCurrentMNDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentMNDate());
		System.out.println(formatDateTime(new Date(), Const.DATE_FORMAT_0));
		//System.out.println(addYearDate(getFirstDatetimeOfYear(),1));
	}
	
	public static Date getCurrentTimeNoSec(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getFirstDatetimeOfYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(calendar.MONTH, 0);
		calendar.set(calendar.DAY_OF_YEAR, 1);
		calendar.set(calendar.AM_PM, calendar.AM);
		calendar.set(calendar.HOUR, 0);
		calendar.set(calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date addYearDate(Date srcTime, int year){
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(srcTime);
			calendar.add(Calendar.YEAR, year);
			return calendar.getTime();
		}
		catch(Exception e){
			return null;
		}	
	}
	
	public static Date addHourDate(Date srcTime, int hours){
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(srcTime);
			calendar.add(Calendar.HOUR, hours);
			return calendar.getTime();
		}
		catch(Exception e){
			return null;
		}	
	}
	
	public static String formatDateTime(Date srcTime, String format){
		try{
			if(srcTime != null){
				SimpleDateFormat  sdf  =  new  SimpleDateFormat(format);
				return sdf.format(srcTime);
			}
		}
		catch(Exception e){
			return "";
		}
		return "";
	}
	
	public static String formatDateTime(Date srcTime){
		try{
			if(srcTime != null){
				SimpleDateFormat  sdf  =  new  SimpleDateFormat(Const.DATE_FORMAT_1);
				return sdf.format(srcTime);
			}
			
		}
		catch(Exception e){
			return "";
		}
		return "";
	}
	
	public static Date formatDate(String srcTime, String format){
		try{
			SimpleDateFormat  sdf  =  new  SimpleDateFormat(format);
			Date date = sdf.parse(srcTime);
		return date;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static Date formatDate(String srcTime){
		try{
			SimpleDateFormat  sdf  =  new  SimpleDateFormat(Const.DATE_FORMAT_1);
			Date date = sdf.parse(srcTime);
			return date;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static Date addMinuteDate(String srcTime, int minutes, String format){
		try{
			Date date = formatDate(srcTime, format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, minutes);
			return calendar.getTime();
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	public static Date addHourDate(String srcTime, int hours, String format){
		try{
			Date date = formatDate(srcTime, format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, hours);
			return calendar.getTime();
		}
		catch(Exception e){
			return null;
		}	
	}
	
	
	
	public static String addMinuteString(String srcTime, int minutes, String format){
		try{
			SimpleDateFormat  sdf  =  new  SimpleDateFormat(format);
			Date date = addMinuteDate(srcTime, minutes, format);
			return sdf.format(date);
		}
		catch(Exception e){
			return "";
		}
	}
	
	public static String addHourString(String srcTime, int hours, String format){
		try{
			SimpleDateFormat  sdf  =  new  SimpleDateFormat(format);
			Date date = addHourDate(srcTime, hours, format);
			return sdf.format(date);
		}
		catch(Exception e){
			return "";
		}
	}
	
	public static boolean timeBefore(String srcTime, Date time, String format){
		try{
			Date date = formatDate(srcTime, format);
			return time.before(date);
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean timeBeforeToday(Date time){
		try{
			Date date = new Date();
			return time.before(date);
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean timeAfterToday(Date time){
		try{
			Date date = new Date();
			return time.after(date);
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean timeAfter(String srcTime, Date time, String format){
		try{
			Date date = formatDate(srcTime, format);
			return time.after(date);
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean timeBetween(String startTime, String endTime, Date time, String format){
			return (timeAfter(startTime,time,format) && timeBefore(endTime, time, format));
	}
	
	public static boolean timeEqual(String srcTime, Date time, String format){
		return (!timeAfter(srcTime,time,format) && !timeBefore(srcTime, time, format));
	}
	
	public static Double timeDiffInMin(String startTime, String endTime, String format){
		Date startDay = formatDate(startTime, format);
		Date endDay = formatDate(endTime, format);
		return timeDiffInMin(startDay, endDay);
	}
	
	public static Double timeDiffInMin(Date startTime, Date endTime){
		if(endTime.before(startTime)){
			return 0.0;
		}
		return new Double((endTime.getTime() - startTime.getTime())/(60*1000));
	}
	
	public static Double timeDiffInMonth(Date startDate, Date endDate){
		if(endDate == null){
			endDate = getCurrentMNDate();
		}
		double months=0;//相差月份
		
		int startYear = startDate.getYear();
		int startMonth = startDate.getMonth();
		int startDay = startDate.getDate();
		
		int endYear = endDate.getYear();
		int endMonth = endDate.getMonth();
		int endDay = endDate.getDate();
		
		months=endMonth - startMonth + (endYear - startYear)*12.0 - 1;
		
		months += (endDay - startDay + 30)/30d;
		BigDecimal b = new BigDecimal(months);  
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	public static Integer timeDiffInMinInt(Date startTime, Date endTime){
		return new Integer((endTime.getTime() - startTime.getTime())/(60*1000)+"");
	}
	
	public static int minutes(String time){
		if(StringUtil.isNotBlank(time)){
			String hour = time.substring(0,2);
			String min = time.substring(2);
			return Integer.parseInt(hour)*60 + Integer.parseInt(min);
		}
		return 0;
	} 
	
}
