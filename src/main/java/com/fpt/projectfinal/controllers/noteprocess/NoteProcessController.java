package com.fpt.projectfinal.controllers.noteprocess;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.services.noteprocess.NoteProcessServices;
@RestController
@CrossOrigin
public class NoteProcessController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	NoteProcessServices noteProcessService;
	
	
	@RequestMapping(value = "/getNoteProcessByMedicalRecordID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getMedicalRecordByID(@RequestParam  int medicalRecordID){
		try {
			Map<String, Object> map= noteProcessService.getNoteProcessByMedicalRecordID(medicalRecordID);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
