package com.fpt.projectfinal.services.usertest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.result.ResultDao;
import com.fpt.projectfinal.daos.test.TestDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.userTest.UserTestDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Result;
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
	
	@Autowired
	ResultDao resultDao;
	
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
	
	@Override
	public Long getNumberOfUserTest() {
		return userTestDao.getNumberOfUserTest();
		
	}

	@Override
	public List<Map<String, Object>> getUserTestByUser() {
		//List<User> users = userDao.getAll();
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> listUserTest = new ArrayList<Map<String,Object>>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acc = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(acc);
		if (username.equals("")) {
			result.put("message", "username null");
			listUserTest.add(result);
			return listUserTest;
		}
		List<UserTest> userTest = userTestDao.getUserTestByUser(user);
		if (userTest == null) {
			result.put("message", "not found");
			listUserTest.add(result);
			return listUserTest;
		}
		try {
			
			for (UserTest u : userTest) {
				Test test = testDao.getTestById(u.getTest().getPostID());
				result.put("postID", test.getPostID());
				result.put("image", test.getImage());
				result.put("title", test.getTitle());
				result.put("result", u.getResult());
				Set<Result> setResult = resultDao.getResultByTest(test);
				Map<String, Object> mapContentResult = new HashMap<>();
				List<Object> contentResult = new ArrayList<>();
				for(Result res : setResult) {
					mapContentResult.put("resultID", res.getResultID());
					mapContentResult.put("content", res.getContent());
					contentResult.add(mapContentResult);
				}
				result.put("resultContent", contentResult);
				result.put("date", u.getDate());
				listUserTest.add(result);
			}
			return listUserTest;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = new HashMap<String, Object>();
			listUserTest = new ArrayList<>();
			result.put("message", e.getMessage());
			listUserTest.add(result);
			return listUserTest;
		}
		
	}
}
