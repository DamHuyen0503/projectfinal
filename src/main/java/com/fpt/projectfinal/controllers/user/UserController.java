package com.fpt.projectfinal.controllers.user;

import java.util.List;
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
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.services.user.UserService;

@RestController
@CrossOrigin
public class UserController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	UserService userService;
	
	
	/*
	 * get user by userID. 
	 * userIDでユーザーを取得します。 
	 */
	@RequestMapping(value = "/getUserByID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getUserByID(@RequestParam  int userID){
		System.out.println("userID:"+userID);
		try {
			
	        return new ResponseEntity<>(userService.getUserByID(userID), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * update user. 
	 * ユーザーを更新します。
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateUser(@RequestBody Map<String, Object> payload) {

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
	
	/*
	 * update information user send request. 
	 * 更新情報ユーザー送信リクエスト。
	 */
	@RequestMapping(value = "/updateUserSendRequest", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateUserSendRequest(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(userService.updateUserSendRequest(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/*
	 * get all information user. 
	 * すべての情報ユーザーを取得します。
	 */
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllUser(@RequestBody Map<String, Object> payload) {
		try {
			List<Map<String, Object>> list = userService.getAllUser(payload);
			if (list != null) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * count total users.
	 * 合計ユーザーをカウントします。
	 */
	@RequestMapping(value = "/getNumberOfUser", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getNumberOfUser() {
		try {
			Long count = userService.getNumberOfUser();
		
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
	 * get information user send request. 
	 * 情報ユーザーがリクエストを送信します。
	 */
	@RequestMapping(value = "/getUserSendRequest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getUserSendRequest(){
		try {
			Map<String, Object> user= userService.getUserSendRequest();
	        return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Get the user information as an expert.
	 * エキスパートとしてユーザー情報を取得します。
	 */
	@RequestMapping(value = "/getAllExpert", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getAllExpert(){
		try {
			 
	        return new ResponseEntity<>(userService.getAllExpert(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
