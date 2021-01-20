package com.project.two.dao;

import java.util.List;

import com.project.two.model.Post;
import com.project.two.model.User;

public interface PostDao {
	
	/**
	 * Insert a new post into the database.
	 * 
	 * @param post - Post to insert.
	 * @return The ID of the post inserted.
	 */
	public int insert(Post post);
	/**
	 * Update a post in the database.
	 * 
	 * @param post - Post to update.
	 */
	public void update(Post post);
	/**
	 * Delete a post from the database.
	 * 
	 * @param post - Post to delete.
	 */
	public void delete(Post post);
	
	public Post selectById(int id);
	/**
	 * Select all posts belonging to a particular user.
	 * 
	 * @param user - ID of the user to select posts of.
	 * @return List of posts belonging to {@code user}
	 */
	public List<Post> selectByUser(int user);
	/**
	 * Select all posts belonging to a particular user.
	 * 
	 * @param user - User to select posts of.
	 * @return List of posts belonging to {@code user}
	 */
	public List<Post> selectByUser(User user);
	
	/**
	 * @return A list of all posts in the database.
	 */
	public List<Post> selectAll();
	/**
	 * Returns a list of all posts in the database up to a given limit.
	 * 
	 * @param limit - Limit to set for returned list.
	 * @return A list of {@code limit} posts in the database.
	 */
	public List<Post> selectAllLimit(int limit);
	
}
