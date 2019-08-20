package com.fpt.projectfinal.services.post;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;


public interface PostService {

	/*
	 * Create a new post.
	 * 新しい投稿を作成します。
	 */
	public String addPost(Map<String, Object> payload);
	

	/*
	 * Update post information.
	 * 投稿情報を更新します。
	 */
	public String updatePost(Map<String, Object> payload);

	/*
	 * 
	 * Get the details of the post.
	 * 投稿の詳細を取得します。
	 */
	public Map<String, Object> getPostById(int id);

	
	/*
	 * Get all the information of the post.
	 * 投稿のすべての情報を取得します。
	 */
	public List<Post> getAllPost();

	/*
	 * Count all posts with tag ID.
	 * タグIDを持つすべての投稿をカウントします。
	 */
	public Long getCountPostsByTagID(Integer tagID, Integer page);
	
	public List<Map<String, Object>> getPostsByTagID(Integer tagID, Integer page);
	
	
	public Long getCountPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString);

	public List<Map<String, Object>> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString);

	/*
	 * Filter posts by title.
	 * タイトルで投稿をフィルタリングします。
	 */
	public List<Post> filterByTitle(String filter);

	/*
	 * Get all the post by category.
	 * カテゴリごとにすべての投稿を取得します。
	 */
	public List<Post> getPostByCategory(int category);

	/*
	 * Get all the information of the draft.
	 * 下書きのすべての情報を取得します。
	 */
	public List<Post> getAllDraft();
	
	/*
	 * Get the latest post.
	 * 最新の投稿を入手してください。
	 */
	public List<Map<String, Object>> getPostNew();
	
	/*
	 * Count the total number of posts.
	 * 投稿の総数を数えます。
	 */
	public Long getNumberOfPost();
	
	
	public List<Map<String, Object>> getPostsByString (String stringSearch, int page);
	
	public List<Map<String, Object>> getPostsByCategory (int categoryID, int page);
	
	public List<Map<String, Object>> getPostsByAuthor (int userID, int page);
}
