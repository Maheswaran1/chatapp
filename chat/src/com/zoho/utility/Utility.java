package com.zoho.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
	
	public Date getDate(long timeInMillisec) {
		TimeZone timeZone = TimeZone.getDefault();
		long time = timeInMillisec+timeZone.getOffset(timeInMillisec);
		Date date = new Date(time);
		return date;
	}
	
	public long getCurrentTime() {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat simpleDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = null;
		try {
		 date = simpleDate1.parse(simpleDate.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long millis = date.getTime();
		return millis;
		
	}
	
	public long nextPasswordChageDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		Date date = calendar.getTime();
		Long nextChangeTime = date.getTime();
		return nextChangeTime;
		
	}
}
