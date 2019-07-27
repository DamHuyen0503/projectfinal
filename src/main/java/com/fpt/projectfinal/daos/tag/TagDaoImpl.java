package com.fpt.projectfinal.daos.tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;

@Repository
@Transactional
public class TagDaoImpl implements TagDao {

	@Autowired
	SessionFactory session;
	
	@Override
	public List<Tag> getAllTag() {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
		Root<Tag> root = query.from(Tag.class);
		query.select(root);
		List<Tag> tags = session.getCurrentSession().createQuery(query).getResultList();
		return tags;

//		return this.session.getCurrentSession().createQuery("from Tag").list();
	}

	@Override
	public Set<Tag> getTagByTest(Test test) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(test.getTags())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
			Root<Tag> root = query.from(Tag.class);
			query.select(root).where(builder.equal(root.join("post"), test));
			List<Tag> tags = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<Tag>(tags);
		}
		return  test.getTags();
	}

	@Override
	public Set<Tag> getTagByPost(Post post) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(post.getTags())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
			Root<Tag> root = query.from(Tag.class);
			query.select(root).where(builder.equal(root.join("post"), post));
			List<Tag> tags = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<Tag>(tags);
		}
		return  post.getTags();
	}


}
