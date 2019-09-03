package com.fpt.projectfinal.daos.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Client;
import com.fpt.projectfinal.models.MedicalRecord;

public interface ClientDao {
	
	public Map<String, Object> getAll(String sort, String order, int page, String searchString);
	
	public List<Client> getAllClient(String sort, String order, int page, String searchString, int status, String username);
	
	public Map<String , Object> getAllClient(String sort, String order, int page, String searchString, int status, int expert);
	
	/*
	 * Create new client. 
	 * 新しいクライアントを作成します。
	 */
	public  void addClient(Client client);
	
	/*
	 * Update information customers.
	 * 情報の顧客を更新します。
	 */
	public String updateClient(Client client);
	
	/*
	 * Get information client by clientID.
	 * clientIDで情報クライアントを取得します。
	 */
	public Client getClientByID(int id);
	
	/*
	 * Get information client by medicalRecordID.
	 * medicalRecordIDによって情報クライアントを取得します。
	 */
	public Set<Client> getClientByMedicalRecord(MedicalRecord medicalRecord);
	
	public List<ClientDao> getClientByExpert(String sort, String order, int page, String searchString, int status);
	
	public Long countClient();
}
