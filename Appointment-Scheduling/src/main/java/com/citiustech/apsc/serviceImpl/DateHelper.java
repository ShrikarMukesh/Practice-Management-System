package com.citiustech.apsc.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
	public static void main(String[] args) {
		//System.out.println(getDayNumberOld(new Date(2021-06-13)));;
		//getDayStringOld(new Date(2021-06-13),"En");
		System.out.println(getDayStringNew(LocalDate.of(2017, 1, 13),Locale.ENGLISH));
	}
	public static int getDayNumberOld(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	public static String getDayStringOld(Date date, Locale locale) {
	    DateFormat formatter = new SimpleDateFormat("EEEE", locale);
	    return formatter.format(date);
	}
	
	public static String getDayStringNew(LocalDate date, Locale locale) {
	    DayOfWeek day = date.getDayOfWeek();
	    return day.getDisplayName(TextStyle.FULL, locale);
	}
}
