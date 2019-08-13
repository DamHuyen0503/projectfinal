package com.fpt.projectfinal.daos.user;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserDao {
	
	public User getUserByEmail(String email);
	
	/*
	 * get user by userID. 
	 * userIDでユーザーを取得します。 
	 */
	public User getUserByID(int userID);
	
	/*
	 * get all information of the user who took the test.
	 * テストを受けたユーザーのすべての情報を取得します。
	 */
	public User getUserByTest(Post test);
	
	/*
	 * update user. 
	 * ユーザーを更新します。
	 */
	public void updateUser(User user);
	
	/*
	 * get all information user. 
	 * すべての情報ユーザーを取得します。
	 */
	public List<User> getAllUser(Map<String, Object> payload);
	
	
	public User getUserByAccount (Account account);
	
	/*
	 * count total users.
	 * 合計ユーザーをカウントします。
	 */
	public Long getNumberOfUser();
	
	public List<User> getAll() ;
	
	/*
	 * Get the user information as an expert.
	 * エキスパートとしてユーザー情報を取得します。
	 */
	public List<User> getAllExpert();
}
