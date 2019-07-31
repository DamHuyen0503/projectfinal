package com.fpt.projectfinal.daos.useraccess;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.NoteProcess;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;

@Repository
@Transactional
public class UserAccessDaoImpl implements UserAccessDao{

	@Autowired
	SessionFactory session;
	@Override
	public void addUserAccess(UserAccess userAccess) {
		this.session.getCurrentSession().save(userAccess);		
	}
	
	
	@Override
	
	
	public void updateUserAccess(UserAccess userAccess) {
		this.session.getCurrentSession().update(userAccess);		
		
	}
	
	
	@Override
	public UserAccess getUserAccessByID(int userAccessID) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserAccess> query = builder.createQuery(UserAccess.class);
		Root<UserAccess> root = query.from(UserAccess.class);
		query.select(root).where(builder.equal(root.get("userAccessID"), userAccessID));
		List<UserAccess> userAccess = session.getCurrentSession().createQuery(query).getResultList();
		if (userAccess.size() > 0) {

			return userAccess.get(0);
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccess> getUserAccessByMedicalRecord(MedicalRecord medicalRecord) {
		
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(medicalRecord.getUserAccess())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<UserAccess> query = builder.createQuery(UserAccess.class);
			Root<UserAccess> root = query.from(UserAccess.class);
			query.select(root).where(builder.equal(root.get("medicalRecord"), medicalRecord));
			List<UserAccess> userAccess =  session.getCurrentSession().createQuery(query).getResultList();
			return userAccess;
		}
		return   (List<UserAccess>) medicalRecord.getUserAccess();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccess> getUserAccessByUser(User user) {

		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(user.getUserAccess())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<UserAccess> query = builder.createQuery(UserAccess.class);
			Root<UserAccess> root = query.from(UserAccess.class);
			query.select(root).where(builder.equal(root.get("user"), user));
			List<UserAccess> userAccess =  session.getCurrentSession().createQuery(query).getResultList();
			return userAccess;
		}
		return   (List<UserAccess>) user.getUserAccess();
	}


	

}
