package com.fpt.projectfinal.services.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categorydao;

	@Override
	public List<Map<String, Object>> getAllCategory() {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Category> listCategory = categorydao.getAllCategory();
		
		for(Category category : listCategory) {
			Map<String, Object> mapCategory = new HashMap<>();
			mapCategory.put("categoryID", category.getCategoryID());
			mapCategory.put("createdDate", category.getCreatedDate());
			mapCategory.put("description", category.getDescription());
			mapCategory.put("categoryID", category.getCategoryID());
			mapCategory.put("image", category.getImage());
			mapCategory.put("modifiedDate", category.getModifiedDate());
			mapCategory.put("name", category.getName());
			result.add(mapCategory);
		}
		return result;
	}

	@Override
	public String addCategory(Category category) {
		if (category.getName() == null) {
			return "name null";
		}
	
		category.setCreatedDate(new Date());
		category.setModifiedDate(new Date());
		categorydao.addCategory(category);
		return "successful";
	}

	@Override
	public void updateCategory(Category category) {
		
		category.setModifiedDate(new Date());
		categorydao.updateCategory(category);

	}

	@Override
	public Map<String, Object> getCategoryByID(int id) {
		Category category = categorydao.getCategoryByID(id);
		Map<String, Object> mapCategory = new HashMap<>();
		mapCategory.put("categoryID", category.getCategoryID());
		mapCategory.put("createdDate", category.getCreatedDate());
		mapCategory.put("description", category.getDescription());
		mapCategory.put("categoryID", category.getCategoryID());
		mapCategory.put("image", category.getImage());
		mapCategory.put("modifiedDate", category.getModifiedDate());
		mapCategory.put("name", category.getName());
		return mapCategory;
	}

	@Override
	public List<Map<String, Object>> CountCategory() {
		List<Category> list = this.categorydao.CountCategory();
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		for (Category c : list) {
			Map<String, Object> cmap = new HashMap<>();
			cmap.put("categoryID", c.getCategoryID());
			cmap.put("name", c.getName());
			cmap.put("image", c.getImage());
		
			cmap.put("description", c.getDescription());
			cmap.put("createdDate", c.getCreatedDate());
			cmap.put("modifiedDate", c.getModifiedDate());
			Set<Post> setPost = c.getPost();
			int count=0;
			for (Post post : setPost) {
				if (post.getStatus().equals(1) || post.getStatus().equals(2)) count++;
			}
			cmap.put("numberOfPosts", count);
			map.add(cmap);
		}

		return map;
	}

	@Override
	public List<Map<String, Object>> CountPostByCategory() {
		// TODO Auto-generated method stub
		return null;
	}

}
