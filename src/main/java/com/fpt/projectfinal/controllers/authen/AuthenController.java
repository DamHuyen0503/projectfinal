package com.fpt.projectfinal.controllers.authen;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.services.authentication.AuthenService;

@CrossOrigin
@RestController
@ComponentScan({"com.fpt.projectfinal.controllers"})
public class AuthenController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	AuthenService authenService;
	
	
	
	@RequestMapping(value = "/getAllAccount", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Account> getAllAccount() {
		List<Account> list = authenService.getAllAccount();
		return list;
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> addAccount(@RequestBody Map<String, Object> payload) {
		
		try {
			return new ResponseEntity<>(authenService.addAccount(payload), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/getByEmail", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getByEmail(@RequestBody Map<String, Object> payload) {
		Account acc = authenService.getAccountByEmail((String)payload.get("email"));
			return acc.getEmail();
	}
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateAccount(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(authenService.updateAccount(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
