package com.fpt.projectfinal.daos.contact;

import java.util.List;

import com.fpt.projectfinal.models.Contact;

public interface ContactDao {

	public void addContact(Contact contact);
	
	public List<Contact> getAllContact();
	
	public void updateContact(Contact contact);
}
