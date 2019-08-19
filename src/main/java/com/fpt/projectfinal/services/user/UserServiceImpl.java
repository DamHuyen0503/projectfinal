package com.fpt.projectfinal.services.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.coyote.http2.Http2AsyncUpgradeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.controllers.role.RoleController;
import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.role.RoleDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.utils.ConvertTimestamp;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao; 
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	RoleDao roleDao;
	
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUserByID(int userID) {
		try {
			Map<String,  Object> result = new HashMap();
			if (userID <=0) {
				result.put("message", "userID invalid");
				return result;
			}
			
			
			User user = userDao.getUserByID(userID); 
			
			result.put("address", user.getAddress());
			result.put("avatar", user.getAvatar());
			result.put("createdDate", user.getCreatedDate());
			result.put("dob", user.getDOB());
			result.put("firstName", user.getFirstName());
			result.put("gender", user.getGender());
			result.put("lastName", user.getLastName());
			result.put("phoneNumber", user.getPhoneNumber());
			result.put("userID", user.getUserID());
			result.put("email", user.getAccount().getEmail());
			Account acc = accountDao.getAccountByUser(user);
			Set<Role> listRole = roleDao.getRoleByAcc(acc);
//			Map<String, Object> mapRole = new HashMap<>();
			List<String> resultRole = new ArrayList<>(); 
			for (Role role : listRole) {
				resultRole.add(role.getName());
			}
			result.put("role", resultRole);
			return result;
		} catch (Exception e) {
			Map<String,  Object> result = new HashMap();
			result.put("error", e.getMessage());
			return result;
		}
	
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

	@Override
	public Map<String, Object>  getUserSendRequest() {
		Map<String, Object> result = new HashMap<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account account = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(account);
		result.put("email", account.getEmail());
		result.put("userID", user.getUserID());
		result.put("address", user.getAddress());
		result.put("avatar", user.getAvatar());
		result.put("dob", user.getDOB());
		result.put("firstName", user.getFirstName());
		result.put("lastName", user.getLastName());
		result.put("phoneNumber", user.getPhoneNumber());
		result.put("gender", user.getGender());
		result.put("createdDate", user.getCreatedDate());
		return result;
	}

	@Override
	public String updateUserSendRequest(Map<String, Object> payload) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account account = accountDao.getAccountByEmail(username);
		User user = userDao.getUserByAccount(account);
		try {
			String dob =(String)payload.get("dob"); 
			user.setDOB(ConvertTimestamp.ConvertDateTime(dob));
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
	public List<Map<String, Object>> getAllExpert() {
		List<Map<String, Object>> result = new ArrayList<>();
		List<User> listUser = userDao.getAllExpert();
		for (User user : listUser) {
			Map<String, Object> mapUser = new HashMap<>();
			mapUser.put("address", user.getAddress());
			mapUser.put("avatar", user.getAvatar());
			mapUser.put("createdDate", user.getCreatedDate());
			mapUser.put("dob", user.getDOB());
			mapUser.put("firstName", user.getFirstName());
			mapUser.put("gender", user.getGender());
			mapUser.put("lastName", user.getLastName());
			mapUser.put("phoneNumber", user.getPhoneNumber());
			mapUser.put("userID", user.getUserID());
			mapUser.put("email", user.getAccount().getEmail());
			result.add(mapUser);
		}
		
		
		return result;
	}


}
