package com.fpt.projectfinal.services.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	@Override
	public List<Map<String, Object>> getAllUser(Map<String, Object> payload) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<User> listUser = userDao.getAllUser(payload);
		for (User user : listUser) {
			Map<String, Object> u = new HashMap<String, Object>();
			u.put("address", user.getAddress());
			u.put("userID", user.getUserID());
			u.put("firstName", user.getFirstName());
			u.put("createdDate", user.getCreatedDate());
			u.put("avatar", user.getAvatar());
			u.put("dob", user.getDOB());
			u.put("gender", user.getGender());
			u.put("lastName", user.getLastName());
			u.put("phoneNumber", user.getPhoneNumber());
			
			result.add(u);
		}
		return result;
	}

	@Override
	public Long getNumberOfUser() {
		
		return userDao.getNumberOfUser();
	}

}
