package com.fpt.projectfinal.daos.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
@Repository
@Transactional
public class PostDaoImpl implements PostDao {

	@Autowired
	SessionFactory session;
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public void addPost(Post post) {
		this.session.getCurrentSession().save(post);
	}

	@Override
	public void updatePost(Post post) {
		this.session.getCurrentSession().update(post);
	}

	@Override
	public Post getPostById(int id) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> query = builder.createQuery(Post.class);
		Root<Post> root = query.from(Post.class);
		query.select(root).where(builder.equal(root.get("postID"), id));
		List<Post> tests = session.getCurrentSession().createQuery(query).getResultList();
		if (tests.size() > 0) {

			return tests.get(0);
		}
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		return this.session.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public Long getCountPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		ProjectionList projectionList = Projections.projectionList();
		Criteria crit = session.getCurrentSession().createCriteria(Post.class);
		crit.add(Restrictions.ne("status", 3));
		crit.add(Restrictions.ne("status", 0));
		if (categoryID != 0) {
			crit.add(Restrictions.eq("categoryID", categoryID));
		}
		crit.add(Restrictions.like("title", searchString, MatchMode.ANYWHERE));
		projectionList.add(Projections.count("postID").as("count"));
		crit.setProjection(projectionList);

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Long> results = crit.list();
		return results.get(0);
	}

	@Override
	public List<Post> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		Category category = categoryDao.getCategoryByID(categoryID);
		Category testCate = categoryDao.getCategoryByID(1);
		ProjectionList projectionList = Projections.projectionList();
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.distinct(true);
		if (categoryID != 0) {
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.like(root.get("title"), "%" + searchString + "%"), cb.notEqual(root.get("category"), testCate),
					cb.equal(root.get("category"), category));
		} else
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.notEqual(root.get("category"), testCate), cb.like(root.get("title"), "%" + searchString + "%"));
		if ("asc".equals(order)) {
			cr.orderBy(cb.asc(root.get(sort)));
		} else {
			cr.orderBy(cb.desc(root.get(sort)));
		}

		Query<Post> query = session.getCurrentSession().createQuery(cr);
		query.setFirstResult((page - 1) * 3);
		query.setMaxResults(3);
		List<Post> results = query.getResultList();
		return results;
	}

	@Override
	public List<Post> filterByTitle(String filter) {
		Criteria crit = session.getCurrentSession().createCriteria(Post.class);
		crit.add(Restrictions.like("title", filter + "%", MatchMode.ANYWHERE));
		List<Post> results = crit.list();
		return results;
	}

	@Override
	public List<Post> getPostByCategory(Category category) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(category.getPost())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Post> query = builder.createQuery(Post.class);
			Root<Post> root = query.from(Post.class);
			query.select(root).where(builder.equal(root.get("category"), category));
			List<Post> posts = session.getCurrentSession().createQuery(query).getResultList();
			return posts;
		}
		return new ArrayList<>(category.getPost());
	}

	@Override
	public List<Post> getAllDraft() {
		List<Post> allDraft = new ArrayList<Post>();

		String sql = "Select e from " + Post.class.getName() + " e " + " where e.status = 0 ";

		allDraft = (List<Post>) session.getCurrentSession().createQuery(sql).list();

		return allDraft;
	}

}
