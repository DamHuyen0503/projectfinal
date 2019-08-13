package com.fpt.projectfinal.daos.account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Role;
import com.fpt.projectfinal.models.User;


public interface AccountDao {
	
	
	
	/*
	 * Create new website access account.
	 * 新しいサイトにアクセスするためのアカウントを作成します。
	 */
	public void addAccount(Account acc);
	
	/*
	 * Get all  information account by email.
	 * メールですべての情報アカウントを取得します。
	 */
	public Account getAccountByEmail(String email);
	
	/*
	 * Get all information account. 
	 * すべての情報アカウントを取得します。
	 */
	public List<Account> getAllAccount();
	
	/*
	 * Get information account by role. 
	 * 役割ごとに情報アカウントを取得します。
	 */
	
	public Set<Account> getAccountByRole(Role role);
	
	/*
	 * update account information.
	 * アカウント情報を更新する
	 */
	public void updateAccount (Account account);
	
	/*
	 * Get information account by user. 
	 * ユーザーごとに情報アカウントを取得します。
	 */
	public Account getAccountByUser(User user);
}


