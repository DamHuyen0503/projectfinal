package com.fpt.projectfinal.daos.account;

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

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

	@Autowired
	public  SessionFactory session;
	
	@Override
	public void addAccount(Account acc) {
		try {
			this.session.getCurrentSession().save(acc);
		}catch (Exception e) {
			System.out.print(e);
		
		}
		
	
	}
	
	@Override
	public Account getAccountByEmail(String email) {
		CriteriaBuilder builder =  session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root).where(builder.equal(root.get("email"), email));
		List<Account> accounts = session.getCurrentSession().createQuery(query).getResultList();
		if(accounts.size() > 0) {
			return accounts.get(0);
		}
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccount() {
		
		return this.session.getCurrentSession().createQuery("from Account").list();
		
		
	}

	@Override
	public Set<Account> getAccountByRole(Role role) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(role.getAccount())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class);
			query.select(root).where(builder.equal(root.join("roles"), role));
			List<Account> accs = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<>(accs);
		}
		return role.getAccount();
	}

	@Override
	public void updateAccount(Account account) {
		this.session.getCurrentSession().update(account);
		
	}

	@Override
	public Account getAccountByUser(User user) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(user.getAccount())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class);
			query.select(root).where(builder.equal(root.join("user"), user));
			List<Account> accs = session.getCurrentSession().createQuery(query).getResultList();
			return accs.get(0);
		}
		return user.getAccount();
		
	}

}
