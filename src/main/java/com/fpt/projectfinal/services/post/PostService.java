package com.fpt.projectfinal.services.post;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;


public interface PostService {

	public String addPost(Map<String, Object> payload);
	
	public String updatePost(Map<String, Object> payload);

	public Map<String, Object> getPostById(int id);

	public List<Post> getAllPost();

	public Long getCountPostsByTagID(Integer tagID, Integer page);
	
	public List<Post> getPostsByTagID(Integer tagID, Integer page);
	
	
	public Long getCountPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString);

	public List<Post> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString);

	public List<Post> filterByTitle(String filter);

	public List<Post> getPostByCategory(int category);

	public List<Post> getAllDraft();
	
	public List<Post> getPostNew();
}
