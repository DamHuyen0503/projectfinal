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

	
	/*
	 * Get all the information of the post.
	 * 投稿のすべての情報を取得します。
	 */
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
	
	/*
	 * Get all the information of the draft.
	 * 下書きのすべての情報を取得します。
	 */
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

	
	/*
	 * Create a new post.
	 * 新しい投稿を作成します。
	 */
	@RequestMapping(value = "/addPost", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> addPost(@RequestBody  Map<String, Object> payload) {

		try {

		
			return new ResponseEntity<>(postService.addPost(payload), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/*
	 * Update post information.
	 * 投稿情報を更新します。
	 */
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

	/*
	 * Get all the post by category.
	 * カテゴリごとにすべての投稿を取得します。
	 */
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
	
	/*
	 * Filter posts by title.
	 * タイトルで投稿をフィルタリングします。
	 */
	@RequestMapping(value = "/filterByTitle/{title},{categoryID}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Post> filterByName(@PathVariable(value = "title") String title, @PathVariable(value = "categoryID") Integer cagetoryID) {
		List<Post> list =postService.filterByTitle(title);
		
		return list;
	}
	
	/*
	 * 
	 * Get the details of the post.
	 * 投稿の詳細を取得します。
	 */
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
	
	/*
	 * Count all posts with tag ID.
	 * タグIDを持つすべての投稿をカウントします。
	 */
	@RequestMapping(value = "/getPostsByTagID", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Object> getPostsByTagID(@RequestParam Integer tagID,  int page) {
		Map<String, Object> tags = new HashMap<String, Object>();
		tags.put("count", postService.getCountPostsByTagID(tagID, page));
		tags.put("listPost", postService.getPostsByTagID(tagID, page));
		
		return tags;
	}
	
	/*
	 * Get the latest post.
	 * 最新の投稿を入手してください。
	 */
	@RequestMapping(value = "/getPostNew", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getPostNew() {
		try {
			
			return new ResponseEntity<>(postService.getPostNew(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Count the total number of posts.
	 * 投稿の総数を数えます。
	 */
	@RequestMapping(value = "/getNumberOfPost", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getNumberOfPost() {
		try {
			Long count = postService.getNumberOfPost();
		
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getPostsByString", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllPost(@RequestParam String stringSearch, int page) {
		try {
			
			return new ResponseEntity<>(postService.getPostsByString(stringSearch, page), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getPostsByCategory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getPostByCategory(@RequestParam int categoryID, int page) {
		try {
			
			return new ResponseEntity<>(postService.getPostsByCategory(categoryID, page), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(value = "/getPostsByAuthor", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getPostsByAuthor(@RequestParam int userID, int page) {
		try {
			
			return new ResponseEntity<>(postService.getPostsByAuthor(userID, page), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
