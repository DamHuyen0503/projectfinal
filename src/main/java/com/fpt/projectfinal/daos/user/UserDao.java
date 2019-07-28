package com.fpt.projectfinal.daos.user;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserDao {
	
	public User getUserByEmail(String email);
	
	public User getUserByID(int userID);
	
	public User getUserByTest(Post test);
	
	public void updateUser(User user);
}
