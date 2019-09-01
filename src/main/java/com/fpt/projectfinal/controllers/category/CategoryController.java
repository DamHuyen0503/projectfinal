package com.fpt.projectfinal.controllers.category;

import java.nio.charset.Charset;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.services.category.CategoryService;



@CrossOrigin
@RestController
public class CategoryController {

	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

	/*
	 * Get all information category. 
	 * すべての情報カテゴリを取得します。
	 */
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getAllCategory() {
		try {
			
			return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Create a new category.
	 * 新しいカテゴリを作成します。
	 */
	@RequestMapping(value = "/addCategory",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createCategory(@RequestBody Category category) {
		try {
			categoryService.addCategory(category);
	        return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	
	/*
	 * Update information category.
	 * 情報カテゴリを更新します。
	 */
	@RequestMapping(value = "/updateCategory",method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
		try {
			categoryService.updateCategory(category);
	        return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	
	/*
	 * Get information category by category ID.
	 * カテゴリIDで情報カテゴリを取得します。
	 */
	@RequestMapping(value = "/getCategoryByID/{categoryID}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getCategoryByID(@PathVariable(value = "categoryID") Integer categoryID ) {
		
		try {
			Map<String, Object> category =categoryService.getCategoryByID(categoryID);
	        return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Count sum category.
	 * 合計カテゴリをカウントします。
	 */
	@RequestMapping(value = "/CountCategory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getCountCategory() {
		try {
			List<Map<String, Object>> listCategory =categoryService.CountCategory();
	        return new ResponseEntity<>(listCategory, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/CountPostByCategory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Object> CountPostByCategory() {
		try {
			List<Map<String, Object>> listCategory =categoryService.CountPostByCategory();
	        return new ResponseEntity<>(listCategory, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.warn(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
