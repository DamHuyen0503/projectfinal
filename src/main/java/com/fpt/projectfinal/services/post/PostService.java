package com.fpt.projectfinal.services.post;

import java.util.List;

import com.fpt.projectfinal.models.Post;

public interface PostService {

	public void addPost(Post payload);

	public void updatePost(Post payload);

	public Post getPostById(int id);
	
	public List<Post> getPostByCategoryId(String id);

	public List<Post> getAllPost();
}
