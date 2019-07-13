package com.fpt.projectfinal.services.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.post.PostDao;
import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public void addPost(Post post) {
		postDao.addPost(post);

	}

	@Override
	public void updatePost(Post post) {
		postDao.updatePost(post);

	}

	@Override
	public Post getPostById(int id) {
		return postDao.getPostById(id);
	}

	@Override
	public List<Post> getAllPost() {
		List<Post> list =  postDao.getAllPost();
		for(Post p:list) {
			p.setTag(tagDao.getTagByPost(p));
		}
		return list;
	}

	@Override
	public Long getCountPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		return postDao.getCountPostDataForTable(sort, order, page, categoryID, searchString);
	}

	@Override
	public List<Post> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		return postDao.getPostDataForTable(sort, order, page, categoryID, searchString);
	}

	@Override
	public List<Post> filterByTitle(String filter) {
		return postDao.filterByTitle(filter);
	}

	@Override
	public List<Post> getPostByCategory(int cate) {
		Category category = categoryDao.getCategoryByID(cate);
		if(category == null) {
			return null;
		}
		List<Post> list = postDao.getPostByCategory(category);
		for(Post p:list) {
			p.setTag(tagDao.getTagByPost(p));
			p.setCategory(category);
			
		}
		return list;
	}

	@Override
	public List<Post> getAllDraft() {
		return postDao.getAllDraft();
	}

}
