package com.fpt.projectfinal.daos.test;

import java.util.List;

import com.fpt.projectfinal.models.Test;

public interface testDao {
	
	public void updateTest(Test test);
	
	public Test getTestById(int id);
	
	public List<Test> getAllTest();
	
	public void addTest(Test test);
	
}