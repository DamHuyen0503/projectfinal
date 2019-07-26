package com.fpt.projectfinal.services.usertest;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.UserTest;

public interface UserTestService {
	
	public Map<String, Object> addUserTest(Map<String, Object> payload);
	
	public List<UserTest> getUserTestByPostID(int postID);

}
