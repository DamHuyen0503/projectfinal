package com.fpt.projectfinal.daos.category;

import java.util.List;

import com.fpt.projectfinal.models.Category;

public interface CategoryDao {
	
	public List<Category> getAllCategory();
	
	public void addCategory(Category category);

	public void updateCategory(Category category);
	
	public Category getCategoryByID(int id);
	
	public List<Category> CountCategory();
}
