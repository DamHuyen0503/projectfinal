package com.fpt.projectfinal.controllers.usertest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.UserTest;
import com.fpt.projectfinal.services.usertest.UserTestService;

@RestController
@CrossOrigin
public class UserTestController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	UserTestService userTestService;
	
	/*
	 * create user test. 
	 * ユーザーテストを作成します。
	 */
	@RequestMapping(value = "/addUserTest", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> addUserTest(@RequestBody Map<String, Object> payload) {
		
		System.out.println("check user test ");	
		try {
			
//			userTestService.addUserTest(payload);
	        return new ResponseEntity<>(userTestService.addUserTest(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Count the total number of users taking the test.
	 * テストを受けるユーザーの総数を数えます。
	 */
	@RequestMapping(value = "/getNumberOfUserTest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getNumberOfUserTest() {
		try {
			Long count = userTestService.getNumberOfUserTest();
		
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * get all the tests that the user has done.
	 * ユーザーが行ったすべてのテストを取得します。
	 */
	@RequestMapping(value = "/getTestByUserSendRequest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getTestByUserSendRequest() {
		try {
			
			return new ResponseEntity<>(userTestService.getUserTestByUser(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
