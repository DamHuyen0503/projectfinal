package com.fpt.projectfinal.daos.tag;

import java.util.List;
import java.util.Set;

import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Tag;
import com.fpt.projectfinal.models.Test;

public interface TagDao {
	
	/*
	 * get all information of tag.
	 * タグのすべての情報を取得します。
	 */
	public List<Tag> getAllTag();
	
	/*
	 * get all information of test.
	 * テストのすべての情報を取得します。
	 */
	public Set<Tag>getTagByTest(Test test);
	
	/*
	 * get all information of post.
	 * 投稿のすべての情報を取得します。
	 */
	public Set<Tag>getTagByPost(Post post);
}
