package com.fpt.projectfinal.services.user;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserService {
	
	public User getUserByEmail(String email); 
	
	public User getUserByID (int userID);
	
	public User getUserByTest(Test test);
	
	public String updateUser(Map<String, Object> payload);
	
	public List<Map<String, Object>> getAllUser(Map<String, Object> payload);
	
	public Long getNumberOfUser();
	
	public Map<String, Object> getUserSendRequest();
}
