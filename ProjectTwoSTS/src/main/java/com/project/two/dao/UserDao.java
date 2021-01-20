package com.project.two.dao;

import java.util.List;

import com.project.two.model.User;

public interface UserDao {

	/**
	 * Insert a new user into the database.
	 * 
	 * @param user - User to insert.
	 * @return The ID of the user inserted.
	 */
	public int insert(User user);
	/**
	 * Update a user in the database.
	 * 
	 * @param user - User to update.
	 */
	public void update(User user);
	/**
	 * Delete a user from the database.
	 * 
	 * @param user - User to delete.
	 */
	public void delete(User user);
	
	/**
	 * Get a user from the database.
	 * 
	 * @param id - User ID to select by.
	 * @return User with ID {@code id}.
	 */
	public User selectById(int id);
	/**
	 * Get a user from the database.
	 * 
	 * @param uname - Username to select by.
	 * @return User with username {@code uname}.
	 */
	public User selectByUname(String uname);
	
	/**
	 * @return A list of all users in the database.
	 */
	public List<User> selectAll();

}
