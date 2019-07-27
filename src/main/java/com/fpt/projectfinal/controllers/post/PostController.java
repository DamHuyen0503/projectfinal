package com.fpt.projectfinal.controllers.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.services.post.PostService;
import com.fpt.projectfinal.services.tag.TagService;
//
@CrossOrigin
@RestController
public class PostController {

	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	PostService postService;
	
	@Autowired
	TagService tagService;

	@RequestMapping(value = "/getAllPost", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllPost() {
		try {
			List<Post> list = postService.getAllPost();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/allDraftPost", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<HashMap<String,Object>> getallDraftPost() {
		List<Post> list =postService.getAllDraft();
		List<HashMap<String, Object>> out = new ArrayList<>();
		for (Post post : list) {
			int check = 0;
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("postID", post.getPostID());
			hm.put("title", post.getTitle());
			hm.put("createdDate", post.getCreatedDate());
			hm.put("status", post.getStatus());
			hm.put("image",post.getImage());
			
		
			out.add(hm);
		}
		return out;
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> addPost(@RequestBody  Map<String, Object> payload) {

		try {

		
			return new ResponseEntity<>(	postService.addPost(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updatePost", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updatePost(@RequestBody Map<String, Object> payload) {

		try {
			
			return new ResponseEntity<>(postService.updatePost(payload), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getAllPostByCategory/{categoryID}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllPostByCategory(@PathVariable(value = "categoryID") String categoryID) {
		try {
			List<Post> list = postService.getPostByCategory(Integer.parseInt(categoryID));
			return new ResponseEntity<>(list, HttpStatus.FOUND);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/filterByTitle/{title},{categoryID}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Post> filterByName(@PathVariable(value = "title") String title, @PathVariable(value = "categoryID") Integer cagetoryID) {
		List<Post> list =postService.filterByTitle(title);
		
		return list;
	}
	
	@RequestMapping(value = "/getDetailPost/{postID}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Object> getDetailPost(@PathVariable(value = "postID") Integer postID ) {
		Map<String, Object> list =postService.getPostById(postID);
		return list;
	}
	
	@RequestMapping(value = "/getPostDataForTable",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> getPostDataForTable(@RequestParam String sort,@RequestParam String order, 
			@RequestParam Integer page, @RequestParam int categoryID, @RequestParam String searchString) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", postService.getCountPostDataForTable(sort, order, page,categoryID,searchString));
		result.put("listPost", postService.getPostDataForTable(sort, order, page,categoryID,searchString));
				//return searchString;//trả về list Post theo điều kiện sort by cái gì, sắp xếp theo chiều order gửi về , ở trang bao nhiêu, từ category nào và search bằng từ khóa n
		return result;
	}
	
	@RequestMapping(value = "/getPostsByTagID", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Post> getPostsByTagID(@RequestParam Integer tagID, @RequestParam Integer page) {
		List<Post> list = postService.getPostsByTagID(tagID,page);
		return list;
	}
	
	

}
