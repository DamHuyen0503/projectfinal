package com.fpt.projectfinal.daos.result;

import java.util.Set;

import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Test;

public interface ResultDao {
	
	/*
	 * Get all test results.
	 * すべてのテスト結果を取得します。
	 */
	public Set<Result> getResultByTest(Test test);
	
	public void deleteResult(Result result);
}
