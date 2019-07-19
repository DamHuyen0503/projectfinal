package com.fpt.projectfinal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

public class ConvertDateTime {
	@Autowired
	SessionFactory session;
	public static Date ConvertDate(String dateTime) throws Exception {
		
		
	        

	           DateFormat sdf = new SimpleDateFormat("yyyy-mm");
	           Date startDate = null;
	        try {
	            startDate = sdf.parse(dateTime);
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return startDate;
	}
}
