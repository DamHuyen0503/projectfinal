package com.fpt.projectfinal.services.authentication;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.role.RoleDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.User;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Pausing;

@Service
public class AuthenServiceImpl implements AuthenService {

	@Autowired
	private AccountDao accDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Override @Transactional
	public List<Account> getAllAccount() {
		
		List<Account> listAcc = accDao.getAllAccount();
		return listAcc;
	}

	@Override @Transactional
	public Account getAccountByEmail(String email) {
		Account acc = accDao.getAccountByEmail(email);
		return acc;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String addAccount(Map<String, Object> payload) {
		if (payload.get("email") == null) {
			return "email null";
		}
		List<Account> accounts = accDao.getAllAccount();
		for (Account account : accounts) {
			 if (account.getEmail().equals(payload.get("email"))) {
				 return "email duplicate";
			 }
		}
		if (payload.get("password") == null) {
			return "password null";
		}
		if (payload.get("password").equals("")) {
			return "password empty";
		}
		try {
			Account acc = new Account();
			acc.setEmail((String)payload.get("email"));
			acc.setPassword((String)payload.get("password"));
			Role r = roleDao.getRoleByID(1);
			Set<Role> setRole = new HashSet<>();
			setRole.add(r);
			acc.setRoles(setRole);
			Map<String,Object> mapUser =  (Map<String, Object>) payload.get("user");
			User u = new User();
			u.setGender((int) mapUser.get("gender"));
			u.setAddress((String)mapUser.get("address"));
			u.setCreatedDate(new Date());
			u.setFirstName((String)mapUser.get("firstName"));
			u.setLastName((String)mapUser.get("lastName"));
			u.setPhoneNumber((String)mapUser.get("phoneNumber"));
			u.setAvatar((String)mapUser.get("avatar"));
			u.setDOB((Date)mapUser.get("DOB"));
			
			acc.setRoles(roleDao.getRoleByName("User"));
			u.setAccount(acc);
			acc.setUser(u);
			 accDao.addAccount(acc);
			
			return "successful";
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
	}
	
	@Override
	public String updateAccount(Map<String, Object> payload) {
		if (payload.get("userID") == null) {
			return "userID null";
		}
		try {
			User user = userDao.getUserByID((int)payload.get("userID"));
			
			Set<Role> roles = new HashSet<>();
			List<Role> listRole = roleDao.getAllRole();
			ArrayList<Integer> roleObj = (ArrayList<Integer>) payload.get("roleID");
			for (Integer obj : roleObj) {
				boolean exist = false;
				for (Role role : listRole) {
					if (obj == role.getRoleID()) {
						roles.add(role);
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
				roles.add(new Role());
			}
			Account account = user.getAccount();
			account.setRoles(roles);
			accDao.updateAccount(account);
			return "successful";
		}catch (Exception e) {
			return e.getMessage();
		}
		
	}

	

}
