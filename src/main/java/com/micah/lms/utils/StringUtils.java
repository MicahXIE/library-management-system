package com.micah.lms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {

    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = format.format(date);
  		
  		return curDate;
    }

    public static String getExpiryDateStr(String curDate, int duration) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	
    	try {
        	date = format.parse(curDate);
        } catch (ParseException e) {
 			e.printStackTrace();
		}

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, duration);
        date = calendar.getTime();
        String expiryDate = format.format(date);
  		
  		return expiryDate;
    }
}