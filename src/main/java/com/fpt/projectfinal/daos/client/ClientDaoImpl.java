package com.fpt.projectfinal.daos.client;

import java.util.ArrayList;
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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.useraccess.UserAccessDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserAccess;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {
	@Autowired
	SessionFactory session;

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserAccessDao userAccessDao;
	
	@Autowired
	MedicalRecordDao medicalRecordDao; 
	@Override
	public Set<Client> getAllClient(Map<String, Object> payload) {
		Set<Client> result = new HashSet<>();
		 
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Client> query = builder.createQuery(Client.class);
		Root<Client> root = query.from(Client.class);	
		User user = userDao.getUserByID((int) payload.get("expert"));
		List<UserAccess> listUserAccess = userAccessDao.getUserAccessByUser(user);
		for (UserAccess userAccess : listUserAccess) {
			List<MedicalRecord> listMedicalReocord = medicalRecordDao.getMedicalRecordByUserAccess(userAccess);
			for(MedicalRecord medical : listMedicalReocord) {
				result.add(medical.getClient());
			}
		}
		
		String sort = (String) payload.get("sort");
		for(Client client : result) {
			query.select(root).where(builder.like(root.get("fullName"), "%" + payload.get("searchString") + "%"), 
					 builder.equal(root.get("client"), client)).orderBy(builder.asc(root.get(sort)));
		
			
			Query<Client> q	=  session.getCurrentSession().createQuery(query);
			q.setFirstResult(((int) payload.get("page") - 1) * 3);
			q.setMaxResults(3);
			List<Client> client1 = q.getResultList();
			if (client1.size() >0) {
				result.add(client1.get(0));
			}
			
		}
		
		if(result.size()>0){
			
			return result;
		}
		return null;
	}

	@Override
	public void addClient(Client client) {
		this.session.getCurrentSession().save(client);
		
	}

	@Override
	public String updateClient(Client client) {
		this.session.getCurrentSession().update(client);
		return "sucessfull";
	}

	@Override
	public Client getClientByID(int id) {
		CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Client> query = builder.createQuery(Client.class);
		Root<Client> root = query.from(Client.class);
		query.select(root).where(builder.equal(root.get("clientID"), id));
		List<Client> clients = session.getCurrentSession().createQuery(query).getResultList();
		if (clients.size() > 0) {
			return clients.get(0);
		} else
			
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Client> getClientByMedicalRecord(MedicalRecord medicalRecord) {
		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
		if(!impl.isLoaded(medicalRecord.getClient())) {
			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<Client> query = builder.createQuery(Client.class);
			Root<Client> root = query.from(Client.class);
			query.select(root).where(builder.equal(root.get("medicalRecord"), medicalRecord));
			List<Client> cls = session.getCurrentSession().createQuery(query).getResultList();
			return new HashSet<>(cls);
		}
		return (Set<Client>) medicalRecord.getClient();
	}


}
