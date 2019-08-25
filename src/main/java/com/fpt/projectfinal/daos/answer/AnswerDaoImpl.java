package com.fpt.projectfinal.daos.answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Slider;

@Repository
@Transactional
public class AnswerDaoImpl implements AnswerDao {

	@Autowired
	SessionFactory session;
	@Override
	public Set<Answer> getAnswerByQuestion(Question question) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(question.getAnswer())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
			Root<Answer> root = query.from(Answer.class);
			query.select(root).where(builder.equal(root.get("question"), question));
			List<Answer> ans = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<>(ans);
		}
		return question.getAnswer();
	}
	@Override
	public void deleteAnswer(Answer answer) {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("DELETE from Answer a ");
//		stringBuilder.append("WHERE a.question.questionID =: questionID");
//		Query<Answer> query = session.getCurrentSession().createQuery(stringBuilder.toString());
//		
//		query = query.setParameter("questionID", questionID);
//		query.getResultList();
		this.session.getCurrentSession().delete(answer);
	}
		
	
}
