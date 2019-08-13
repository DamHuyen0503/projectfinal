package com.fpt.projectfinal.controllers.tag;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.controllers.category.CategoryController;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.services.tag.TagService;

@CrossOrigin
@RestController
public class TagController {
	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	TagService tagService;
	
	/*
	 * get all information of tag.
	 * タグのすべての情報を取得します。
	 */
	@RequestMapping(value = "/getAllTag", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Tag> getAllAccount() {
		List<Tag> list = tagService.getAllTag();
		return list;
	}

}
