package com.project.two.service;

import java.util.List;

import com.project.two.model.User;

public interface UserService {
	
	/**
	 * @see com.project.two.dao.UserDao#insert(User user)
	 */
	public int insertUser(User user);
	/**
	 * @see com.project.two.dao.UserDao#update(User user)
	 */
	public void updateUser(User user);
	/**
	 * @see com.project.two.dao.UserDao#delete(User user)
	 */
	public void deleteUser(User user);

	/**
	 * @see com.project.two.dao.UserDao#selectById(int id)
	 */
	public User getUserById(int user_id);
	/**
	 * @see com.project.two.dao.UserDao#selectByUname(String uname)
	 */
	public User getUserByName(String username);

	/**
	 * @see com.project.two.dao.UserDao#selectAll()
	 */
	public List<User> getAllUsers();

}
