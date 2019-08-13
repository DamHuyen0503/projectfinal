package com.fpt.projectfinal.services.authentication;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Account;



public interface AuthenService {
	
	/*
	 * Create new website access account.
	 * 新しいサイトにアクセスするためのアカウントを作成します。
	 */
	public String addAccount(Map<String,Object> payload);

	/*
	 * Get all information account. 
	 * すべての情報アカウントを取得します。
	 */
	public List<Account> getAllAccount();
	
	/*
	 * Get all  information account by email.
	 * メールですべての情報アカウントを取得します。
	 */
	public Account getAccountByEmail(String email);
	
	/*
	 * update account information.
	 * アカウント情報を更新する
	 */
	public String updateAccount(Map<String, Object> payload);
}
