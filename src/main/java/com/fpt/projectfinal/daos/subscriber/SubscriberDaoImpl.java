package com.fpt.projectfinal.daos.subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Subscriber;
import com.fpt.projectfinal.models.User;
@Repository
@Transactional
public class SubscriberDaoImpl implements SubscriberDao {
	@Autowired
	SessionFactory session;
	
	@Override
	public void addSubscriber(Subscriber subscriber) {
		this.session.getCurrentSession().save(subscriber);
		
	}

	@Override
	public Long getNumberOfSubscriber() {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Subscriber> root = query.from(Subscriber.class);
		query.select(builder.count(root));
		List<Long> count = session.getCurrentSession().createQuery(query).getResultList();
		return count.get(0);
	}

	@Override
	public List<Subscriber> getAllSubscriber() {
		return this.session.getCurrentSession().createQuery("from Subscriber").list();
	}

	@Override
	public void updateSubscriber(Subscriber subscriber) {
		this.session.getCurrentSession().update(subscriber);
	}

	@Override
	public Subscriber getSubscriberByID(int subscriberID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Subscriber> query = builder.createQuery(Subscriber.class);
		Root<Subscriber> root = query.from(Subscriber.class);
		query.select(root).where(builder.equal(root.get("subscriberID"), subscriberID));
		List<Subscriber> subscribers = session.getCurrentSession().createQuery(query).getResultList();
		if (subscribers.size() > 0) {

			return subscribers.get(0);
		}
		return null;
	}

	@Override
	public List<Subscriber> listSubscriberByCategory(int categoryID) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT S FROM Subscriber S ");
			stringBuilder.append("JOIN S.categorys SC ");
			stringBuilder.append("WHERE SC.categoryID = :categoryId ");
			
			Query<Subscriber> query = session.getCurrentSession().createQuery(stringBuilder.toString());
			query = query.setParameter("categoryId", categoryID);
			
//		    subs = session.getCurrentSession().createQuery(stringBuilder.toString()).setParameter("categoryId", categoryID).getResultList();
			return query.getResultList();
	}

}
