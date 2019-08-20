package com.fpt.projectfinal.daos.category;

import java.util.List;

import com.fpt.projectfinal.models.Category;

public interface CategoryDao {
	/*
	 * Get all information category. 
	 * すべての情報カテゴリを取得します。
	 */
	public List<Category> getAllCategory();
	
	/*
	 * Create a new category.
	 * 新しいカテゴリを作成します。
	 */
	public void addCategory(Category category);


	/*
	 * Update information category.
	 * 情報カテゴリを更新します。
	 */
	public void updateCategory(Category category);
	
	/*
	 * Get information category by category ID.
	 * カテゴリIDで情報カテゴリを取得します。
	 */
	public Category getCategoryByID(int id);
	
	/*
	 * Count sum category.
	 * 合計カテゴリをカウントします。
	 */
	public List<Category> CountCategory();
	
	public List<Category> CountPostByCategory();
}
