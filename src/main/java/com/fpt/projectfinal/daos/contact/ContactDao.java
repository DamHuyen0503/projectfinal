package com.fpt.projectfinal.daos.contact;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Contact;

public interface ContactDao {

	public void addContact(Contact contact);
	
	public List<Contact> getAllContact(Map<String, Object> payload);
	
	public void updateContact(Contact contact);
	
	public Contact getContactByID(int contactID);
	
	public List<Contact> getContactByStatus(int status);
	
}
