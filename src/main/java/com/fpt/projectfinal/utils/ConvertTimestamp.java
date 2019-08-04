package com.fpt.projectfinal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ConvertTimestamp {
	public static Date ConvertDateTime(String day) throws ParseException {
//		String day = "2019-08-19T21:00:00.000Z";
		 String[] d = day.split(Pattern.quote("."));
		 LocalDateTime dateTime = LocalDateTime.parse(d[0]);
		 Duration durationTaken = Duration.of(7, ChronoUnit.HOURS);
		 LocalDateTime endDate = dateTime.plus(durationTaken);
		 Date date = java.sql.Timestamp.valueOf(endDate);
		 return date;
		
	}
}
