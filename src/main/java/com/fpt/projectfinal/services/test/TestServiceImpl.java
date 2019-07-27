package com.fpt.projectfinal.services.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.answer.AnswerDao;
import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.question.QuestionDao;
import com.fpt.projectfinal.daos.result.ResultDao;
import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.daos.test.TestDao;
import com.fpt.projectfinal.daos.user.UserDao;
import com.fpt.projectfinal.daos.userTest.UserTestDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Answer;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Question;
import com.fpt.projectfinal.models.Result;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

@Service
public class TestServiceImpl implements TestService {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	UserDao userDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	TagDao tagDao;

	@Autowired
	TestDao testDao;

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	AnswerDao answerDao;
	
	@Autowired
	ResultDao resultDao;

	@Autowired 
	UserTestDao userTestDao;

	@Override
	public List<Map<String, Object>> getAllTest() {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Post> tests = testDao.getAllTest();
//		Map<String, Object> result = new HashMap<>();
		for(Post test : tests) {
			Map<String, Object> mapTest = new HashMap<>();
			System.out.println("user" + test.getPostID());
			User user = userDao.getUserByTest(test);
			int count = userTestDao.countUserByTest(test);
			mapTest.put("postID", test.getPostID());
			mapTest.put("title", test.getTitle());
			mapTest.put("description", test.getDescription());
			mapTest.put("image", test.getImage());
			mapTest.put("status", test.getStatus());
			mapTest.put("userID", user.getUserID());
			mapTest.put("userName", user.getFirstName());
			mapTest.put("categoryID", 1);
			mapTest.put("modifiedDate", test.getModifiedDate());
			mapTest.put("createdDate", test.getCreatedDate());
			mapTest.put("participants", count);
			result.add(mapTest);
			
		}
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getTestById(int id) {
		
		Map<String, Object> map = new HashMap<>();
		if (id == 0) {
			map.put("message", "id null");
			return map;
		}
		Category category = categoryDao.getCategoryByID(1);
		Test test = testDao.getTestById(id);
		test.setCategory(category);
		List<Question> questions = questionDao.getQuestionByTest(test);
		for(Question q :questions) {
			q.setAnswer(answerDao.getAnswerByQuestion(q));
		}
		
		test.setQuestion(new HashSet(questions));
		test.setTags(tagDao.getTagByTest(test));
		test.setResult(resultDao.getResultByTest(test));
		
		if (test.getCategory().getCategoryID() != 1) {
			map.put("error", "id invalidate");
			return map;
		}
		List<Map<String, Object>> quests = new ArrayList<>();
		for (Question quest : test.getQuestion()) {
			Map<String, Object> mapQuest = new HashMap();
			List<Map<String, Object>> answers = new ArrayList<>();
			for (Answer ans : quest.getAnswer()) {
				Map<String, Object> mapAns = new HashMap();
				mapAns.put("answerID", ans.getAnswerID());
				mapAns.put("content", ans.getContent());
				mapAns.put("image", ans.getImage());
				mapAns.put("score", ans.getScore());
				answers.add(mapAns);
			}
			mapQuest.put("anwsers", answers);
			mapQuest.put("content", quest.getContent());
			mapQuest.put("score", quest.getScore());
			quests.add(mapQuest);
		}
		map.put("questions", quests);
		map.put("title", test.getTitle());
		map.put("content", test.getContent());
		map.put("description", test.getDescription());
		map.put("image", test.getImage());
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("email user", username);
		List<String> tags = new ArrayList();
		for (Tag tag : test.getTags()) {
			tags.add(tag.getContent());
		}
		map.put("tags", tags);
		List<Map<String, Object>> results = new ArrayList<>();
		for (Result result : test.getResult()) {
			Map<String, Object> mapResult = new HashMap<>();
			mapResult.put("resultID", result.getResultID());
			mapResult.put("content", result.getContent());
			mapResult.put("lowestPoint", result.getLowestPoint());
			mapResult.put("highestPoint", result.getHighestPoint());
			results.add(mapResult);
		}
		map.put("results", results);
		return map;
	}



	@Override
	public String addTest(Map<String, Object> payload) {
		
		if (payload.get("status") == null) {
			return "status null";
		}
		if ((int)payload.get("status") <0 || (int) payload.get("status")>4) {
			return "status not valid";
		}
		if (payload.get("title").toString().length() >255) {
			return "title more than 255";
		}
		if (payload.get("description").toString().length() >255) {
			return "description more than 255";
		}
		if (payload.get("image").toString().length() >255) {
			return "image more than 255";
		}
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account acc = accountDao.getAccountByEmail(username);
			Category category = categoryDao.getCategoryByID(1);
			Test test = new Test();
			test.setUser(acc.getUser());
			test.setCreatedDate(new Date());
			test.setTitle((String) payload.get("title"));
			test.setCategory(category);
			test.setContent((String) payload.get("content"));
			test.setImage((String) payload.get("image"));
			test.setDescription((String) payload.get("description"));

			List<Tag> listTag = tagDao.getAllTag();
			Set<Tag> tags = new HashSet<>();
			ArrayList<String> tagObjs = (ArrayList<String>) payload.get("tags");
			for (String obj : tagObjs) {
				boolean exist = false;
				for (Tag tag : listTag) {
					if (obj.equalsIgnoreCase(tag.getContent())) {
						tags.add(tag);
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
				tags.add(new Tag(obj, new Date()));
			}
			test.setTags(tags);
			Set<Question> questions = new HashSet<>();
			ArrayList<Object> question_list = (ArrayList<Object>) payload.get("questions");
			for (Object quest : question_list) {
				Map<String, Object> questmap = (Map) quest;
				Set<Answer> answers = new HashSet<>();
				ArrayList<Object> answer_list = (ArrayList<Object>) questmap.get("answers");
				Question q = new Question();
				for (Object ans : answer_list) {
					Map<String, Object> ansmap = (Map) ans;
					
					answers.add(new Answer((String) ansmap.get("content"), (int) ansmap.get("score"),
							(String) ansmap.get("image"), q));
				}
				q.setContent((String) questmap.get("content"));
				q.setAnswer(answers);
				q.setScore((int) questmap.get("score"));
				q.setTest(test);
				questions.add(q);
			}
			test.setQuestion(questions);
			
			
			Set<Result> results = new HashSet<>();
			ArrayList<Object> result_list = (ArrayList<Object>) payload.get("results");
			for (Object result : result_list) {
				Map<String, Object> resultmap = (Map) result;

				results.add(new Result((int) resultmap.get("highestPoint"), (int) resultmap.get("lowestPoint"),
						(String) resultmap.get("content"), test));
			}
			test.setResult(results);
			testDao.addTest(test);
			return "successful";
		} catch (Exception e) {
			
			logger.warn(e.getMessage(), e);
			return "add fail";
		}
		
	}

	@Override
	public String updateTest(Map<String, Object> payload) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (username == null) {
			return "token fail";
		}
		Test test = new Test();
		if (payload.get("postID") == null) {
			return "postID null";
		}
		if (payload.get("title").toString().length() >255) {
			return "title more than 255";
		}
		if (payload.get("description").toString().length() >255) {
			return "description more than 255";
		}
		if (payload.get("image").toString().length() >255) {
			return "image more than 255";
		}
		try {
			Account acc = accountDao.getAccountByEmail(username);
			Category category = categoryDao.getCategoryByID(1);
			test.setPostID((int) payload.get("postID"));
			test.setUser(acc.getUser());
			test.setCreatedDate(new Date());
			test.setTitle((String) payload.get("title"));
			test.setCategory(category);
			test.setContent((String) payload.get("content"));
			test.setImage((String) payload.get("image"));
			test.setDescription((String) payload.get("description"));

			List<Tag> listTag = tagDao.getAllTag();
			Set<Tag> tags = new HashSet<>();
			ArrayList<String> tagObjs = (ArrayList<String>) payload.get("tags");
			for (String obj : tagObjs) {
				boolean exist = false;
				for (Tag tag : listTag) {
					if (obj.equalsIgnoreCase(tag.getContent())) {
						tags.add(tag);
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
				tags.add(new Tag(obj, new Date()));
			}
			test.setTags(tags);
			Set<Question> questions = new HashSet<>();
			ArrayList<Object> question_list = (ArrayList<Object>) payload.get("questions");
			for (Object quest : question_list) {
				Map<String, Object> questmap = (Map) quest;
				Set<Answer> answers = new HashSet<>();
				ArrayList<Object> answer_list = (ArrayList<Object>) questmap.get("answers");
				Question q = new Question();
				for (Object ans : answer_list) {
					Map<String, Object> ansmap = (Map) ans;

					answers.add(new Answer((String) ansmap.get("content"), (int) ansmap.get("score"),
							(String) ansmap.get("image"), q));
				}
				q.setContent((String) questmap.get("content"));
				q.setAnswer(answers);
				q.setScore((int) questmap.get("score"));
				q.setTest(test);
				questions.add(q);
			}
			test.setQuestion(questions);
			Set<Result> results = new HashSet<>();
			ArrayList<Object> result_list = (ArrayList<Object>) payload.get("results");
			for (Object result : result_list) {
				Map<String, Object> resultmap = (Map) result;

				results.add(new Result((int) resultmap.get("highestPoint"), (int) resultmap.get("lowestPoint"),
						(String) resultmap.get("content"), test));
			}
			test.setResult(results);
			testDao.updateTest(test);
			return "successful";
		} catch (Exception e) {
			e.getMessage();
			return "update fail";
		}
		
}

}
