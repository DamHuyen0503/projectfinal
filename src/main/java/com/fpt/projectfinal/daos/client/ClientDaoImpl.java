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
import com.fpt.projectfinal.models.Subscriber;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient(String sort, String order, int page, String searchString, int status, int expert) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT U.medicalRecord.client FROM UserAccess U ");
			stringBuilder.append("WHERE U.user.userID = :userID  and fullName like :searchString ");
			stringBuilder.append("and U.medicalRecord.status = :status ");
			stringBuilder.append("order by U.medicalRecord.client.").append(sort).append(" ").append(order);
			
			Query<Client> query = session.getCurrentSession().createQuery(stringBuilder.toString());
			query = query.setParameter("userID", expert);
			query = query.setParameter("status", status);
			query = query.setParameter("searchString", "%" +searchString+ "%");
			
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(5);
			List<Client> l = query.getResultList();
			for (Client c : l) {
				System.out.println("clientDao: "+c.getClientID());
			}
//			return new ArrayList<>(new HashSet(query.getResultList()));
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	
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

	@Override
	public List<ClientDao> getClientByExpert(String sort, String order, int page, String searchString, int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClient(String sort, String order, int page, String searchString, int status, String username) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT U.medicalRecord.client FROM UserAccess U ");
			stringBuilder.append("WHERE U.user.account.email = :username and fullName like :searchString ");
			stringBuilder.append("and U.medicalRecord.status = :status ");
			stringBuilder.append("order by U.medicalRecord.client.").append(sort).append(" ").append(order);
			
			Query<Client> query = session.getCurrentSession().createQuery(stringBuilder.toString());
			query = query.setParameter("username", username);
			query = query.setParameter("status", status);
			query = query.setParameter("searchString", "%" +searchString+ "%");
			
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(5);
			return new ArrayList<>(new HashSet(query.getResultList()));
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}


}
