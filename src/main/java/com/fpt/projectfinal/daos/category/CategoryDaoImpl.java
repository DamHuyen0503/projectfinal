package com.fpt.projectfinal.daos.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Subscriber;
import com.fpt.projectfinal.models.User;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SessionFactory session;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory() {
		return this.session.getCurrentSession().createQuery("from Category").list();

	}

	@Override
	public void addCategory(Category category) {

		this.session.getCurrentSession().save(category);

	}

	@Override
	public void updateCategory(Category category) {
		
		this.session.getCurrentSession().update(category);
	}

	@Override
	public Category getCategoryByID(int id) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		query.select(root).where(builder.equal(root.get("categoryID"), id));
		List<Category> categorys = session.getCurrentSession().createQuery(query).getResultList();
		if (categorys.size() > 0) {
			return categorys.get(0);
		} else
			return null; 
	}

	@Override
	public List<Category> CountCategory() {
		
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		query.select(root);
		List<Category> categories = session.getCurrentSession().createQuery(query).getResultList();
		for (Category c:categories) {
			CriteriaQuery<Post> queryPost = builder.createQuery(Post.class);
			Root<Post> rootPost = queryPost.from(Post.class);
			queryPost.select(rootPost).where(builder.equal(rootPost.get("category"), c), 
											 builder.equal(rootPost.get("status"), 1));
			Set<Post> posts = new HashSet(session.getCurrentSession().createQuery(queryPost).getResultList());
			c.setPost(posts);
			System.out.println(posts.size());
		}
		return categories;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Category> CountPostByCategory() {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Category> query = builder.createQuery(Category.class);
		Root<Category> root = query.from(Category.class);
		query.select(root);
		List<Category> categories = session.getCurrentSession().createQuery(query).getResultList();
		for (Category c:categories) {
			CriteriaQuery<Post> queryPost = builder.createQuery(Post.class);
			Root<Post> rootPost = queryPost.from(Post.class);
			queryPost.select(rootPost).where(builder.equal(rootPost.get("category"), c),
											builder.equal(root.get("status"), 1)	);
			@SuppressWarnings("unchecked")
			Set<Post> posts = new HashSet(session.getCurrentSession().createQuery(queryPost).getResultList());
			c.setPost(posts);
			System.out.println(posts.size());
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryBySubscriber(int subscriberID) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT C FROM Category C JOIN C.subscriber s ");
		stringBuilder.append("WHERE s.subscriberID = :subscriberID");
		Query<Category> query = session.getCurrentSession().createQuery(stringBuilder.toString());
		query = query.setParameter("subscriberID", subscriberID);
		return query.getResultList();
	} 

}
