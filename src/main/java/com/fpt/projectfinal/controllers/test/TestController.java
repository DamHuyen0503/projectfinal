package com.fpt.projectfinal.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.services.test.TestService;

@RestController
@CrossOrigin
public class TestController {

	@Autowired
	TestService testService;
	
	
}
