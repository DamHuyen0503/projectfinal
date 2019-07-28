package com.fpt.projectfinal.services.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao; 
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByID(int userID) {
		User user = userDao.getUserByID(userID); 
		return user;
	}

	@Override
	public User getUserByTest(Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(Map<String, Object> payload) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (payload.get("userID") == null) {
			return "userID is null";
		}
		User user = userDao.getUserByID((int)payload.get("userID"));
		try {
			user.setAddress((String)payload.get("dob"));
			user.setGender((int)payload.get("gender"));
			
			user.setAddress((String)payload.get("address"));
			user.setPhoneNumber((String)payload.get("phoneNumber"));
			user.setFirstName((String)payload.get("firstName"));
			user.setLastName((String)payload.get("lastName"));
			user.setAvatar((String)payload.get("avatar"));
			userDao.updateUser(user);
			return "successful";
		}catch (Exception e) {
			return "update fail";
		}
	}

}
