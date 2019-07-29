package com.fpt.projectfinal.daos.userTest;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.UserTest;

public interface UserTestDao {
	
	public void addUserTest(UserTest userTest);
	
	public int countUserByTest(Post test);
}
