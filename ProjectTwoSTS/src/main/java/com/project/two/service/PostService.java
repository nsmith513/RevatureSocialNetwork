package com.project.two.service;

import java.util.List;

import com.project.two.model.Post;
import com.project.two.model.User;

public interface PostService {
	
	public int insertPost(Post post);
	public void updatePost(Post post);
	public void deletePost(Post post);
	
	public Post getPostById(int id);
	public List<Post> getPostByUser(int user);
	public List<Post> getPostByUser(User user);
	
	public List<Post> getAllPost();
	public List<Post> getPostWithLimit(int limit);
}
