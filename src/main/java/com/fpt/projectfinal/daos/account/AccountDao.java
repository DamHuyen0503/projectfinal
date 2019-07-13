package com.fpt.projectfinal.daos.account;

import java.util.List;

import com.fpt.projectfinal.models.Account;


public interface AccountDao {
	
	
	
	/*
	 * huyendt
	 * 12/07/2019
	 * add account
	 */
	public int addAccount(Account acc);
	
	/*
	 * huyendt
	 * 12/07/2019
	 * get account by email 
	 */
	public Account getAccountByEmail(String email);
	
	/*
	 * huyendt
	 * 12/07/2019
	 * get all account
	 */
	public List<Account> getAllAccount();
}


