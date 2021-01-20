package com.project.two.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.two.dao.PostDao;
import com.project.two.model.Post;
import com.project.two.model.User;

@Service("postServe")
public class PostServiceImpl implements PostService {
	
	private PostDao postDao;

	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public int insertPost(Post post) {
		return postDao.insert(post);
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		postDao.update(post);
	}

	@Override
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		postDao.delete(post);
	}

	@Override
	public Post getPostById(int id) {
		return postDao.selectById(id);
	}

	@Override
	public List<Post> getPostByUser(int user) {
		// TODO Auto-generated method stub
		List<Post> posts = postDao.selectByUser(user);
		return posts;
	}

	@Override
	public List<Post> getPostByUser(User user) {
		// TODO Auto-generated method stub
		List<Post> posts = postDao.selectByUser(user);
		return posts;
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		List<Post> posts = postDao.selectAll();
		return posts;
	}

	@Override
	public List<Post> getPostWithLimit(int limit) {
		// TODO Auto-generated method stub
		List<Post> posts = postDao.selectAllLimit(limit);
		return posts;
	}

}
