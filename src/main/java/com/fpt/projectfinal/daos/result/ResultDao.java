package com.fpt.projectfinal.daos.result;

import java.util.Set;

import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Test;

public interface ResultDao {
	
	public Set<Result> getResultByTest(Test test);
}
