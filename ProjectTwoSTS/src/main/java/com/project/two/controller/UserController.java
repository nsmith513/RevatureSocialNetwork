package com.project.two.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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

import com.project.two.model.User;
import com.project.two.service.UserService;
import com.project.two.util.Support;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

	private UserService userServe;

	@Autowired
	public void setUserServe(UserService userServe) {
		this.userServe = userServe;
	}
	
	@PostMapping(value="/login")
	public @ResponseBody ResponseEntity<User> login(HttpSession session, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		// Get user by username
		User user = userServe.getUserByName(username);
		
		// Return failure if no user was found or password does not match
		System.out.println("\t\t" + password);
		if (user == null || !Support.verifypw(password, user.getPwd()))
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		
		// Save user to session and send user data to client
		session.setAttribute("user", user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="logout")
	public @ResponseBody String logout(HttpSession session) {
		session.invalidate();
		return "success";
	}
	
	@PostMapping(value="/register")
	public @ResponseBody String register(@RequestBody User user) {
		// Make sure fields won't violate not null constraint
		if (Support.eqor(null, user.getUname(), user.getPwd(), user.getEmail()))
			return "failure: required field(s) are null";
		
		// Hash password
		user.setPwd(Support.hashpw(user.getPwd()));
		
		// Attempt to add user to DB
		try {
			userServe.insertUser(user);
		} catch (IllegalArgumentException e) {
			return "failure: " + e.getMessage();
		}
		return "success";
	}
	
	@PutMapping(value="/update")
	public @ResponseBody String update(@RequestBody User user) {
		if (Support.eqor(null, user.getUname(), user.getPwd(), user.getEmail()))
			return "failure: required field(s) are null";
		try {
			userServe.updateUser(user);
		} catch (IllegalArgumentException e) {
			return "failure: " + e.getMessage();
		}
		return "success";
	}
	
	@GetMapping(value="/getAll")
	public @ResponseBody ResponseEntity<List<User>> userGetAll() {
		List<User> users = userServe.getAllUsers();
		return new ResponseEntity<List<User>>(users, (users == null || users.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping(value="/getById/{id}")
	public @ResponseBody ResponseEntity<User> userGetById(@PathVariable("id") int id) {
		User user = userServe.getUserById(id);
		return new ResponseEntity<User>(user, user == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping(value="/getByUname/{uname}")
	public @ResponseBody ResponseEntity<User> userGetByName(@PathVariable("uname") String uname) {
		User user = userServe.getUserByName(uname);
		return new ResponseEntity<User>(user, user == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping(value="/search/{search}")
	public @ResponseBody ResponseEntity<List<User>> search(@PathVariable("search") String search) {
		List<User> users = userServe.getAllUsers();
		if (users == null)
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		users = users.stream()
			    .filter(user -> user.getUname().startsWith(search))
			    .collect(Collectors.toList());
		return new ResponseEntity<List<User>>(users, users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
}
