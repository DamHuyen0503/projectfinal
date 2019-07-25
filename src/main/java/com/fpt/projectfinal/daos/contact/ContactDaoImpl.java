package com.fpt.projectfinal.daos.contact;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Contact;
@Repository
@Transactional
public class ContactDaoImpl implements ContactDao{
	@Autowired
	SessionFactory session;
	@Override
	public void addContact(Contact contact) {
		this.session.getCurrentSession().save(contact);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllContact() {
		return this.session.getCurrentSession().createQuery("from Contact").list();
	}

	@Override
	public void updateContact(Contact contact) {
		this.session.getCurrentSession().update(contact);
		
	}

}
