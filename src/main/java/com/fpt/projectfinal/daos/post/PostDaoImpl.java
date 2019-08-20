package com.fpt.projectfinal.daos.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
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
import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.User;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

	@Autowired
	SessionFactory session;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	TagDao tagDao;

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
		List<Post> posts = session.getCurrentSession().createQuery(query).getResultList();
		if (posts.size() > 0) {

			return posts.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPost() {
		return this.session.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public Long getCountPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<Post> root = cr.from(Post.class);
		cr.select(cb.count(root));
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		cr.distinct(true);
		if (categoryID != 0) {
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.like(root.get("title"), "%" + searchString + "%"), cb.notEqual(root.get("category"), 1),
					cb.equal(root.get("category"), categoryID));
		} else
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.notEqual(root.get("category"), 0), cb.like(root.get("title"), "%" + searchString + "%"));
		return session.getCurrentSession().createQuery(cr).getSingleResult();
	}

	@Override
	public List<Post> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.distinct(true);
		if (categoryID != 0) {
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.like(root.get("title"), "%" + searchString + "%"), 
					cb.equal(root.get("category"), categoryID));
		} else
			cr.where(cb.notEqual(root.get("status"), 3), cb.notEqual(root.get("status"), 0),
					cb.like(root.get("title"), "%" + searchString + "%"),
					cb.notEqual(root.get("category"), 1));
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
	
	@Override
	public Long getCountPostsByTagID(Integer tagID, Integer page) {
		
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<Post> root = cr.from(Post.class);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);

		
		Join<Post, Tag> join = root.join("tags", JoinType.INNER);
		cr.select(cb.count(root)).where(cb.equal(join.get("tagID"), tagID));
		
		return session.getCurrentSession().createQuery(cr).getSingleResult();
	}

	@Override
	public List<Post> getPostsByTagID(Integer tagID, Integer page) {
		
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		Join<Post, Tag> join = root.join("tags", JoinType.INNER);
		cr.select(root).where(cb.equal(join.get("tagID"), tagID));
		
		Query<Post> query = session.getCurrentSession().createQuery(cr);
		query.setFirstResult((page-1) * 3);
		query.setMaxResults(3);

		
		List<Post> tags = query.getResultList();

		return tags;
	}

	@Override
	public List<Post> getPostNew() {
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.where(cb.equal(root.get("status"), 1),cb.notEqual(root.get("category"), 1));
		cr.orderBy(cb.desc(root.get("createdDate")));
		
		Query<Post> query = session.getCurrentSession().createQuery(cr);
		query.setMaxResults(5);
		List<Post> posts = query.getResultList();
		
		return posts;
	}

	@Override
	public Long getNumberOfPost() {
		Category cate = categoryDao.getCategoryByID(1);
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Post> root = query.from(Post.class);
		query.select(builder.count(root)).where(builder.notEqual(root.get("category"), cate));
		List<Long> count = session.getCurrentSession().createQuery(query).getResultList();
		return count.get(0);
	}

	@Override
	public Map<String, Object> getPostsByString(String stringSearch, int page) {
		Map<String , Object> result = new HashMap<String, Object>();
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.where(cb.notEqual(root.get("category"), 1),
				cb.like(root.get("title"), "%" + stringSearch + "%"));
		
		Query<Post> query = session.getCurrentSession().createQuery(cr);
		result.put("count", query.getResultList().size());
		query.setFirstResult((page-1) * 5);
		query.setMaxResults(5);

		
		List<Post> post = query.getResultList();
		result.put("Post", post);
		return result;
	}

	@Override
	public Map<String, Object> getPostsByCategory(int categoryID, int page) {
		Map<String , Object> result = new HashMap<String, Object>();
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.where(cb.equal(root.get("category"), categoryID ));
		
		Query<Post> query = session.getCurrentSession().createQuery(cr);
		result.put("count", query.getResultList().size());
		query.setFirstResult((page-1) * 5);
		query.setMaxResults(5);

		
		List<Post> post = query.getResultList();
		result.put("Post", post);
		return result;
	}

	@Override
	public Map<String, Object> getPostsByAuthor(int userID, int page) {
		Map<String , Object> result = new HashMap<String, Object>();
		CriteriaBuilder cb = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);
		Root<Post> root = cr.from(Post.class);
		cr.where(cb.equal(root.get("user"), userID ));
		
		Query<Post> query = session.getCurrentSession().createQuery(cr);
		result.put("count", query.getResultList().size());
		query.setFirstResult((page-1) * 5);
		query.setMaxResults(5);

		
		List<Post> post = query.getResultList();
		result.put("Post", post);
		return result;
	}



}
