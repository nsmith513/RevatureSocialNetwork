package com.project.two.service;

import java.util.List;

import com.project.two.model.ImageLink;
import com.project.two.model.Post;

public interface ImageLinkService {
	
	public void insertImage(ImageLink imagelink);
	public void updateImage(ImageLink imagelink);
	public void deleteImage(ImageLink imagelink);
	
	public List<ImageLink> selectImageByPost(int post_id);
	public List<ImageLink> selectImageByPost(Post post);
}
