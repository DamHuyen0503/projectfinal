package com.fpt.projectfinal.services.user;

import com.auth0.json.mgmt.users.User;

public interface UserService {
	
	public User getUserByEmail(String email); 
}
