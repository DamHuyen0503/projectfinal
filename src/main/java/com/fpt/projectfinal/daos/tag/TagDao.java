package com.fpt.projectfinal.daos.tag;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;

public interface TagDao {
	
	public List<Tag> getAllTag();
	
	public Set<Tag>getTagByTest(Test test);
	public Set<Tag>getTagByPost(Post post);
}
