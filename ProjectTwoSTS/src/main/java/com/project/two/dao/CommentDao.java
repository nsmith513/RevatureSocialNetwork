package com.project.two.dao;

import java.util.List;

import com.project.two.model.Comment;
import com.project.two.model.Post;

public interface CommentDao {

	/**
	 * Insert a new comment into the database.
	 * 
	 * @param post - Comment to insert.
	 * @return The ID of the comment inserted.
	 */
	public int insert(Comment post);
	/**
	 * Update a comment in the database.
	 * 
	 * @param post - Comment to update.
	 */
	public void update(Comment post);
	/**
	 * Delete a comment in the database.
	 * 
	 * @param post - Comment to delete.
	 */
	public void delete(Comment post);
	
	/**
	 * Select all comments belonging to a particular post.
	 * 
	 * @param post - ID of the post to select comments of.
	 * @return List of comments belonging to {@code post}
	 */
	public List<Comment> selectByPost(int post);
	/**
	 * Select all comments belonging to a particular post.
	 * 
	 * @param post - Post to select comments of.
	 * @return List of comments belonging to {@code post}
	 */
	public List<Comment> selectByPost(Post post);
	
}
