package com.fpt.projectfinal.services.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.Check;
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
	public Map<String, Object> getAllClient(String sort, String order, int page, String searchString, int status, int expert) {
		Map<String, Object> mapClientResult = new HashMap<>();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try { 
			Map<String, Object> mapResult = clientDao.getAllClient(sort, order, page, searchString, status, expert);
			
			List<Client> listClient = (List<Client>) mapResult.get("listClient");
			int count = (int) mapResult.get("count");
			mapClientResult.put("count", count);
			for(Client client : listClient) {
				Map<String, Object> mapClient = new HashMap<>();
				List<MedicalRecord> listMedical = medicalRecordDao.getMedicalRecordByClient(client);
				
				if (status != 0) {
					mapClient.put("status", status-1);	
				}else 
				   {
					 
					   mapClient  = new HashMap<>();
					   int check = 1;
					   for (MedicalRecord medical : listMedical) 
					   	{
							if (medical.getStatus() == 0) {
								check = 0;
							}
						}
						if (check == 0) {
							mapClient.put("status", 0);
						}
						else {
							mapClient.put("status", 1);
						}
				}
				List<Integer> medicalID = new ArrayList<>(); 
				for (MedicalRecord m : listMedical) {
					medicalID.add(m.getMedicalRecordID());
				}
				mapClient.put("medicalRecordID", medicalID);
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
			mapClientResult.put("listClient", result);
			return mapClientResult;
		}catch (Exception e) {
			result = new ArrayList<>();
			Map<String, Object> error = new HashMap<>();
			error.put("error", e.getMessage());
			return error;
		}
		
	}
	
	@Override
	public Object addClient(Map<String, Object> payload) {
		Client client = new Client();
		try {
			if (payload.get("dob") == null) {
				return "DOB null";
			}
	
			if ((int)payload.get("gender") <0  || (int)payload.get("gender") >2) {
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
			client.setAlias((String) payload.get("alias"));
			client.setCreatedDate(new Date());
			clientDao.addClient(client);
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return client;
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

	@Override
	public Map<String, Object> getAllClient(String sort, String order, int page, String searchString,int status, String username) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapResult = new HashMap<>();
		try {
			List<Client> setClient = clientDao.getAllClient(sort, order, page, searchString, status, username);
//			System.out.println("size"+ setClient.size());
//			System.out.println("userName:" + username);
			for(Client client : setClient) {
				List<MedicalRecord> listMedical = medicalRecordDao.getMedicalRecordByClient(client);
				Map<String, Object> mapClient = new HashMap<>();
				
				List<Integer> medicalID = new ArrayList<>(); 
				for (MedicalRecord m : listMedical) {
					medicalID.add(m.getMedicalRecordID());
				}
				mapClient.put("medicalRecordID", medicalID);
				mapClient.put("status", status);
				
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
				mapClient.put("countMedicalRecord", listMedical.size());
				result.add(mapClient);
			}
			mapResult.put("count", result.size());
			mapResult.put("listClient", result);
			return mapResult;

		} catch (Exception e) {
			result = new ArrayList<>();
			Map<String, Object> error = new HashMap<>();
			error.put("error", e.getMessage());
			return error;
		}
			}

	@Override
	public Map<String, Object> getAll(String sort, String order, int page, String searchString) {
		Map<String, Object> mapresult = new HashMap<>();
		List<Object> clientList = new ArrayList<>();
		try {
			Map<String, Object> clientMap = clientDao.getAll(sort, order, page, searchString);
			List<Client> listClient = (List<Client>) clientMap.get("listClient");
			Map<String, Object> mapClient  = new HashMap<>();
			mapresult.put("count", clientMap.get("count"));
			
			for(Client client : listClient) {
				List<MedicalRecord> listMedical = medicalRecordDao.getMedicalRecordByClient(client);
				mapClient  = new HashMap<>();
				int check = 1;
				for (MedicalRecord medical : listMedical) {
					
					if (medical.getStatus() == 0) {
						check = 0;
					}
				}
				if (check == 0) {
					mapClient.put("status", 0);
				}
				else {
					mapClient.put("status", 1);
				}
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
				List<Integer> listMedicalID = new ArrayList<>();
				mapClient.put("countMedicalRecord", listMedical.size());
				clientList.add(mapClient);
			}
			mapresult.put("listClient", clientList);
			return mapresult;
		} catch (Exception e) {
			mapresult = new HashMap<>();
			mapresult.put("error", e.getMessage());
			return mapresult;
		}
		
	}

	@Override
	public Long countClient() {
		return clientDao.countClient();
	}

	

}
