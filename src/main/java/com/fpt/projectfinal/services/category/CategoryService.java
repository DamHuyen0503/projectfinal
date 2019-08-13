package com.fpt.projectfinal.services.category;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;

public interface CategoryService {

	
	 /*
	 * Get all information category. 
	 * すべての情報カテゴリを取得します。
	 */
	 
	public List<Map<String, Object>> getAllCategory();

	/*
	 * Create a new category.
	 * 新しいカテゴリを作成します。
	 */
	public String addCategory(Category category);

	

	/*
	 * Update information category.
	 * 情報カテゴリを更新します。
	 */
	public void updateCategory(Category category);

	
	/*
	 * Get information category by category ID.
	 * カテゴリIDで情報カテゴリを取得します。
	 */
	public Map<String, Object> getCategoryByID(int id);

	/*
	 * Count sum category.
	 * 合計カテゴリをカウントします。
	 */
	public List<Map<String, Object>> CountCategory();
}
