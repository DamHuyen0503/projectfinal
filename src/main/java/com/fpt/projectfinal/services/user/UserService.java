package com.fpt.projectfinal.services.user;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

public interface UserService {
	
	public User getUserByEmail(String email); 
	
	/*
	 * get user by userID. 
	 * userIDでユーザーを取得します。 
	 */
	public Map<String, Object> getUserByID (int userID);
	
	/*
	 * get all information of the user who took the test.
	 * テストを受けたユーザーのすべての情報を取得します。
	 */
	public User getUserByTest(Test test);
	
	/*
	 * update user. 
	 * ユーザーを更新します。
	 */
	public String updateUser(Map<String, Object> payload);
	
	/*
	 * get all information user. 
	 * すべての情報ユーザーを取得します。
	 */
	public Map<String, Object> getAllUser(String sort, String order, int page, int roleID, String searchString);
	
	/*
	 * count total users.
	 * 合計ユーザーをカウントします。
	 */
	public Long getNumberOfUser();
	
	/*
	 * update information user send request. 
	 * 更新情報ユーザー送信リクエスト。
	 */
	public Map<String, Object> getUserSendRequest();
	
	/*
	 * update information user send request. 
	 * 更新情報ユーザー送信リクエスト。
	 */
	public String updateUserSendRequest(Map<String, Object> payload);
	
	/*
	 * Get the user information as an expert.
	 * エキスパートとしてユーザー情報を取得します。
	 */
	public Map<String, Object> getAllExpert();
	
	
}
