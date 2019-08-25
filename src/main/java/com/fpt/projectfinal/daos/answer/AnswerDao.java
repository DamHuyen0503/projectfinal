package com.fpt.projectfinal.daos.answer;

import java.util.Set;

import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Question;

public interface AnswerDao {
	public Set<Answer> getAnswerByQuestion(Question question);
	
	public void deleteAnswer(Answer answer);
}
