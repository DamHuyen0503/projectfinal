package com.fpt.projectfinal.controllers.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.services.user.UserService;

@RestController
@CrossOrigin
public class UserController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	UserService userService;
	
	@RequestMapping(value = "/getUserByID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getUserByID(@RequestParam  int userID){
		System.out.println("userID:"+userID);
		try {
			User user= userService.getUserByID(userID);
	        return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updatePost(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(userService.updateUser(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}