package com.fpt.projectfinal.daos.user;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserDao {
	
	public User getUserByEmail(String email);
	
	public User getUserByID(int userID);
	
	public User getUserByTest(Post test);
	
	public void updateUser(User user);
	
	public List<User> getAllUser(Map<String, Object> payload);
	
	public User getUserByAccount (Account account);
	
	public Long getNumberOfUser();
	
	public List<User> getAll() ;
	
	
}
