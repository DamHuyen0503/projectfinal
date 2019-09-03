package com.fpt.projectfinal.daos.result;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Test;

@Repository
@Transactional
public class ResultDaoImpl implements ResultDao {

	@Autowired
	SessionFactory session;
	
	@Override
	public Set<Result> getResultByTest(Test test) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(test.getResult())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Result> query = builder.createQuery(Result.class);
			Root<Result> root = query.from(Result.class);
			query.select(root).where(builder.equal(root.get("test"), test));
			List<Result> result = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<>(result);
		}
		return test.getResult();
	}

	@Override
	public void deleteResult(Result result) {
		this.session.getCurrentSession().delete(result);
		
	}

}
