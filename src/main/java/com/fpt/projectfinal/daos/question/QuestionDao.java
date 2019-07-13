package com.fpt.projectfinal.daos.question;

import java.util.List;

import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Test;

public interface QuestionDao {
	public List<Question> getQuestionByTest(Test test);
}
