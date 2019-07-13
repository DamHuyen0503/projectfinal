package com.fpt.projectfinal.services.client;

import java.util.List;

import com.fpt.projectfinal.models.Client;

public interface ClientService {
	
	public List<Client> getAllClient();
	
	public String addClient(Client client);
	
	public String updateClient(Client client);
	
	public Client getClientByID(int id);
}
