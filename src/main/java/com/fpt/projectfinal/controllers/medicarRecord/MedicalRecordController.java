package com.fpt.projectfinal.controllers.medicarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.services.medicalrecord.MedicalRecordService;
import com.fpt.projectfinal.services.role.RoleService;

@RestController
@CrossOrigin
public class MedicalRecordController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	final String ROLEADMIN = "ADMIN";
	final String ROLEEXPERT = "EXPERT";
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@Autowired 
	RoleService roleService;

	/*
	 * Create new medical records.
	 * 新しい医療記録を作成します。
	 */
	@RequestMapping(value = "/addMedicalRecord", method = RequestMethod.POST,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> createMedicalRecord(@RequestBody Map<String, Object> payload) {

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
	
	/*
	 * Get all client information according to clientID.
	 * clientIDに従ってすべてのクライアント情報を取得します。
	 */
	@RequestMapping(value = "/getMedicalRecordByID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getMedicalRecordByID(@RequestParam  int medicalRecordID){
		System.out.println("check get medical Record by ID");
		try {
			Map<String, Object> map= medicalRecordService.getMedicalRecordByID(medicalRecordID);
	        return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/*
	 * Update medical record information.
	 * 医療記録情報を更新します。
	 */
	@RequestMapping(value = "/updateMedicalRecord", method = RequestMethod.POST,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> updateMedicalRecord(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(medicalRecordService.updateMedicalRecord(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/*
	 * Get all the medical record information created in the day.
	 * その日に作成されたすべての医療記録情報を取得します。
	 */
	@RequestMapping(value = "/getMedicalRecordByDay", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getMedicalRecordByDay(@RequestParam  String day) throws Exception{
		 DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		 	
		try {
			Date date = sourceFormat.parse(day);
			List<Map<String, Object>> map= medicalRecordService.getMedicalRecordByDay(date);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/*
	 * Delete medical records.
	 * 医療記録を削除します。
	 */
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
	
	/*
	 * Get all the information of all medical records.
	 * すべての医療記録のすべての情報を取得します。
	 */
	@RequestMapping(value = "/getAllMedicalRecord", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllMedicalRecord(@RequestParam  int clientID) {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Set<String> roles = roleService.getRoleByToken("");
			for(String role : roles) {
				if (role.equals(ROLEADMIN)) {
					list = medicalRecordService.getMedicalRecordByClient(clientID);
					
				}
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
//			return new ResponseEntity<> (, HttpStatus.BAD_REQUEST);
			
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
