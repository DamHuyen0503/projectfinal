package com.fpt.projectfinal.controllers.contact;

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
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Contact;
import com.fpt.projectfinal.services.contact.ContactServices;

@CrossOrigin
@RestController
public class ContactController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	ContactServices contactService;

	
	/*
	 * Create new contact. 
	 * 新しい連絡先を作成します。
	 */
	@RequestMapping(value = "/addContact",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createContact(@RequestBody Map<String, Object> payload) {
		try {
			
	        return new ResponseEntity<>(contactService.addContact(payload), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	/*
	 * Get all information contact. 
	 * すべての情報の連絡先を取得します。
	 */
	@RequestMapping(value = "/getAllContact", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getContact(@RequestParam String sort, String order, int page, int status, String searchString) {
		try {
			
	        return new ResponseEntity<>(contactService.getAllContact(sort, order, page, status, searchString), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	
	
	/*
	 * Change the status of the contact.
	 * 連絡先のステータスを変更します。
	 */
	@RequestMapping(value = "/changeStatusContact",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Object> updateCategory(@RequestBody Map<String, Object> payload) {
		try {
			
	        return new ResponseEntity<>(contactService.updateContact(payload), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
