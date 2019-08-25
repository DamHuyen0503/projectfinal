package com.fpt.projectfinal.services.test;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Test;

public interface TestService {
	

	/*
	 * create test
	 * テストを作成する.
	 */
	public String addTest(Map<String, Object> payload);
	
	/*
	 * update test.
	 * 更新テスト。
	 */
	public String updateTest(Map<String, Object> payload);

	/*
	 * get test by ID.
	 * IDでテストを取得します。
	 */
	public Map<String, Object> getTestById(int id);

	/*
	 * get all Information of test. 
	 * テストのすべての情報を取得します。
	 */
	public List<Map<String, Object>> getAllTest();
	
	public int checkTest(int testID);
}
