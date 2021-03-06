package com.fpt.projectfinal.daos.userTest;

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
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;
import com.fpt.projectfinal.models.UserTest;
@Repository
@Transactional
public class UserTestDaoImpl implements UserTestDao{
	@Autowired
	SessionFactory session;
	
	@Override
	public void addUserTest(UserTest userTest) {
		this.session.getCurrentSession().save(userTest);
	
	}

	@Override
	public int countUserByTest(Post test) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserTest> query = builder.createQuery(UserTest.class);
		Root<UserTest> root = query.from(UserTest.class);
		query.select(root).where(builder.equal(root.get("test"), test));
		List<UserTest> users = session.getCurrentSession().createQuery(query).getResultList();
		if(users.size()>0){
			
			return users.size();
		}
		return 0;
	}

	@Override
	public Long getNumberOfUserTest() {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<UserTest> root = query.from(UserTest.class);
		query.select(builder.count(root));
		List<Long> count = session.getCurrentSession().createQuery(query).getResultList();
		return count.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTest> getUserTestByUser(User user) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(user.getUserTest())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<UserTest> query = builder.createQuery(UserTest.class);
			Root<UserTest> root = query.from(UserTest.class);
			query.select(root).where(builder.equal(root.get("user"),user));
			List<UserTest> userTests = session.getCurrentSession().createQuery(query).getResultList();
			return userTests;
		}
		return new ArrayList<>(user.getUserTest());
	}

}
