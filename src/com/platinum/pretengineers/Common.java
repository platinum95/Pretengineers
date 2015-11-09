package com.platinum.pretengineers;

import java.util.Calendar;

public class Common {

	static int course, group, day, hour;
	static String groupXmlURL, courseXmlURL;
	static String[] groupURLs = {"A1", "A2", "A3", "A4", "A5", "A6"};
	static String[] courseURLs = {"eng", "engwm"};
	static String[][] currentClasses;
	static Calendar cal;
	
	
	
	static void getTime(){
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_WEEK);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		
		
	}
}
