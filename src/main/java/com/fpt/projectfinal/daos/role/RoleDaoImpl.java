package com.fpt.projectfinal.daos.role;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
	@Autowired
	public  SessionFactory session;

	

	@SuppressWarnings("unchecked")
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
	public Set<Role> getRoleByName(String roleName) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);
		query.select(root).where(builder.equal(root.get("name"), roleName));
		List<Role> roles = session.getCurrentSession().createQuery(query).getResultList();
		if(roles.size()>0){
			
			return  new HashSet<Role>(roles);
		}
		return null;
	}



	

	@Override
	public void updateRole(Role role) {
		this.session.getCurrentSession().update(role);
		
	}



	@Override
	public Role getRoleByID(int roleID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);
		query.select(root).where(builder.equal(root.get("roleID"), roleID));
		List<Role> roles = session.getCurrentSession().createQuery(query).getResultList();
		if(roles.size()>0){
			
			return  roles.get(0);
		}
		return null;
	}

}
