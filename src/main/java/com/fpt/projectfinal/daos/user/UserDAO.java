package com.fpt.projectfinal.daos.user;

import com.fpt.projectfinal.models.User;

public interface UserDAO {
	
	public User getUserByEmail(String email);
}
