package com.fpt.projectfinal.services.usertest;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.UserTest;

public interface UserTestService {
	
	/*
	 * create user test. 
	 * ユーザーテストを作成します。
	 */
	public String addUserTest(Map<String, Object> payload);
	
	/*
	 * get all test by ID. 
	 * すべてのテストをIDで取得します。
	 */
	public List<UserTest> getUserTestByPostID(int postID);

	/*
	 * Count the total number of users taking the test.
	 * テストを受けるユーザーの総数を数えます。
	 */
	public Long getNumberOfUserTest();
	
	/*
	 * get all the tests that the user has done.
	 * ユーザーが行ったすべてのテストを取得します。
	 */
	public List<Map<String, Object>> getUserTestByUser();
}
