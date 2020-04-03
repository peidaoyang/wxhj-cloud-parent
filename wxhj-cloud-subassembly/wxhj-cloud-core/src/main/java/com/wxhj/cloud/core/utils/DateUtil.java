package com.wxhj.cloud.core.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date calcDate(Date date,int field, int amount) 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	public static int getDateNumber(Date date,int field)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(field);
	}
}
