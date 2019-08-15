package com.fpt.projectfinal.services.contact;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.contact.ContactDao;
import com.fpt.projectfinal.models.Contact;
@Service
public class ContactServiceImpl implements ContactServices{

	@Autowired
	ContactDao contactDao;
	@Override
	public String addContact(Map<String, Object> payload) {
		Contact contact = new Contact();
		
		try {
			if (payload.get("name") == null) {
				return "name null";
			}
		
			if (payload.get("email") == null) {
				return "email null";
			}
			contact.setDate(new Date());
			contact.setContent((String) payload.get("content"));
			contact.setEmail((String) payload.get("email"));
			contact.setName((String) payload.get("name"));
			contact.setStatus((int) payload.get("status"));
			contactDao.addContact(contact);
			return "successful";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	@Override
	public List<Contact> getAllContact(Map<String, Object> payload) {
		return contactDao.getAllContact(payload);
	}

	@Override
	public String updateContact(Map<String, Object> payload) {
		try {
			if ((int)payload.get("contactID") <=0) {
				return "contactID invalid";
			}
			
			Contact contact = contactDao.getContactByID((int) payload.get("contactID"));
			if (contact == null ) {
				return "contact not found";
			}
			contact.setStatus((int) payload.get("status"));
			contactDao.updateContact(contact);
			return "successful";
		}catch (Exception e) {
			return e.getMessage();
		}
		
		
	}

	
}
