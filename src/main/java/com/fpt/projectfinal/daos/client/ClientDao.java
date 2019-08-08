package com.fpt.projectfinal.daos.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

public interface ClientDao {
	
	public Set<Client> getAllClient(Map<String, Object> payload);
	
	public  String addClient(Client client);
	
	public String updateClient(Client client);
	
	public Client getClientByID(int id);
	
//	public Client getClientByMedicalRecord(MedicalRecord medicalRecord);
	
	public Set<Client> getClientByMedicalRecord(MedicalRecord medicalRecord);
}
