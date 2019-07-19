package com.fpt.projectfinal.daos.client;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
import com.fpt.projectfinal.models.Question;

public interface ClientDao {
	
	public List<Client> getAllClient();
	
	public  String addClient(Client client);
	
	public String updateClient(Client client);
	
	public Client getClientByID(int id);
	
//	public Client getClientByMedicalRecord(MedicalRecord medicalRecord);
	
	public Set<Client> getClientByMedicalRecord(MedicalRecord medicalRecord);
}
