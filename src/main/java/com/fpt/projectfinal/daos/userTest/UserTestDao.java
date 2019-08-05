package com.fpt.projectfinal.daos.userTest;

import java.util.List;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.User;
import com.fpt.projectfinal.models.UserTest;

public interface UserTestDao {
	
	public void addUserTest(UserTest userTest);
	
	public int countUserByTest(Post test);
	
	public Long getNumberOfUserTest();
	
	public List<UserTest> getUserTestByUser(User user);
	
	
}
