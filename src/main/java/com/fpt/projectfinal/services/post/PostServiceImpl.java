package com.fpt.projectfinal.services.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.account.AccountDao;
import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.post.PostDao;
import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.models.Account;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	CategoryDao categoryDao;


	@Autowired
	AccountDao accountDao;
	
	@Override
	public String updatePost(Map<String, Object> payload) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acc = accountDao.getAccountByEmail(username);
		
		Category category = categoryDao.getCategoryByID((int)payload.get("categoryID"));
		Post post = new Post();
		post.setUser(acc.getUser());
		post.setModifiedDate(new Date());
		post.setPostID((int) payload.get("postID"));
		post.setTitle((String) payload.get("title"));
		post.setCategory(category);
		post.setContent((String) payload.get("content"));
		post.setImage((String) payload.get("image"));
		post.setDescription((String) payload.get("description"));
		post.setStatus((int)payload.get("status"));
		
		
		List<Tag> listTag = tagDao.getAllTag();
		Set<Tag> tags = new HashSet<>();
		ArrayList<String> tagObjs = (ArrayList<String>) payload.get("tags");
		for (String obj : tagObjs) {
			boolean exist = false;
			for (Tag tag : listTag) {
				if (obj.equalsIgnoreCase(tag.getContent())) {
					tags.add(tag);
					exist = true;
					break;
				}
			}
			if (exist) {
				continue;
			}
			tags.add(new Tag(obj, new Date()));
		}
		post.setTags(tags);
		
		postDao.updatePost(post);
		return "successful";
	
	}

	@Override
	public Post getPostById(int id) {
		Post p = postDao.getPostById(id);
		p.setTags(tagDao.getTagByPost(p));
		return p;
	}

	@Override
	public List<Post> getAllPost() {
		List<Post> list =  postDao.getAllPost();
		for(Post p:list) {
			p.setTags(tagDao.getTagByPost(p));
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
	 List<Post> list = postDao.getPostDataForTable(sort, order, page, categoryID, searchString);
	 for(Post p : list) {
		 p.setTags(tagDao.getTagByPost(p));
	 }
	 return list;
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
			p.setTags(tagDao.getTagByPost(p));
			p.setCategory(category);
			
		}
		return list;
	}

	@Override
	public List<Post> getAllDraft() {
		return postDao.getAllDraft();
	}

	@Override
	public String addPost(Map<String, Object> payload) {
		if (payload.get("status") == null ) {
			return "status null";
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account acc = accountDao.getAccountByEmail(username);
		
		Category category = categoryDao.getCategoryByID((int)payload.get("categoryID"));
		Post post = new Post();
		post.setUser(acc.getUser());
		post.setCreatedDate(new Date());
		post.setTitle((String) payload.get("title"));
		post.setCategory(category);
		post.setContent((String) payload.get("content"));
		post.setImage((String) payload.get("image"));
		post.setDescription((String) payload.get("description"));
		post.setStatus((int)payload.get("status"));
		
		
		List<Tag> listTag = tagDao.getAllTag();
		Set<Tag> tags = new HashSet<>();
		ArrayList<String> tagObjs = (ArrayList<String>) payload.get("tags");
		for (String obj : tagObjs) {
			boolean exist = false;
			for (Tag tag : listTag) {
				if (obj.equalsIgnoreCase(tag.getContent())) {
					tags.add(tag);
					exist = true;
					break;
				}
			}
			if (exist) {
				continue;
			}
			tags.add(new Tag(obj, new Date()));
		}
		post.setTags(tags);
		
		postDao.addPost(post);
		return "successful";
	}

}
