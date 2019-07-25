package com.fpt.projectfinal.services.contact;

import java.util.List;

import com.fpt.projectfinal.models.Contact;

public interface ContactServices {

	public String addContact(Contact contact);
	
	public List<Contact> getAllContact();
	
	public String updateContact(Contact contact);
}
