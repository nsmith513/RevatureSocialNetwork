package com.project.two.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.two.model.ImageLink;
import com.project.two.model.Post;

@Repository("imageLinkDao")
@Transactional
public class ImageLinkDaoImpl implements ImageLinkDao {

	private SessionFactory sesFac;

	@Autowired
	public void setSesFac(SessionFactory sesFac) {
		this.sesFac = sesFac;
	}
	
	@Override
	public int insert(ImageLink post) {
		return ((Integer)sesFac.getCurrentSession().save(post)).intValue();
	}

	@Override
	public void update(ImageLink post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ImageLink post) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ImageLink> selectByPost(int post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageLink> selectByPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}
