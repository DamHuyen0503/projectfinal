package com.fpt.projectfinal.daos.test;

import java.util.List;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;

public interface TestDao {
	
	public void updateTest(Test test);
	
	public Test getTestById(int testID);
	
	public List<Post> getAllTest(Category category);
	
	public void addTest(Test test);
	
}