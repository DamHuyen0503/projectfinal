package com.fpt.projectfinal.services.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Client;
@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;
	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addClient(Client client) {
		if (client.getDob() == null) {
			return "DOB null";
		}
		if (client.getGender() <0  && client.getGender() >2) {
			return "gender null";
		}
		if (client.getAddress() == null) {
			return "address null";
		}
		if (client.getPhoneNumber() == null) {
			return "phone null";
		}
		if (client.getFullName() == null) {
			return "client null";
		}
		client.setCreatedDate(new Date());
		clientDao.addClient(client);
		return null;
	}

	@Override
	public String updateClient(Client client) {
		clientDao.updateClient(client);
		return null;
	}

	@Override
	public Client getClientByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
