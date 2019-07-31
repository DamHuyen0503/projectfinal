package com.fpt.projectfinal.daos.subscriber;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
