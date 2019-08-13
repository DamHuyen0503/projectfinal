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
import com.fpt.projectfinal.utils.ConvertTimestamp;
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
	public String addClient(Map<String, Object> payload) {
		Client client = new Client();
		try {
			if (payload.get("dob") == null) {
				return "DOB null";
			}
	
			if ((int)payload.get("gender") <=0  || (int)payload.get("gender") >2) {
				return "gender invalid";
			}
			if (payload.get("address") == null) {
				return "address null";
			}
			if (payload.get("phoneNumber") == null) {
				return "phone null";
			}
			if (payload.get("fullName") == null) {
				return "name null";
			}
		
			client.setSsn((String) payload.get("ssn"));
			client.setEmail((String) payload.get("email"));
			Date dob = ConvertTimestamp.ConvertDateTime((String) payload.get("dob"));
			client.setDob(dob);
			client.setGender((int) payload.get("gender"));
			client.setFullName((String)payload.get("fullName"));
			client.setPhoneNumber((String) payload.get("phoneNumber"));
			client.setAddress((String)payload.get("address"));
			client.setCreatedDate(new Date());
			clientDao.addClient(client);
			return "successful";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
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
		try {
			if (clientID <= 0) {
				result.put("message", "clientID invalid");
				return result;
			}
			Client client = clientDao.getClientByID(clientID);
			if (client == null ) {
				result.put("message", "client not found");
				return result;
			}
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
			
		}catch (Exception e) {
			Map<String, Object> resultError = new HashMap<String, Object>();
			resultError.put("error", e.getMessage());
			return result;
		}
		
	}

	

}
