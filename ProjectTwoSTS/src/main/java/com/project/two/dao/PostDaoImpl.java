package com.project.two.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.two.model.Post;
import com.project.two.model.User;

@Repository("postDao")
@Transactional
public class PostDaoImpl implements PostDao {
	
	private SessionFactory sesFac;

	@Autowired
	public void setSesFac(SessionFactory sesFac) {
		this.sesFac = sesFac;
	}

	@Override
	public int insert(Post post) {
		return ((Integer)sesFac.getCurrentSession().save(post)).intValue();
	}

	@Override
	public void update(Post post) {
		Session ses = sesFac.getCurrentSession();
		if (ses.createQuery("SELECT 1 FROM Post where id = " + post.getId()).uniqueResult() == null)
			throw new IllegalArgumentException("no post with id " + post.getId());
		ses.update(post);
	}

	@Override
	public void delete(Post post) {
		// TODO Auto-generated method stub
		sesFac.getCurrentSession().delete(post);
	}

	@Override
	public Post selectById(int id) {
		return sesFac.getCurrentSession().get(Post.class, id);
	}
	
	@Override
	public List<Post> selectByUser(int user) {
		return sesFac.getCurrentSession().createQuery("FROM Post WHERE user_id = " + user, Post.class).list();
	}

	@Override
	public List<Post> selectByUser(User user) {
		return selectByUser(user.getId());
	}

	@Override
	public List<Post> selectAll() {
		return sesFac.getCurrentSession().createQuery("FROM Post", Post.class).list();
	}

	@Override
	public List<Post> selectAllLimit(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
