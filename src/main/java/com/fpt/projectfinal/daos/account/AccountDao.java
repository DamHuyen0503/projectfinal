package com.fpt.projectfinal.daos.account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;


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
	
	public Set<Account> getAccountByRole(Role role);
	
	public void updateAccount (Account account);
	
	public Account getAccountByUser(User user);
}


