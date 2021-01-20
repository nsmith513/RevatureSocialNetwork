package com.project.two.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.two.dao.CommentDao;
import com.project.two.model.Comment;

@Service("commentServe")
public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao;
	
	@Autowired
	public void setImageLinkDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	@Override
	public int insertComment(Comment comment) {
		return commentDao.insert(comment);
	}

}
