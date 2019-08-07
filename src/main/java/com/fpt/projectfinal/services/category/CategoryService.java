package com.fpt.projectfinal.services.category;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;

public interface CategoryService {

	public List<Map<String, Object>> getAllCategory();

	public String addCategory(Category category);

	public void updateCategory(Category category);

	public Map<String, Object> getCategoryByID(int id);

	public List<Map<String, Object>> CountCategory();
}
