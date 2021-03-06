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
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/*
	 * Get all notes of a medical record.
	 * 医療記録のすべてのメモを取得します。
	 */
	@RequestMapping(value = "/getNoteProcessByMedicalRecordID", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getNoteProcessByMedicalRecordID(@RequestParam  int medicalRecordID){
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
	
	/*
	 * Create new notes of a medical record.
	 * 医療記録の新しいメモを作成します。
	 */
	@RequestMapping(value = "/addNoteProcess", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> addNoteProcess(@RequestBody  Map<String, Object> payload) {

		try {

		
			return new ResponseEntity<>(noteProcessService.addNoteProcess(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Update more notes for medical records.
	 * 医療記録のメモを更新します。
	 */
	@RequestMapping(value = "/updateNoteProcess", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> updateNoteProcess(@RequestBody  Map<String, Object> payload) {

		try {

		
			return new ResponseEntity<>(noteProcessService.updateNoteProcess(payload), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Get all user notes.
	 * すべてのユーザーメモを取得します。
	 */
	@RequestMapping(value = "/getNoteProcessByUserSendRequest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object>  getNoteProcessByUserSendRequest(){
		try {
			
	        return new ResponseEntity<>(noteProcessService.getNoteProcessByUserSendRequest(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
