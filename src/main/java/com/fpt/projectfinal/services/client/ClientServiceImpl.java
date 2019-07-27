package com.fpt.projectfinal.services.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		result.put("phonenumber", client.getPhoneNumber());
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
