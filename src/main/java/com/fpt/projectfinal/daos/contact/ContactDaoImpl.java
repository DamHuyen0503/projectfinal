package com.fpt.projectfinal.daos.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Contact;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;
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
	public List<Contact> getAllContact(Map<String, Object> payload) {
		List<Contact> result = new ArrayList<>();
		 
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
		Root<Contact> root = query.from(Contact.class);	
		List<Contact> listContact = this.getContactByStatus((int)payload.get("status"));
	
		String sort = (String) payload.get("sort");
		for(Contact contact : listContact) {
			query =	query.select(root).where(builder.like(root.get("content"), "%" + payload.get("searchString") + "%"));
			query =  ((CriteriaQuery<Contact>) builder).orderBy(builder.asc(root.get(sort)));
//			 builder.equal(root.get("contact"), contact)).orderBy(builder.asc(root.get(sort)));
			
			Query<Contact> q	=  session.getCurrentSession().createQuery(query);
			q.setFirstResult(((int) payload.get("page") - 1) * 3);
			q.setMaxResults(3);
			List<Contact> contacts = q.getResultList();
			if (contacts.size() >0) {
				result.add(contacts.get(0));
			}
			
		}
		
		if(result.size()>0){
			
			return result;
		}
		return null;
	}

	@Override
	public void updateContact(Contact contact) {
		this.session.getCurrentSession().update(contact);
		
	}

	@Override
	public Contact getContactByID(int contactID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
		Root<Contact> root = query.from(Contact.class);
		query.select(root).where(builder.equal(root.get("contactID"), contactID));
		List<Contact> contacts = session.getCurrentSession().createQuery(query).getResultList();
		if (contacts.size() > 0) {

			return contacts.get(0);
		}
		return null;
	}

	@Override
	public List<Contact> getContactByStatus(int status) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
		Root<Contact> root = query.from(Contact.class);
		query.select(root).where(builder.equal(root.get("status"), status));
		List<Contact> contacts = session.getCurrentSession().createQuery(query).getResultList();
		if (contacts.size() > 0) {

			return contacts;
		}
		return null;
	}

}
