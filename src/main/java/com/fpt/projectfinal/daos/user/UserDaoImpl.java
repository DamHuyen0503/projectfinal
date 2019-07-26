package com.fpt.projectfinal.daos.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory session;
	@Override
	public User getUserByEmail(String email) {
		
		CriteriaBuilder builder =  session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("email"), email));
		List<User> users = session.getCurrentSession().createQuery(query).getResultList();
		if(users.size() > 0) {
			return users.get(0);
		}
		else return null;
		
	}
	@Override
	public User getUserByID(int userID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("userID"), userID));
		List<User> users = session.getCurrentSession().createQuery(query).getResultList();
		if(users.size()>0){
			
			return users.get(0);
		}
		return null;
	}
	@Override
	public User getUserByTest(Post test ) {
		System.out.println();
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(test.getUser())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("post"), test));
			List<User> users = session.getCurrentSession().createQuery(query).getResultList();
			return users.get(0);
		}
		return test.getUser();
	}

}
