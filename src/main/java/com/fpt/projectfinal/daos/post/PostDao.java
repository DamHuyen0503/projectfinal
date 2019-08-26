package com.fpt.projectfinal.daos.post;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;

public interface PostDao {

	/*
	 * Create a new post.
	 * 新しい投稿を作成します。
	 */
	public void addPost(Post post);
	

	/*
	 * Update post information.
	 * 投稿情報を更新します。
	 */
	public void updatePost(Post post);
	
	/*
	 * 
	 * Get the details of the post.
	 * 投稿の詳細を取得します。
	 */
	public Post getPostById(int id);
	
	
	/*
	 * Get all the information of the post.
	 * 投稿のすべての情報を取得します。
	 */
	public List<Post> getAllPost();
	
	public Long getCountPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	public List<Post> getPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	/*
	 * Filter posts by title.
	 * タイトルで投稿をフィルタリングします。
	 */
	public List<Post> filterByTitle(String filter);
	
	/*
	 * Get all the post by category.
	 * カテゴリごとにすべての投稿を取得します。
	 */
	public List<Post> getPostByCategory(Category category); 
	
	/*
	 * Get all the information of the draft.
	 * 下書きのすべての情報を取得します。
	 */
	public List<Post> getAllDraft();
	
	/*
	 * Count all posts with tag ID.
	 * タグIDを持つすべての投稿をカウントします。
	 */
	public Long getCountPostsByTagID(Integer tagID, Integer page);
	
	public List<Post> getPostsByTag(Integer tagID, Integer page);
	
	/*
	 * Get the latest post.
	 * 最新の投稿を入手してください。
	 */
	public List<Post> getPostNew();
	
	/*
	 * Count the total number of posts.
	 * 投稿の総数を数えます。
	 */
	public Long getNumberOfPost();
	
	
	public Map<String , Object> getPostsByString(String stringSearch, int page);
	
	public Map<String , Object> getPostsByCategory(int categoryID, int page);
	
	public Map<String , Object> getPostsByAuthor(int userID, int page);
}