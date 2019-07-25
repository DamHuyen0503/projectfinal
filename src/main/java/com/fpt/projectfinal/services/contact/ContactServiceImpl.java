package com.fpt.projectfinal.services.contact;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.contact.ContactDao;
import com.fpt.projectfinal.models.Contact;
@Service
public class ContactServiceImpl implements ContactServices{

	@Autowired
	ContactDao contactDao;
	@Override
	public String addContact(Contact contact) {
		if (contact.getName() == null) {
			return "name null";
		}
		if (contact.getEmail() == null) {
			return "email null";
		}
		contact.setDate(new Date());
		contactDao.addContact(contact);
		return "successful";
	}

	@Override
	public List<Contact> getAllContact() {
		return contactDao.getAllContact();
	}

	@Override
	public String updateContact(Contact contact) {
		if (contact.getContactID() == 0) {
			return "contactID null";
		}
		contactDao.updateContact(contact);
		return "successful";
		
	}

	
}
