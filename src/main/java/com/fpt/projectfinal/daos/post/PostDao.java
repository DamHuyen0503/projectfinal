package com.fpt.projectfinal.daos.post;

import java.util.List;

import com.fpt.projectfinal.models.Post;

public interface PostDao {

	public int addPost(Post post);
	
	public int updatePost(Post post);
	
	public Post getPostById(int id);
	
	public List<Post> getAllPost();
}