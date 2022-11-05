package com.citiustech.controller;

import com.citiustech.helper.UserNotFoundException;
import com.citiustech.model.Role;
import com.citiustech.model.User;
import com.citiustech.model.UserRole;
import com.citiustech.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		log.info("UserController -- createUser()");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(500L);
		role.setRoleName("USER");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);
		return this.userService.createUser(user,roles);
	}

	//creating user
	@PostMapping("/admin/user")
	public User createOther(@RequestBody User user) throws Exception {
		log.info("UserController -- createUser()");

		Set<UserRole> roles = new HashSet<>();

		Role role = new Role();
		role.setRoleId(200L);
		role.setRoleName(user.getRole());

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);
		return this.userService.createUser(user,roles);
	}

	//Getting a User By username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username){
		return this.userService.getUser(username);
	}

	//Getting a User By Id
	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable("id") Long id){
		return this.userService.getUserById(id);
	}


	@GetMapping("/users")
	public List<User> userList(){
		return this.userService.listOfUsers();
	}

	@GetMapping("/hi")
	public String sayHello() {
		System.out.println("Hi UMS");
		return "Hello UMS";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserNotFoundException ex)
	{
		return new ResponseEntity<>("User not found Exception", HttpStatus.NOT_FOUND);
	}
}
