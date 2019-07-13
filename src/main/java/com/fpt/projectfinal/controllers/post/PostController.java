package com.fpt.projectfinal.controllers.post;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.services.post.PostService;
//
//@CrossOrigin
//@RestController
public class PostController {

//	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
//
//	@Autowired
//	PostService postService;
//
//	@RequestMapping(value = "/getAllPost", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
//	@ResponseBody
//	public ResponseEntity<Object> getAllPost() {
//		try {
//			List<Post> list = postService.getAllPost();
//			return new ResponseEntity<>(list, HttpStatus.FOUND);
//		} catch (NullPointerException e) {
//			logger.warn(e.getMessage(), e);
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@RequestMapping(value = "/addPost", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Object> addPost(@RequestBody Post payload) {
//
//		try {
//
//			postService.addPost(payload);
//			return new ResponseEntity<>("Successful", HttpStatus.CREATED);
//		} catch (NullPointerException e) {
//			logger.warn(e.getMessage(), e);
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@RequestMapping(value = "/updatePost", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Object> updatePost(@RequestBody Post payload) {
//
//		try {
//			postService.updatePost(payload);
//			return new ResponseEntity<>("Successful", HttpStatus.CREATED);
//		} catch (NullPointerException e) {
//			logger.warn(e.getMessage(), e);
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@RequestMapping(value = "/getAllPostByCategory/{categoryID}", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE })
//	@ResponseBody
//	public ResponseEntity<Object> getAllPostByCategory(@PathVariable(value = "categoryID") String categoryID) {
//		try {
//			List<Post> list = postService.getPostByCategoryId(categoryID);
//			return new ResponseEntity<>(list, HttpStatus.FOUND);
//		} catch (NullPointerException e) {
//			logger.warn(e.getMessage(), e);
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
