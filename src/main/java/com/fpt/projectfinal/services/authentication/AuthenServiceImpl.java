package com.fpt.projectfinal.services.authentication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.User;

@Service
public class AuthenServiceImpl implements AuthenService {

	@Autowired
	private AccountDao accDao;

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

	@Override
	public int addAccount(Map<String, Object> payload) {
		Account acc = new Account();
		acc.setEmail((String)payload.get("email"));
		acc.setPassword((String)payload.get("password"));
		Map<String,Object> mapUser =  (Map<String, Object>) payload.get("user");
		User u = new User();
		u.setAddress((String)mapUser.get("address"));
		u.setAddress((String)mapUser.get("firstName"));
		u.setAddress((String)mapUser.get("lastName"));
		u.setPhoneNumber((String)mapUser.get("phoneNumber"));
		u.setAvatar((String)mapUser.get("avatar"));
		u.setDOB((Date)mapUser.get("DOB"));
		u.setAccount(acc);
		acc.setUser(u);
		return accDao.addAccount(acc);
		
	}

	

}
