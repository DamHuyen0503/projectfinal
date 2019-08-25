package com.fpt.projectfinal.daos.contact;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.fpt.projectfinal.models.Post;
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
	public Map<String, Object> getAllContact(String sort, String order, int page, int status, String searchString) {
		Map<String, Object> result = new HashMap<String, Object>();
		 
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Contact> cr = cb.createQuery(Contact.class);
		Root<Contact> root = cr.from(Contact.class);
		cr.distinct(true);
		if (status == 2) {
			cr.where(cb.like(root.get("content"), "%" + searchString + "%"));		
		}
		else {
			cr.where(cb.equal(root.get("status"), status), 
					cb.like(root.get("content"), "%" + searchString + "%"));	
		}
		if ("asc".equals(order)) {
			cr.orderBy(cb.asc(root.get(sort)));
		} else {
			cr.orderBy(cb.desc(root.get(sort)));
		}
			
			Query<Contact> q	=  session.getCurrentSession().createQuery(cr);
			List<Contact> c = q.getResultList();
			
			q.setFirstResult((page - 1) * 3);
			q.setMaxResults(3);
			List<Contact> resultContact = q.getResultList();
			result.put("count", c.size());
			result.put("listContact", resultContact);
			return result;
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

	@Override
	public List<Contact> getAll() {
		return this.session.getCurrentSession().createQuery("from Contact").list();
	}

}
