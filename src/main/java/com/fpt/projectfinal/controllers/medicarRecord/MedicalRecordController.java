package com.fpt.projectfinal.controllers.medicarRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.services.medicalrecord.MedicalRecordService;

@RestController
@CrossOrigin
public class MedicalRecordController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	MedicalRecordService medicalRecordService;

	@RequestMapping(value = "/addMedicalRecord", method = RequestMethod.POST,

			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> createMedicalRecord(@RequestBody Map<String, Object> payload, UriComponentsBuilder builder) {

		try {
			medicalRecordService.addMedicalRecord(payload);
			return new ResponseEntity<>(payload, HttpStatus.CREATED);
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
	public ResponseEntity<Object>  getMedicalRecordByID(@RequestParam  String medicalRecordID){
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
	
//		System.out.println("check");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//		Date date = sdf.parse("201"
//				+ "9/07/22 00:00:00"); 
//		
//		System.out.println("date: "+date);
//		 String sDate1="2019/07/22 00:00:00";  
//		    Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
//		    System.out.println(sDate1+"\t"+ date);  
		
		 DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
	        String dateInString = "13/2/2019";

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
}
