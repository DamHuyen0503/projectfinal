package com.fpt.projectfinal.daos.post;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;

public interface PostDao {

	public void addPost(Post post);
	
	public void updatePost(Post post);
	
	public Post getPostById(int id);
	
	public List<Post> getAllPost();
	
	public Long getCountPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	public List<Post> getPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	public List<Post> filterByTitle(String filter);
	
	public List<Post> getPostByCategory(Category category); 
	
	public List<Post> getAllDraft();
	
	public List<Post> getPostsByTagID(Integer tagID, Integer page);
}