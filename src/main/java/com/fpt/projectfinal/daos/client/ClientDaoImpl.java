package com.fpt.projectfinal.daos.client;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

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
	public Map<String , Object> getAllClient(String sort, String order, int page, String searchString, int status, int expert) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String , Object> mapResult = new HashMap<>();
		stringBuilder.append("SELECT distinct U.medicalRecord.client FROM UserAccess U ");
		stringBuilder.append("WHERE U.user.userID = :userID  and fullName like :searchString ");
		stringBuilder.append("and U.medicalRecord.status = :status ");
		stringBuilder.append("order by U.medicalRecord.client.").append(sort).append(" ").append(order);
		Query<Client> query = session.getCurrentSession().createQuery(stringBuilder.toString());
		
		query = query.setParameter("userID", expert);
		query = query.setParameter("status", status);
		query = query.setParameter("searchString", "%" +searchString+ "%");
		List<Client> liClient = query.getResultList();
		mapResult.put("count", liClient.size());
		if (status == 0) {
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(5);
			mapResult.put("listClient", query.getResultList());
			return mapResult;
		}
		if (status == 1) {
			List<Client> result = new ArrayList<>();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("SELECT distinct U.medicalRecord.client FROM UserAccess U ");
			strBuilder.append("WHERE U.user.userID = :userID  and fullName like :searchString ");
			strBuilder.append("and U.medicalRecord.status = 0 ");
			strBuilder.append("order by U.medicalRecord.client.").append(sort).append(" ").append(order);
			Query<Client> q = session.getCurrentSession().createQuery(strBuilder.toString());
			q = q.setParameter("userID", expert);
			q = q.setParameter("searchString", "%" +searchString+ "%");
			List<Client> listClient = q.getResultList();
			Map<Integer, String> mapClient = new HashMap<>();
			for (Client c : listClient) {
				mapClient.put(c.getClientID(), c.getFullName());
			}
			for (Client c : liClient) {
				if (!mapClient.containsKey(c.getClientID())) {
					result.add(c);
				}
			}
			
			mapResult.put("listClient", result);
			return mapResult;
//			return result;
		}
		return new  HashMap<>();		
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
			stringBuilder.append("SELECT distinct U.medicalRecord.client FROM UserAccess U ");
			stringBuilder.append("WHERE U.user.account.email = :username and fullName like :searchString ");
			stringBuilder.append("and U.medicalRecord.status = :status ");
			stringBuilder.append("order by U.medicalRecord.client.").append(sort).append(" ").append(order);
			
			Query<Client> query = session.getCurrentSession().createQuery(stringBuilder.toString());
			query = query.setParameter("username", username);
			query = query.setParameter("status", status);
			query = query.setParameter("searchString", "%" +searchString+ "%");
			
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(5);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Map<String, Object> getAll(String sort, String order, int page, String searchString) {
		StringBuilder stringBuilder = new StringBuilder();
			Map<String, Object> mapResult = new HashMap<>();
			stringBuilder.append("SELECT C FROM Client C ");
			stringBuilder.append("WHERE fullName like :searchString ");
			stringBuilder.append("order by ").append(sort).append(" ").append(order);
			Query<Client> query = session.getCurrentSession().createQuery(stringBuilder.toString());
			query = query.setParameter("searchString", "%" +searchString+ "%");
			List<Client> l = query.getResultList();
			mapResult.put("count", l.size());
			query.setFirstResult((page - 1) * 5);
			query.setMaxResults(5);
			mapResult.put("listClient", query.getResultList());
			
			return mapResult ;
		
	}


}
