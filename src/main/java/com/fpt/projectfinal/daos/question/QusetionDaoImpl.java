package com.fpt.projectfinal.daos.question;

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

import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Test;
@Repository
@Transactional
public class QusetionDaoImpl implements QuestionDao {

	@Autowired
	SessionFactory session;
	
	@Override
	public List<Question> getQuestionByTest(Test test) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(test.getQuestion())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Question> query = builder.createQuery(Question.class);
			Root<Question> root = query.from(Question.class);
			query.select(root).where(builder.equal(root.get("test"), test));
			List<Question> quests = session.getCurrentSession().createQuery(query).getResultList();
			return quests;
		}
		return new ArrayList<>(test.getQuestion());
		
	}

}
