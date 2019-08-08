package com.fpt.projectfinal.services.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.client.ClientDao;
import com.fpt.projectfinal.daos.medicalrecord.MedicalRecordDao;
import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;
@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired 
	MedicalRecordDao medicalRecordDao;
	@Override
	public List<Map<String, Object>> getAllClient(Map<String, Object> payload) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Set<Client> setClient = clientDao.getAllClient(payload);
		for(Client client : setClient) {
			Map<String, Object> mapClient = new HashMap<>();
			mapClient.put("clientID", client.getClientID());
			mapClient.put("gender", client.getGender());
			mapClient.put("dob", client.getDob());
			mapClient.put("address", client.getAddress());
			
			mapClient.put("phoneNumber", client.getPhoneNumber());
			mapClient.put("note", client.getNote());
			mapClient.put("alias", client.getAlias());
			mapClient.put("ssn", client.getSsn());

			mapClient.put("fullName", client.getFullName());
			mapClient.put("email", client.getEmail());
			mapClient.put("createdDate", client.getCreatedDate());
			result.add(mapClient);
		}
		
		return result;
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
			return "name null";
		}
		try {
			client.setCreatedDate(new Date());
			clientDao.addClient(client);
			return "successful";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		
		
	}

	@Override
	public String updateClient(Client client) {
		clientDao.updateClient(client);
		return null;
	}

	@Override
	public Map<String, Object> getClientByID(int clientID) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (clientID == 0) {
			result.put("message", "clientID invalidate");
			return result;
		}
		Client client = clientDao.getClientByID(clientID);
		List<MedicalRecord> medicalRecord = medicalRecordDao.getMedicalRecordByClient(client);
		result.put("clientID", clientID);
		result.put("gender", client.getGender());
		result.put("dob", client.getDob());
		result.put("address", client.getAddress());
		
		result.put("phoneNumber", client.getPhoneNumber());
		result.put("note", client.getNote());
		result.put("alias", client.getAlias());
		result.put("ssn", client.getSsn());

		result.put("fullName", client.getFullName());
		result.put("email", client.getEmail());
		result.put("createdDate", client.getCreatedDate());
		result.put("numberOfRecord", medicalRecord.size());
		
		return result;
	}

	

}
