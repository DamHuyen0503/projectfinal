package com.fpt.projectfinal.services.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
import com.fpt.projectfinal.models.User;

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
		Category category = null;
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Post post = new Post();
		if (username == null) {
			return "token fail";
		}
		
		if (payload.get("postID") == null) {
			return "postID null";
		}
		if (payload.get("title").toString().length() >255) {
			return "title more than 255";
		}
		if (payload.get("description").toString().length() >255) {
			return "description more than 255";
		}
		if (payload.get("image").toString().length() >255) {
			return "image more than 255";
		}
		if (payload.get("categoryID") != null) {
			category = categoryDao.getCategoryByID((int)payload.get("categoryID"));
		}
		try {
			Account acc = accountDao.getAccountByEmail(username);
			
		
		
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
			@SuppressWarnings("unchecked")
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
		}catch (Exception e) {
			e.getMessage();
			return "update fail";
		}
		
	
	}

	@Override
	public Map<String, Object> getPostById(int id) {
		Post post = postDao.getPostById(id);
		
		post.setTags(tagDao.getTagByPost(post));
		Map<String, Object> map = new HashMap<>();
		map.put("postID", post.getPostID());
		map.put("content",post.getContent());
		map.put("createdDate",post.getCreatedDate());
		map.put("description",post.getDescription());
		map.put("image",post.getImage());
		map.put("status",post.getStatus());
		map.put("title",post.getTitle());
		Map<String, Object> mapCate = new HashMap<>();
		mapCate.put("categoryID", post.getCategory().getCategoryID());
		mapCate.put("name", post.getCategory().getName());
		
		map.put("category", mapCate);
		map.put("modifiedDate",post.getModifiedDate());
		User user = post.getUser();
		
		Map<String, Object> u = new HashMap<>();
		u.put("address", user.getAddress());
		u.put("avatar", user.getAvatar());
		u.put("createdDate", user.getCreatedDate());
		u.put("dob", user.getDOB());
		u.put("firstName", user.getFirstName());
		u.put("lastName", user.getLastName());
		u.put("phoneNumber", user.getPhoneNumber());
		map.put("user", u);
		List<String> tags = new ArrayList();
		for (Tag tag : post.getTags()) {
			tags.add(tag.getContent());
		}
		map.put("tags", tags);
		return map;
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
	public List<Map<String, Object>> getPostDataForTable(String sort, String order, Integer page, Integer categoryID,
			String searchString) {
		
	 List<Post> list = postDao.getPostDataForTable(sort, order, page, categoryID, searchString);
	 
	 List<Map<String, Object>> listPost = new ArrayList<>();
		
		for (Post post : list) {
			Map<String, Object> result = new HashMap<>();
			result.put("postID", post.getPostID());
			result.put("content", post.getContent());
			result.put("createdDate", post.getCreatedDate());
			result.put("discription", post.getDescription());
			result.put("image", post.getImage());
			result.put("modifiedDate", post.getModifiedDate());
			result.put("status", post.getStatus());
			result.put("title", post.getTitle());
			Set<Tag> tags = tagDao.getTagByPost(post);
			result.put("tags", tags);
			Map<String, Object> mapCate = new HashMap<>();
			mapCate.put("categoryID", post.getCategory().getCategoryID());
			mapCate.put("name", post.getCategory().getName());
			result.put("category", mapCate );
			listPost.add(result);
		}
		return listPost;
	 
	 
	 
	
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

	@SuppressWarnings("unchecked")
	@Override
	public String addPost(Map<String, Object> payload) {
		Category category = null;
		if (payload.get("status") == null) {
			return "status null";
		}
		if ((int)payload.get("status") <0 || (int) payload.get("status")>4) {
			return "status not valid";
		}
		if (payload.get("title").toString().length() >255) {
			return "title more than 255";
		}
		if (payload.get("description").toString().length() >255) {
			return "description more than 255";
		}
		if (payload.get("image").toString().length() >255) {
			return "image more than 255";
		}
		if (payload.get("categoryID") != null) {
			category = categoryDao.getCategoryByID((int)payload.get("categoryID"));
		}
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Account acc = accountDao.getAccountByEmail(username);
			
			
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
		} catch (Exception e) {
			e.getMessage();
			return "add fail";
		}
		
	}
	
	@Override
	public Long getCountPostsByTagID(Integer tagID, Integer page) {

		return postDao.getCountPostsByTagID(tagID, page);
	}

	@Override
	public List<Map<String, Object>> getPostsByTagID(Integer tagID, Integer page) {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Post> listPost = postDao.getPostsByTagID(tagID, page);
		for (Post post : listPost) {
			Map<String, Object> mapPost = new HashMap<>();
			mapPost.put("content", post.getContent());
			mapPost.put("createdDate", post.getCreatedDate());
			mapPost.put("discription", post.getDescription());
			mapPost.put("image", post.getImage());
			mapPost.put("modifiedDate", post.getModifiedDate());
			mapPost.put("postID", post.getPostID());
			mapPost.put("status", post.getStatus());
			mapPost.put("title", post.getTitle());
			Set<Tag> setTag = tagDao.getTagByPost(post);
			List<Map<String, Object>> listTag = new ArrayList<>();
			for (Tag tag : setTag) {
				Map<String, Object> mapTag = new HashMap<>();
				mapTag.put("content", tag.getContent());
				mapTag.put("createdDate", tag.getCreatedDate());
				mapTag.put("tagID", tag.getTagID());
				listTag.add(mapTag);
			}
			mapPost.put("tags", listTag);
			result.add(mapPost);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getPostNew() {
		List<Map<String, Object>> listPostNew = new ArrayList<>();
		
		List<Post> listPost = postDao.getPostNew();
		for (Post post : listPost) {
			Map<String, Object> result = new HashMap<>();
			result.put("postID", post.getPostID());
			result.put("content", post.getContent());
			result.put("createdDate", post.getCreatedDate());
			result.put("discription", post.getDescription());
			result.put("image", post.getImage());
			result.put("modifiedDate", post.getModifiedDate());
			result.put("status", post.getStatus());
			result.put("title", post.getTitle());
			Set<Tag> tags = tagDao.getTagByPost(post);
			result.put("tags", tags);
			result.put("categoryID", post.getCategory().getCategoryID());
			listPostNew.add(result);
		}
		return listPostNew;
	}

	@Override
	public Long getNumberOfPost() {
		return postDao.getNumberOfPost();
	}

	@Override
	public Map<String, Object> getPostsByString(String stringSearch, int page) {
		Map<String, Object> resultPost = postDao.getPostsByString(stringSearch, page);
		List<Post> listPost = (List<Post>) resultPost.get("Post");
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> mapPost = new HashMap<>();
		result.put("count", resultPost.get("count"));
		List<Object> postList = new ArrayList<>();
		for (Post post : listPost) {
			mapPost = new HashMap<>();
			mapPost.put("postID", post.getPostID());
			mapPost.put("content", post.getContent());
			mapPost.put("createdDate", post.getCreatedDate());
			mapPost.put("description", post.getDescription());
			mapPost.put("image", post.getImage());
			mapPost.put("modifiedDate", post.getModifiedDate());
			mapPost.put("status", post.getStatus());
			mapPost.put("title", post.getTitle());
			mapPost.put("categoryID", post.getCategory().getCategoryID());
			Set<Tag> tags = post.getTags();
			mapPost.put("tags", tags);
			postList.add(mapPost);
		}
		result.put("listPost", postList);
		return result;
	}

	@Override
	public Map<String, Object> getPostsByCategory(int categoryID, int page) {
		Map<String, Object> resultPost = postDao.getPostsByCategory(categoryID, page);
		List<Post> listPost = (List<Post>) resultPost.get("Post");
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> mapPost = new HashMap<>();
		result.put("count", resultPost.get("count"));
		List<Object> postList = new ArrayList<>();
		for (Post post : listPost) {
			mapPost = new HashMap<>();
			mapPost.put("postID", post.getPostID());
			mapPost.put("content", post.getContent());
			mapPost.put("createdDate", post.getCreatedDate());
			mapPost.put("description", post.getDescription());
			mapPost.put("image", post.getImage());
			mapPost.put("modifiedDate", post.getModifiedDate());
			mapPost.put("status", post.getStatus());
			mapPost.put("title", post.getTitle());
			mapPost.put("categoryID", post.getCategory().getCategoryID());
			Set<Tag> tags = post.getTags();
			mapPost.put("tags", tags);
			postList.add(mapPost);
		}
		result.put("listPost", postList);
		return result;
	}

	@Override
	public Map<String, Object> getPostsByAuthor(int userID, int page) {
		Map<String, Object> resultPost = postDao.getPostsByAuthor(userID, page);
		List<Post> listPost = (List<Post>) resultPost.get("Post");
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> mapPost = new HashMap<>();
		result.put("count", resultPost.get("count"));
		List<Object> postList = new ArrayList<>();
		for (Post post : listPost) {
			mapPost = new HashMap<>();
			mapPost.put("postID", post.getPostID());
			mapPost.put("content", post.getContent());
			mapPost.put("createdDate", post.getCreatedDate());
			mapPost.put("description", post.getDescription());
			mapPost.put("image", post.getImage());
			mapPost.put("modifiedDate", post.getModifiedDate());
			mapPost.put("status", post.getStatus());
			mapPost.put("title", post.getTitle());
			mapPost.put("categoryID", post.getCategory().getCategoryID());
			Set<Tag> tags = post.getTags();
			mapPost.put("tags", tags);
			postList.add(mapPost);
		}
		result.put("listPost", postList);
		return result;
	}


}
