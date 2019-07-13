package com.fpt.projectfinal.daos.test;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TestDaoImpl implements testDao {

	@Autowired
	SessionFactory session;
	
	@Override
	public void addTest(com.fpt.projectfinal.models.Test test) {
		this.session.getCurrentSession().save(test);
	}

	@Override
	public void updateTest(com.fpt.projectfinal.models.Test test) {
		this.session.getCurrentSession().update(test);
	}

	@Override
	public com.fpt.projectfinal.models.Test getTestById(int id) {
		
		return null;
	}

	@Override
	public List<com.fpt.projectfinal.models.Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
