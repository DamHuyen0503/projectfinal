package com.fpt.projectfinal.daos.account;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.User;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

	@Autowired
	public  SessionFactory session;
	
	@Override
	public int addAccount(Account acc) {
		try {
			this.session.getCurrentSession().save(acc);
		}catch (Exception e) {
			System.out.print(e);
			return 0;
		}
		
		return 1;
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

}
