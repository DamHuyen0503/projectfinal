package com.fpt.projectfinal.daos.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.question.QuestionDao;
import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;

@Repository
@Transactional
public class TestDaoImpl implements TestDao {

	@Autowired
	SessionFactory session;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	QuestionDao questionDao;
	
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
		
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Test> query = builder.createQuery(Test.class);
		Root<Test> root = query.from(Test.class);
		query.select(root).where(builder.equal(root.get("postID"), id));
		List<Test> tests = session.getCurrentSession().createQuery(query).getResultList();
		if(tests.size()>0){
			
			return tests.get(0);
		}
		return null;
	}

	@Override
	public List<com.fpt.projectfinal.models.Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
