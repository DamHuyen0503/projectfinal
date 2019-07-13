package com.fpt.projectfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fpt.projectfinal.utils.ConvertDateTime;

@SpringBootApplication()
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		ConvertDateTime cv = new ConvertDateTime();
		cv.ConvertDate("2019-07-11T12:45:23.769Z");
	}

}
