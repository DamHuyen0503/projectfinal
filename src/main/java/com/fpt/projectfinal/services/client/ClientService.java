package com.fpt.projectfinal.services.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Client;

public interface ClientService {
	
	public List<Map<String, Object>> getAllClient(Map<String, Object> payload);
	
	public String addClient(Client client);
	
	public String updateClient(Client client);
	
	public Map<String, Object> getClientByID(int id);
}
