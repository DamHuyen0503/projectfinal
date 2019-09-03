package com.fpt.projectfinal.services.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fpt.projectfinal.models.Client;

public interface ClientService {
	
	public Map<String, Object> getAll(String sort, String order, int page, String searchString);
	
	public Map<String, Object> getAllClient(String sort, String order, int page, String searchString, int status, String username);
	
	public Map<String , Object> getAllClient(String sort, String order, int page, String searchString, int status, int expert);
	
	/*
	 * Create new client. 
	 * 新しいクライアントを作成します。
	 */
	public Object addClient(Map<String, Object> payload);
	
	/*
	 * Update information customers.
	 * 情報の顧客を更新します。
	 */
	public String updateClient(Client client);
	
	/*
	 * Get information client by clientID.
	 * clientIDで情報クライアントを取得します。
	 */
	public Map<String, Object> getClientByID(int id);
	
	public Long countClient();
}
