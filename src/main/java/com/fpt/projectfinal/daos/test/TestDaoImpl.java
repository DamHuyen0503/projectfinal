package com.fpt.projectfinal.daos.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.question.QuestionDao;
import com.fpt.projectfinal.models.Post;
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
	public com.fpt.projectfinal.models.Test getTestById(int postID) {
		
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Test> query = builder.createQuery(Test.class);
		Root<Test> root = query.from(Test.class);
		query.select(root).where(builder.equal(root.get("postID"), postID));
		List<Test> tests = session.getCurrentSession().createQuery(query).getResultList();
		if(tests.size()>0){
			
			return tests.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllTest() {
		return this.session.getCurrentSession().createQuery("from Post").list();
	}

	

}
