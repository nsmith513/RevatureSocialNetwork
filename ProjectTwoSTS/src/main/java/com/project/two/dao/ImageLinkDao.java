package com.project.two.dao;

import java.util.List;

import com.project.two.model.ImageLink;
import com.project.two.model.Post;

public interface ImageLinkDao {
	
	/**
	 * Insert a new image link into the database.
	 * 
	 * @param post - Image link to insert.
	 * @return - ID of the image link inserted.
	 */
	public int insert(ImageLink post);
	/**
	 * Update an image link in the database.
	 * 
	 * @param post - Image link to update.
	 */
	public void update(ImageLink post);
	/**
	 * Delete an image link from the database.
	 * 
	 * @param post - Image link to delete.
	 */
	public void delete(ImageLink post);
	
	/**
	 * Select all image links belonging to a particular post.
	 * 
	 * @param post - ID of the post to select image link of.
	 * @return List of image links belonging to {@code post}
	 */
	public List<ImageLink> selectByPost(int post);
	/**
	 * Select all image links belonging to a particular post.
	 * 
	 * @param post - Post to select image links of.
	 * @return List of image links belonging to {@code post}
	 */
	public List<ImageLink> selectByPost(Post post);
	
}
