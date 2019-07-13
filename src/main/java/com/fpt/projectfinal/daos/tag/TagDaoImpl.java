package com.fpt.projectfinal.daos.tag;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Tag;

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
		
	}

}
