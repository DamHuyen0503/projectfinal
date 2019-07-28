package com.fpt.projectfinal.services.authentication;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Account;



public interface AuthenService {
	
	/*
	 * huyendt
	 * 12/07/2019
	 * add new account
	 */
	public int addAccount(Map<String,Object> payload);
	/*
	 * huyendt
	 * 12/07/2019
	 * get all account
	 */
	public List<Account> getAllAccount();
	
	/*
	 * huyendt
	 * 12/07/2019
	 * get account by email
	 */
	public Account getAccountByEmail(String email);
	
	public String updateAccount(Map<String, Object> payload);
}
