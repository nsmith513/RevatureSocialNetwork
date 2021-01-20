package com.project.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.project.two.model.Post;
import com.project.two.model.User;
import com.project.two.service.PostService;
import com.project.two.service.UserService;

@Component ("serviceTest")
public class ServiceTest {
	
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private static UserService userService = appContext.getBean("userServe", UserService.class);
	private static PostService postService = appContext.getBean("postServe", PostService.class);
	
	User nikUser = new User("AmericanNik", "nikasaurus", "nik@gmail.com", null);
	User zachUser = new User("Zacharias", "zachthewack", "zach@gmail.com", null);
	User nickUser = new User("NickWithAC", "nicketysplit", "nick@gmail.com", null);
	
	Post firstPost = new Post("Potatoes are secretly tomatoes", null, null, null, null, zachUser);
	Post secondPost = new Post("Tomatoes are people too", null, null, null, null, zachUser);
	Post thirdPost = new Post("May the potato lords have mercy on us", null, null, null, null, nikUser);
	Post fourthPost = new Post("Look at all these chickens", null, null, null, null, nickUser);

	@BeforeClass
	public static void setUpBeforeClass() {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		nikUser.setId(userService.insertUser(nikUser));
		zachUser.setId(userService.insertUser(zachUser));
		nickUser.setId(userService.insertUser(nickUser));
		
		firstPost.setId(postService.insertPost(firstPost));
		secondPost.setId(postService.insertPost(secondPost));
		thirdPost.setId(postService.insertPost(thirdPost));
		fourthPost.setId(postService.insertPost(fourthPost));
	}

	@After
	public void tearDown() throws Exception {
		postService.deletePost(firstPost);
		postService.deletePost(secondPost);
		postService.deletePost(thirdPost);
		postService.deletePost(fourthPost);
		
		userService.deleteUser(nikUser);
		userService.deleteUser(zachUser);
		userService.deleteUser(nickUser);
	}
	
	@Test
	public void insertAndDeleteUserTest() {
		
		User otherTestUser = new User("otherTest", "pass", "otherTest@gmail.com", null);
		User testUser = new User("Test", "pass", "test@gmail.com", null);

		otherTestUser.setId(userService.insertUser(otherTestUser));
		testUser.setId(userService.insertUser(testUser));
		
		//insert a few normal users
//		userService.insert(otherTestUser);
//		assertEquals("Creating the second user", 7, userService.insert(testUser));
		
		
//		userService.delete(testUser);
		
		userService.deleteUser(otherTestUser);
		userService.deleteUser(testUser);
		
	}
	
	@Test
	public void selectUserByIdTest() {

		assertEquals("checking othertest User", nikUser.getId(), userService.getUserById(nikUser.getId()).getId());
	}
	
	@Test
	public void selectAllTest() {
		
		assertEquals("3 users", 5, userService.getAllUsers().size());
		
	}
	
	@Test
	public void selectByUsernameTest() {
		
		assertEquals("Get Nik user", "AmericanNik", userService.getUserByName("AmericanNik").getUname());
	}
	
	@Test
	public void updateUserTest() {
		
		nikUser.setUname("AmericanNiklos");
		
		userService.updateUser(nikUser);
		
		assertEquals("Make sure the username was changed", "AmericanNiklos", userService.getUserByName("AmericanNiklos").getUname());
		
		nikUser.setUname("AmericanNik");
	}
	

	
/////////////////POST DAOS
	
	@Test
	public void insertAndDeletePostTest() {
		
		Post otherTestPost = new Post("Hey. I just ate seventeen tomatoes in one minute. Beat that... scrub....", null, null, null, null, nickUser);
		Post testPost = new Post("Tomatoes are just tomatoes", null, null, null, null, zachUser);
		
		otherTestPost.setId(postService.insertPost(otherTestPost));
		testPost.setId(postService.insertPost(testPost));
		
		postService.deletePost(otherTestPost);
		postService.deletePost(testPost);
	}

	@Test
	public void selectPostByUserTest() {
		
		assertEquals("number of Zach's posts", 2, postService.getPostByUser(zachUser).size());
		assertEquals("number of Nick's posts", 1, postService.getPostByUser(nickUser).size());
	}
	
	
	@Test
	public void selectByIdTest() {
		
		assertEquals("Figure out if the id is correct...", firstPost.getId(), postService.getPostById(firstPost.getId()).getId());
	}
	
	@Test
	public void updatePostTest() {
		
		firstPost.setContent("Potatoes are potatoes bruh");
		
		postService.updatePost(firstPost);
		
		assertEquals("first post content has changed", "Potatoes are potatoes bruh", postService.getPostById(firstPost.getId()).getContent());
	
		firstPost.setContent("Potatoes are secretly tomatoes");
	}
	
	@Test
	public void selectAllPostsTest() {
		
		assertEquals("Checking number of posts total", 7, postService.getAllPost().size());
	}

}
