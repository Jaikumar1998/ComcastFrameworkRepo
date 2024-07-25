package com.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int randomNumber() {
		Random random = new Random();
		int randomno =random.nextInt(5000);
		return randomno;
	}
	
	public String getSystemDateYYYYMMDD() {
		 Date dateobj = new Date();
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String date = sdf.format(dateobj);
		 return date;
	}
	
	public String getRequiredDateYYYYMMDD(int days) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sim.format(cal.getTime());
		return reqDate;
	}

}
