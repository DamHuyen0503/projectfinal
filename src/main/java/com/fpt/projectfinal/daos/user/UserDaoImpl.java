package com.fpt.projectfinal.daos.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.role.RoleDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory session;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Override
	public User getUserByEmail(String email) {
		
		CriteriaBuilder builder =  session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("email"), email));
		List<User> users = session.getCurrentSession().createQuery(query).getResultList();
		if(users.size() > 0) {
			return users.get(0);
		}
		else return null;
		
	}
	@Override
	public User getUserByID(int userID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("userID"), userID));
		List<User> users = session.getCurrentSession().createQuery(query).getResultList();
		if(users.size()>0){
			
			return users.get(0);
		}
		return null;
	}
	@Override
	public User getUserByTest(Post test ) {
		System.out.println();
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(test.getUser())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("post"), test));
			List<User> users = session.getCurrentSession().createQuery(query).getResultList();
			return users.get(0);
		}
		return test.getUser();
	}
	@Override
	public void updateUser(User user) {
		this.session.getCurrentSession().update(user);
	}
	@SuppressWarnings("unused")
	@Override
	public List<User> getAllUser(Map<String, Object> payload) {
		List<User> result = new ArrayList<>();
		 
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);	
		Role role = roleDao.getRoleByID((int)payload.get("roleID"));
		Set<Account> accounts = accountDao.getAccountByRole(role);
		String sort = (String) payload.get("sort");
		for(Account acc : accounts) {
			query.select(root).where(builder.like(root.get("firstName"), "%" + payload.get("searchString") + "%"), 
					 builder.equal(root.get("account"), acc)).orderBy(builder.asc(root.get(sort)));
		
			
			Query<User> q	=  session.getCurrentSession().createQuery(query);
			q.setFirstResult(((int) payload.get("page") - 1) * 3);
			q.setMaxResults(3);
			List<User> user = q.getResultList();
			if (user.size() >0) {
				result.add(user.get(0));
			}
			
		}
		
		if(result.size()>0){
			
			return result;
		}
		return null;
	}
	@Override
	public User getUserByAccount(Account account) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(account.getUser())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("account"), account));
			List<User> users = session.getCurrentSession().createQuery(query).getResultList();
			return users.get(0);
		}
		return account.getUser();
	}
	@Override
	@SuppressWarnings({ "unused", "unchecked" })
	public Long getNumberOfUser() {
		
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<User> root = query.from(User.class);
		query.select(builder.count(root));
		List<Long> count = session.getCurrentSession().createQuery(query).getResultList();
		return count.get(0);
	}
	
}
