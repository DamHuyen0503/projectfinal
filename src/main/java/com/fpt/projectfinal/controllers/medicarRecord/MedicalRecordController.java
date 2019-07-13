package com.fpt.projectfinal.controllers.medicarRecord;

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
}
