package com.fpt.projectfinal.daos.client;

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

import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {
	@Autowired
	SessionFactory session;

	@Override
	public List<Client> getAllClient() {
		
		return null;
	}

	@Override
	public String addClient(Client client) {
		this.session.getCurrentSession().save(client);
		return "sucessfull";
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

//	@Override
//	public Client getClientByMedicalRecord(MedicalRecord medicalRecord) {
//
//		
//		PersistenceUnitUtil impl = session.getPersistenceUnitUtil();
//		if(!impl.isLoaded(medicalRecord.getClient())) {
//			CriteriaBuilder builder = session.getCurrentSession().getCriteriaBuilder();
//			CriteriaQuery<Client> query = builder.createQuery(Client.class);
//			Root<Client> root = query.from(Client.class);
//			query.select(root).where(builder.equal(root.get("MedicalRecord"), medicalRecord));
//			Client clients = (Client) session.getCurrentSession().createQuery(query).getResultList();
//			return clients;
//		}
//		return (Client) medicalRecord.getClient();
//		
//	}

}
