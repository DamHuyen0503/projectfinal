package com.fpt.projectfinal.daos.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;

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
		return null;
	}

	@Override
	public String updateClient(Client client) {
		this.session.getCurrentSession().update(client);
		return null;
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

}
