package com.project.two.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.two.dao.ImageLinkDao;
import com.project.two.model.ImageLink;
import com.project.two.model.Post;

@Service("imageLinkServe")
public class ImageLinkServiceImpl implements ImageLinkService {
	
	private ImageLinkDao imagelinkdao;
	
	@Autowired
	public void setImageLinkDao(ImageLinkDao imagelinkdao) {
		this.imagelinkdao = imagelinkdao;
	}

	@Override
	public void insertImage(ImageLink imagelink) {
		// TODO Auto-generated method stub
		imagelinkdao.insert(imagelink);
	}

	@Override
	public void updateImage(ImageLink imagelink) {
		// TODO Auto-generated method stub
		imagelinkdao.update(imagelink);
	}

	@Override
	public void deleteImage(ImageLink imagelink) {
		// TODO Auto-generated method stub
		imagelinkdao.delete(imagelink);
	}

	@Override
	public List<ImageLink> selectImageByPost(int post_id) {
		// TODO Auto-generated method stub
		List<ImageLink> imagelinks = imagelinkdao.selectByPost(post_id);
		return imagelinks;
	}

	@Override
	public List<ImageLink> selectImageByPost(Post post) {
		// TODO Auto-generated method stub
		List<ImageLink> imagelinks = imagelinkdao.selectByPost(post);
		return imagelinks;
	}

}
