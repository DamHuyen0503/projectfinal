package com.fpt.projectfinal.services.usertest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.test.TestDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.userTest.UserTestDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserTest;
@Service
public class UserTestServiceImpl implements UserTestService {
	@Autowired
	UserTestDao userTestDao;
	
	@Autowired
	TestDao testDao;
	
	@Autowired 
	UserDao userDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public String addUserTest(Map<String, Object> payload) {
		UserTest userTest = new UserTest();
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Test test =	testDao.getTestById((int)payload.get("postID"));
			test.setUser(userDao.getUserByTest(test));
			Account acc = accountDao.getAccountByEmail(username);
			userTest.setUser(acc.getUser()	);
			userTest.setDate(new Date());
			userTest.setTest(test);
			userTest.setResult((int)payload.get("result"));
			userTestDao.addUserTest(userTest);
			return "successful";
		}catch (Exception e) {
			System.out.print(e.getMessage());
			return "add fail";
		}
		
	}

	@Override
	public List<UserTest> getUserTestByPostID(int postID) {
		// TODO Auto-generated method stub
		return null;
	}
}
