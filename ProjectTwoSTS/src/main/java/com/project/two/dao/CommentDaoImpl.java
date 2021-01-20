package com.project.two.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.two.model.Comment;
import com.project.two.model.Post;

@Repository("commentDao")
@Transactional
public class CommentDaoImpl implements CommentDao {

	private SessionFactory sesFac;

	@Autowired
	public void setSesFac(SessionFactory sesFac) {
		this.sesFac = sesFac;
	}

	@Override
	public int insert(Comment post) {
		return ((Integer)sesFac.getCurrentSession().save(post)).intValue();
	}

	@Override
	public void update(Comment post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Comment post) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> selectByPost(int post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> selectByPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}
