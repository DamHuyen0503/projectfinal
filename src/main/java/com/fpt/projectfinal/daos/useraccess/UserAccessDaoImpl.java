package com.fpt.projectfinal.daos.useraccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.models.Client;
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
	public List<UserAccess> getUserAccessByUser(User user, int clientID) {
		
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
		
		
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("SELECT U FROM UserAccess U ");
//		stringBuilder.append("WHERE U.user =: user ");
//		stringBuilder.append("and U.medicalRecord.client.clientID =: clientID");
//		
//		Query<UserAccess> query = session.getCurrentSession().createQuery(stringBuilder.toString());
//		query = query.setParameter("user", user);
//		query = query.setParameter("clientID", clientID);
////		List<Client> liClient = query.getResultList();
////		mapResult.put("count", liClient.size());
////		query.setFirstResult((page - 1) * 7);
////		query.setMaxResults(7);
////		mapResult.put("listClient", query.getResultList());
//		return query.getResultList();
//		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
//		List<UserAccess> userAccess = new ArrayList<>();
//		if(!impl.isLoaded(user.getUserAccess())) {
//			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
//			CriteriaQuery<UserAccess> query = builder.createQuery(UserAccess.class);
//			Root<UserAccess> root = query.from(UserAccess.class);
//			query.select(root).where(builder.equal(root.get("user"), user), 
//									 builder.equal(root.join("medicalRecord"), "clientID"),
//									 builder.equal(root.get("client"), clientID));
//			userAccess =  session.getCurrentSession().createQuery(query).getResultList();
//			return userAccess;
//		}
//		else {
//			userAccess.add((UserAccess) user.getUserAccess());
//			return   userAccess;
//		}
		
	}


	

}
