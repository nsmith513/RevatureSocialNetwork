package com.project.two.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.two.model.Comment;
import com.project.two.model.ImageLink;
import com.project.two.model.Post;
import com.project.two.service.CommentService;
import com.project.two.service.ImageLinkService;
import com.project.two.service.PostService;
import com.project.two.service.UserService;
import com.project.two.util.Support;

@Controller
@RequestMapping("/post")
@CrossOrigin(origins="*")
public class PostController {
	
	private PostService postServe;
	private UserService userServe;
	private ImageLinkService imageLinkServe;
	private CommentService commentServe;

	@Autowired
	public void setPostServe(PostService postServe) {
		this.postServe = postServe;
	}

	@Autowired
	public void setUserServe(UserService userServe) {
		this.userServe = userServe;
	}

	@Autowired
	public void setImageLinkServe(ImageLinkService imageLinkServe) {
		this.imageLinkServe = imageLinkServe;
	}

	@Autowired
	public void setCommentServe(CommentService commentServe) {
		this.commentServe = commentServe;
	}
	
	@PostMapping(value="/create")
	public @ResponseBody String createPost(@RequestBody Post post) {
		if (Support.eqor(null, post.getContent(), post.getUser()))
			return "failure: required field(s) are null";

		postServe.insertPost(post);
		for (ImageLink image : post.getImages()) {
			image.setPost(post);
			imageLinkServe.insertImage(image);
		}
		return "success";
	}
	
	@PostMapping(value="/comment")
	public @ResponseBody String createComment(@RequestBody Comment comment) {
		if (Support.eqor(null, comment.getContent(), comment.getUser(), comment.getPost()))
			return "failure: required field(s) are null";
		
		commentServe.insertComment(comment);
		return "success";
	}
	
	@PutMapping(value="/like")
	public @ResponseBody String doLike(@RequestParam("user") int user,
			@RequestParam("post") int post) {
		
		Post toLike = postServe.getPostById(post);
		toLike.getLikes().add(userServe.getUserById(user));
		postServe.updatePost(toLike);
		
		return "success";
	}
	
	@PutMapping(value="/unlike")
	public @ResponseBody String doNotLike(@RequestParam("user") int user,
			@RequestParam("post") int post) {
		
		Post toLike = postServe.getPostById(post);
		toLike.getLikes().remove(userServe.getUserById(user));
		postServe.updatePost(toLike);
		
		return "success";
	}
	
	@GetMapping(value="/getAll")
	public @ResponseBody ResponseEntity<List<Post>> postGetAll() {
		List<Post> posts = postServe.getAllPost();
		return new ResponseEntity<List<Post>>(posts, (posts == null || posts.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping(value="/getByUser/{id}")
	public @ResponseBody ResponseEntity<List<Post>> postGetByUser(@PathVariable("id") int id) {
		List<Post> posts = postServe.getPostByUser(id);
		return new ResponseEntity<List<Post>>(posts, (posts == null || posts.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
}
