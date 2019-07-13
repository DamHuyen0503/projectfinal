package com.fpt.projectfinal.services.test;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Test;

public interface TestService {
	

	public void addTest(Map<String, Object> payload);
	
	public void updateTest(Map<String, Object> payload);

	public Map<String, Object> getTestById(int id);

	public List<Test> getAllTest();
}
