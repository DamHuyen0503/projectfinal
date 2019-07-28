package com.fpt.projectfinal.services.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.tag.TagDao;
import com.fpt.projectfinal.models.Tag;
@Service
public class TagServiceImpl  implements TagService {
	@Autowired
	TagDao tagDao;

	@Override
	public List<Tag> getAllTag() {
		return tagDao.getAllTag();

	}
}
