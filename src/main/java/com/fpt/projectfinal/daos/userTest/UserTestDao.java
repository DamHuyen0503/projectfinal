package com.fpt.projectfinal.daos.userTest;

import java.util.List;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserTest;

public interface UserTestDao {
	
	/*
	 * create user test. 
	 * ユーザーテストを作成します。
	 */
	public void addUserTest(UserTest userTest);
	
	/*
	 * Count the total number of users taking the test.
	 * テストを受けるユーザーの総数を数えます。
	 */
	public int countUserByTest(Post test);
	
	/*
	 * Count the total number of users taking the test.
	 * テストを受けるユーザーの総数を数えます。
	 */
	public Long getNumberOfUserTest();
	
	/*
	 * get all the tests that the user has done.
	 * ユーザーが行ったすべてのテストを取得します。
	 */
	public List<UserTest> getUserTestByUser(User user);
	
	
}
