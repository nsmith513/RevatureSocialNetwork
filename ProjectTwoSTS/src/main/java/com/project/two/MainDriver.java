package com.project.two;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.two.dao.UserDao;
import com.project.two.model.Post;
import com.project.two.model.User;
import com.project.two.service.PostService;
import com.project.two.service.UserService;

public class MainDriver {
	
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private static UserService userServ = appContext.getBean("userServe", UserService.class);
	private static UserDao userDao = appContext.getBean("userDao", UserDao.class);
	private static PostService postServ = appContext.getBean("postServe", PostService.class);
	
	private static User bob = new User(1, "bob", "123", "bob@gmail.com", null);
	
	private static void insertInitialValues() {
		userServ.insertUser(bob);
		Set<User> likes = new HashSet<User>();
		likes.add(bob);
		postServ.insertPost(new Post("This is a post!", null, null, null, likes, bob));
	}
	
	public static void main(String[] args) {
//		insertInitialValues();
		
		System.out.println("\n\nSelecting post");
		System.out.println(userServ.getUserByName("bob"));
		bob.setEmail("bob@yahoo.com");
		userServ.updateUser(bob);
		System.out.println(userServ.getUserByName("bob"));
		//System.out.println(postServ.getPostByUser(bob.getId()));
		
		
		//HibernateUtil.closeSession();
		
	}

}
