package com.project.two;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.project.two.model.Post;
import com.project.two.model.User;
import com.project.two.service.PostService;
import com.project.two.service.UserService;
import com.project.two.util.Support;

/**
 * Contains logic to execute on server start.
 * 
 * @author Nicholas Smith
 */
@Component
public class Init {
	private static boolean initialized = false;
	
	private UserService userServe;
	private PostService postServe;
	
	@Autowired
	public void setUserServe(UserService userServe) {
		this.userServe = userServe;
	}

	@Autowired
	public void setPostServe(PostService postServe) {
		this.postServe = postServe;
	}
    
	/**
	 * Insert initial data into the database.
	 */
	private void insertInitialValues() {
		User bob = new User("bob", Support.hashpw("123"), "bob@gmail.com", "https://cdn.discordapp.com/avatars/294771443676217346/6d23afe2b54f726adce66a09d5e740a3.png?size=256");
		User zach = new User("zachariah", Support.hashpw("123"), "zachariah@gmail.com", "https://cdn.discordapp.com/avatars/778275584878313483/c6150c33f43a9f27fd7703277bb6115e.png?size=256");
		userServe.insertUser(bob);
		userServe.insertUser(zach);
		Set<User> likes = new HashSet<User>();
		likes.add(bob);
		postServe.insertPost(new Post("This is a post!", null, null, null, likes, bob));
		postServe.insertPost(new Post("This is another post!", null, null, null, null, bob));
		postServe.insertPost(new Post("Uh oh, monkey...", null, null, null, null, zach));
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		if (!initialized) {
			insertInitialValues();
			initialized = true;
		}
	}
}
