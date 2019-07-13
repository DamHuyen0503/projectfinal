package com.fpt.projectfinal.daos.client;

import java.util.List;
import com.fpt.projectfinal.models.Client;

public interface ClientDao {
	
	public List<Client> getAllClient();
	
	public  String addClient(Client client);
	
	public String updateClient(Client client);
	
	public Client getClientByID(int id);
}
