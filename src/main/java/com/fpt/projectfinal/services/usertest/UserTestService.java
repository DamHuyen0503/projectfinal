package com.fpt.projectfinal.services.usertest;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.UserTest;

public interface UserTestService {
	
	public String addUserTest(Map<String, Object> payload);
	
	public List<UserTest> getUserTestByPostID(int postID);

	public Long getNumberOfUserTest();
}
