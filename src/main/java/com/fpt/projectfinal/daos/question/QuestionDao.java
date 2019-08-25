package com.fpt.projectfinal.daos.question;

import java.util.List;

import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Test;

public interface QuestionDao {
	
	/*
	 * get all question of test.
	 * テストのすべての質問を取得します。  
	 */
	public List<Question> getQuestionByTest(Test test);
	
	public void deleteQuestion(Question question);
}
