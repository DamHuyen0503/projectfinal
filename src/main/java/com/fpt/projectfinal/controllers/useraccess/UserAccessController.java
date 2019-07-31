package com.fpt.projectfinal.controllers.useraccess;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.fpt.projectfinal.services.role.RoleService;
import com.fpt.projectfinal.services.useraccess.UserAccessService;

@RestController
@CrossOrigin
public class UserAccessController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	final String ROLEEXPERT = "EXPERT";
	@Autowired
	UserAccessService userAccessService;
	
	@Autowired 
	RoleService roleService;
	
	@RequestMapping(value = "/addUserAccess", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> addUserAccess(@RequestBody  Map<String, Object> payload) {

		try {

		
			return new ResponseEntity<>(userAccessService.addUserAccess(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/updateUserAccess", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateUserAccess(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(userAccessService.updateUserAccess(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserAccessByMedicalRecordID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getAllTestByID(@RequestParam  int medicalRecordID){
		
		try {
			List<Map<String, Object>> map=  userAccessService.getUserAccessByMedicalRecordID(medicalRecordID);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserAccessByUser", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getUserAccessByUser(){
		
		try {
			Set<String> roles = roleService.getRoleByToken("");
			for(String role : roles) {
				
				if (role.equals(ROLEEXPERT)) {
					List<Map<String, Object>> map=  userAccessService.getUserAccessByUser();
					return new ResponseEntity<>(map, HttpStatus.CREATED);
				}
			}
			
			return new ResponseEntity<> ("not found", HttpStatus.BAD_REQUEST);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
