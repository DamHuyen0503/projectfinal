package com.fpt.projectfinal.services.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.daos.user.UserDAO;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;
import com.fpt.projectfinal.models.User;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	TagDao tagDao;
	
	@Override
	public Test getTestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTest(Map<String, Object> payload) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userDao.getUserByEmail(username) ;
		Category category = categoryDao.getCategoryByID(1);
		Test test = new Test();
		test.setUser(u);
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
		test.setTag(tags);
		
	}

	@Override
	public void updateTest(Map<String, Object> payload) {
		// TODO Auto-generated method stub
		
	}

}
