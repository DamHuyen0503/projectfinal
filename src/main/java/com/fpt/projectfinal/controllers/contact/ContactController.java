package com.fpt.projectfinal.controllers.contact;

import java.util.List;

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
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Contact;
import com.fpt.projectfinal.services.contact.ContactServices;

@CrossOrigin
@RestController
public class ContactController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired 
	ContactServices contactService;

	@RequestMapping(value = "/addContact",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createCategory(@RequestBody Contact contact,UriComponentsBuilder builder) {
		try {
			contactService.addContact(contact);
	        return new ResponseEntity<>(contact, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@RequestMapping(value = "/getAllContact", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Contact> getContact() {
		List<Contact> list = contactService.getAllContact();
		return list;
	}
	
	@RequestMapping(value = "/updateContact",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Object> updateCategory(@RequestBody Contact contact,UriComponentsBuilder builder) {
		try {
			contactService.updateContact(contact);
	        return new ResponseEntity<>(contact, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
