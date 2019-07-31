package com.fpt.projectfinal.controllers.medicarRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.bcel.classfile.Constant;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.services.medicalrecord.MedicalRecordService;
import com.fpt.projectfinal.services.role.RoleService;

@RestController
@CrossOrigin
public class MedicalRecordController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	final String ROLEMANAGER = "MANAGER";
	final String ROLEEXPERT = "EXPERT";
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@Autowired 
	RoleService roleService;

	@RequestMapping(value = "/addMedicalRecord", method = RequestMethod.POST,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> createMedicalRecord(@RequestBody Map<String, Object> payload, UriComponentsBuilder builder) {

		try {
			
			return new ResponseEntity<>(medicalRecordService.addMedicalRecord(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/getMedicalRecordByID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getMedicalRecordByID(@RequestParam  int medicalRecordID){
		System.out.println("check get medical Record by ID");
		try {
			Map<String, Object> map= medicalRecordService.getMedicalRecordByID(medicalRecordID);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/updateMedicalRecord", method = RequestMethod.POST,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> updateMedicalRecord(@RequestBody Map<String, Object> payload, UriComponentsBuilder builder) {

		try {
			medicalRecordService.updateMedicalRecord(payload);
			return new ResponseEntity<>(payload, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/getMedicalRecordByDay", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getMedicalRecordByDay(@RequestParam  String day) throws Exception{
		 DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = sourceFormat.parse(day);
			System.out.println(day);
        	System.out.println(sourceFormat.format(date));
			List<Map<String, Object>> map= medicalRecordService.getMedicalRecordByDay(date);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
//			return null;
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/deleteMedicalRecord", method = RequestMethod.DELETE,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> deleteMedicalRecord(@RequestParam int medicalRecordID) {

		try {
			
			return new ResponseEntity<>(medicalRecordService.deleteMedicalRecord(medicalRecordID), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

	@RequestMapping(value = "/getAllMedicalRecord", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllMedicalRecord(@RequestParam  int clientID) {
		int check = 0;
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Set<String> roles = roleService.getRoleByToken("");
			for(String role : roles) {
				if (role.equals(ROLEMANAGER)) {
					list = medicalRecordService.getMedicalRecordByClient(clientID);
					return new ResponseEntity<>(list, HttpStatus.OK);
				}
//				if (role.equals(ROLEEXPERT)) {
//					
//				}
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
