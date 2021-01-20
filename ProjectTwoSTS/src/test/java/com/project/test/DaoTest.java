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

import com.project.two.dao.PostDao;
import com.project.two.dao.UserDao;
import com.project.two.model.Post;
import com.project.two.model.User;

@Component ("daoTest")
public class DaoTest {

	private static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private static UserDao userDao = appContext.getBean("userDao", UserDao.class);
	private static PostDao postDao = appContext.getBean("postDao", PostDao.class);
	
	User nikUser = new User("AmericanNik", "nikasaurus", "nik@gmail.com", null);
	User zachUser = new User("Zacharias", "zachthewack", "zach@gmail.com", null);
	User nickUser = new User("NickWithAC", "nicketysplit", "nick@gmail.com", null);
	
	Post firstPost = new Post("Potatoes are secretly tomatoes", null, null, null, null, zachUser);
	Post secondPost = new Post("Tomatoes are people too", null, null, null, null, zachUser);
	Post thirdPost = new Post("May the potato lords have mercy on us", null, null, null, null, nikUser);
	Post fourthPost = new Post("Look at all these chickens", null, null, null, null, nickUser);
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		nikUser.setId(userDao.insert(nikUser));
		zachUser.setId(userDao.insert(zachUser));
		nickUser.setId(userDao.insert(nickUser));
		
		firstPost.setId(postDao.insert(firstPost));
		secondPost.setId(postDao.insert(secondPost));
		thirdPost.setId(postDao.insert(thirdPost));
		fourthPost.setId(postDao.insert(fourthPost));
	}

	@After
	public void tearDown() throws Exception {
		
		postDao.delete(firstPost);
		postDao.delete(secondPost);
		postDao.delete(thirdPost);
		postDao.delete(fourthPost);
		
		userDao.delete(nikUser);
		userDao.delete(zachUser);
		userDao.delete(nickUser);
	}
	

////////////USER DAOS
	
	@Test
	public void insertAndDeleteUserTest() {
		
		User otherTestUser = new User("otherTest", "pass", "otherTest@gmail.com", null);
		User testUser = new User("Test", "pass", "test@gmail.com", null);

		otherTestUser.setId(userDao.insert(otherTestUser));
		testUser.setId(userDao.insert(testUser));
		
		//insert a few normal users
//		userDao.insert(otherTestUser);
//		assertEquals("Creating the second user", 7, userDao.insert(testUser));
		
		
//		userDao.delete(testUser);
		
		userDao.delete(otherTestUser);
		userDao.delete(testUser);

		
		//Reinsert a user that was already created -> throws an error
//		assertNotEquals("Reinserting Nik", 6, userDao.insert(nikUser));
	}
	
	@Test
	public void selectUserByIdTest() {

		assertEquals("checking othertest User", nikUser.getId(), userDao.selectById(nikUser.getId()).getId());
	}
	
	@Test
	public void selectAllTest() {
		
		assertEquals("3 users", 5, userDao.selectAll().size());
		
	}
	
	@Test
	public void selectByUsernameTest() {
		
		assertEquals("Get Nik user", "AmericanNik", userDao.selectByUname("AmericanNik").getUname());
	}
	
	@Test
	public void updateUserTest() {
		
		nikUser.setUname("AmericanNiklos");
		
		userDao.update(nikUser);
		
		assertEquals("Make sure the username was changed", "AmericanNiklos", userDao.selectByUname("AmericanNiklos").getUname());
		
		nikUser.setUname("AmericanNik");
	}
	

	
/////////////////POST DAOS
	
	@Test
	public void insertAndDeletePostTest() {
		
		Post otherTestPost = new Post("Hey. I just ate seventeen tomatoes in one minute. Beat that... scrub....", null, null, null, null, nickUser);
		Post testPost = new Post("Tomatoes are just tomatoes", null, null, null, null, zachUser);
		
		otherTestPost.setId(postDao.insert(otherTestPost));
		testPost.setId(postDao.insert(testPost));
		
		postDao.delete(otherTestPost);
		postDao.delete(testPost);
	}

	@Test
	public void selectPostByUserTest() {
		
		assertEquals("number of Zach's posts", 2, postDao.selectByUser(zachUser).size());
		assertEquals("number of Nick's posts", 1, postDao.selectByUser(nickUser).size());
	}
	
	
	@Test
	public void selectByIdTest() {
		
		assertEquals("Figure out if the id is correct...", firstPost.getId(), postDao.selectById(firstPost.getId()).getId());
	}
	
	@Test
	public void updatePostTest() {
		
		firstPost.setContent("Potatoes are potatoes bruh");
		
		postDao.update(firstPost);
		
		assertEquals("first post content has changed", "Potatoes are potatoes bruh", postDao.selectById(firstPost.getId()).getContent());
	
		firstPost.setContent("Potatoes are secretly tomatoes");
	}
	
	@Test
	public void selectAllPostsTest() {
		
		assertEquals("Checking number of posts total", 7, postDao.selectAll().size());
	}
	
	
}
