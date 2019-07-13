package com.fpt.projectfinal.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConvertDateTime {
	public static Date ConvertDate(String dateTime) {
		
		String s = dateTime.substring(0, dateTime.length()-2);
		System.out.println(s);
//		LocalDateTime start = LocalDateTime.parse((String) notemap.get("startTime"));
//		LocalDateTime end = LocalDateTime.parse((String) notemap.get("endTime"));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String startStr = start.format(formatter);
//		String endStr = start.format(formatter);
////		
//
//		Date startTime = (Date) formatter.parse(startStr);
//		Date endTime = (Date) formatter.parse(endStr);
		
		return null;
		
	}
}
