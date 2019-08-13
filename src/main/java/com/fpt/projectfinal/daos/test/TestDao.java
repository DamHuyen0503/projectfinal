package com.fpt.projectfinal.daos.test;

import java.util.List;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Test;

public interface TestDao {
	
	/*
	 * update test.
	 * 更新テスト。
	 */
	public void updateTest(Test test);
	
	/*
	 * get test by ID.
	 * IDでテストを取得します。
	 */
	public Test getTestById(int testID);
	
	/*
	 * get all Information of test. 
	 * テストのすべての情報を取得します。
	 */
	public List<Post> getAllTest(Category category);
	
	/*
	 * create test
	 * テストを作成する.
	 */
	public void addTest(Test test);
	
}