package com.project.two.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.two.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sesFac;

	@Autowired
	public void setSesFac(SessionFactory sesFac) {
		this.sesFac = sesFac;
	}

	@Override
	public int insert(User user) {
		Session ses = sesFac.getCurrentSession();
		if (ses.createQuery("SELECT 1 FROM User where uname = '" + user.getUname() + "'").uniqueResult() != null)
			throw new IllegalArgumentException("duplicate uname violates unique constraint");
		if (ses.createQuery("SELECT 1 FROM User where email = '" + user.getEmail() + "'").uniqueResult() != null)
			throw new IllegalArgumentException("duplicate email violates unique constraint");
		return ((Integer)ses.save(user)).intValue();
	}

	@Override
	public void update(User user) {
		Session ses = sesFac.getCurrentSession();
		if (ses.createQuery("SELECT 1 FROM User where id = " + user.getId()).uniqueResult() == null)
			throw new IllegalArgumentException("no user with id " + user.getId());
		ses.update(user);
	}

	@Override
	public void delete(User user) {
		sesFac.getCurrentSession().delete(user);
	}

	@Override
	public User selectById(int id) {
		return sesFac.getCurrentSession().get(User.class, id);
	}

	@Override
	public User selectByUname(String uname) {
		// TODO: this is broken
		return sesFac.getCurrentSession().createQuery("FROM User WHERE uname = '" + uname + "'", User.class).uniqueResult();
	}

	@Override
	public List<User> selectAll() {
		return sesFac.getCurrentSession().createQuery("FROM User", User.class).list();
	}

}
