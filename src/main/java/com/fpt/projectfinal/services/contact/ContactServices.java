package com.fpt.projectfinal.services.contact;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Contact;

public interface ContactServices {

	public String addContact(Contact contact);
	
	public List<Contact> getAllContact(Map<String, Object> payload);
	
	public String updateContact(Map<String, Object> payload);
}
