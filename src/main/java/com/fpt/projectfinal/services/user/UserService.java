package com.fpt.projectfinal.services.user;

import java.util.Map;

import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserService {
	
	public User getUserByEmail(String email); 
	
	public User getUserByID (int userID);
	
	public User getUserByTest(Test test);
	
	public String updateUser(Map<String, Object> payload);
}
