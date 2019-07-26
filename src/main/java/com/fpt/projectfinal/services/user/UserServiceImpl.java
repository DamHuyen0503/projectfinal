package com.fpt.projectfinal.services.user;

import org.springframework.beans.factory.annotation.Autowired;
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

}
