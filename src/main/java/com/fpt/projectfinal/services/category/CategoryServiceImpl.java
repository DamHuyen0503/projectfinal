package com.fpt.projectfinal.services.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.models.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categorydao;

	@Override
	public List<Map<String, Object>> getAllCategory() {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Category> listCategory = categorydao.getAllCategory();
		Map<String, Object> mapCategory = new HashMap<>();
		for(Category category : listCategory) {
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
	public Category getCategoryByID(int id) {

		return this.categorydao.getCategoryByID(id);
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
			cmap.put("numberOfPosts", c.getPost().size());
			map.add(cmap);
		}

		return map;
	}

}
