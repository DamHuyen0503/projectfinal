package com.fpt.projectfinal.daos.userTest;

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

}
