package com.fpt.projectfinal.services.post;

import java.util.List;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;

public interface PostService {

public void addPost(Post post);
	
	public void updatePost(Post post);
	
	public Post getPostById(int id);
	
	public List<Post> getAllPost();
	
	public Long getCountPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	public List<Post> getPostDataForTable(String sort,String order,Integer page,Integer categoryID,String searchString);
	
	public List<Post> filterByTitle(String filter);
	
	public List<Post> getPostByCategory(int category); 
	
	public List<Post> getAllDraft();
}
