package com.project.two.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.two.dao.UserDao;
import com.project.two.model.User;

@Service("userServe")
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	@Override
	public User getUserById(int user_id) {
		// TODO Auto-generated method stub
		User user = userDao.selectById(user_id);
		return user;
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUname(username);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userDao.selectAll();
		return users;
	}

}
