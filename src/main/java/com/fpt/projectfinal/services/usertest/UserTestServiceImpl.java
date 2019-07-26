package com.fpt.projectfinal.services.usertest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.test.TestDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.userTest.UserTestDao;
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
	
	@Override
	public Map<String, Object> addUserTest(Map<String, Object> payload) {
		UserTest userTest = new UserTest();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			System.out.println("postID:"+payload.get("postID"));
			System.out.println("userID:"+payload.get("userID"));
		
			Test test =	testDao.getTestById((int)payload.get("postID"));
			User user = userDao.getUserByID((int)payload.get("userID"));
			userTest.setDate(new Date());
			userTest.setTest(test);
			userTest.setUser(user);
			userTest.setResult((int)payload.get("result"));
			result.put("userID", user.getUserID());
			result.put("date", new Date() );
			result.put("postID",test.getPostID());
			result.put("result", userTest.getResult());
			userTestDao.addUserTest(userTest);
			
		}catch (Exception e) {
			e.getMessage();
		}
		return result;
	}

	@Override
	public List<UserTest> getUserTestByPostID(int postID) {
		// TODO Auto-generated method stub
		return null;
	}
}
