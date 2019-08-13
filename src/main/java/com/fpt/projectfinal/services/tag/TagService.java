package com.fpt.projectfinal.services.tag;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.projectfinal.models.Tag;
public interface TagService {

	/*
	 * get all information of tag.
	 * タグのすべての情報を取得します。
	 */
	public List<Tag> getAllTag();
	
}
