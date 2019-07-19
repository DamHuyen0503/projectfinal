package com.fpt.projectfinal.daos.role;

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
import com.fpt.projectfinal.models.Role;
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
	@Autowired
	public  SessionFactory session;

	

	@Override
	public List<Role> getAllRole() {
		return this.session.getCurrentSession().createQuery("from Role").list();
	
	}



	@Override
	public Set<Role> getRoleByAcc(Account acc) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(acc.getRoles())) {
			CriteriaBuilder builder =  session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Role> query = builder.createQuery(Role.class);
			Root<Role> root = query.from(Role.class);
			query.select(root).where(builder.equal(root.join("account"), acc));
			List<Role> role = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<>(role);
		}
		
		else return acc.getRoles();
	}



	@Override
	public void updateRole() {
		
		
	}

}
