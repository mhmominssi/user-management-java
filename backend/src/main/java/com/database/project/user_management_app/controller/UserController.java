package com.database.project.user_management_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.project.user_management_app.model.UserModel;
import com.database.project.user_management_app.service.UserService;
@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<UserModel> getAllUser(){
		List<UserModel> users = userService.getAllUser();
		System.out.println("users : " + users);
		return users;
	}
	
	@GetMapping("/user/{userId}")
	public Optional<UserModel> getUserById(@PathVariable Long userId) {
		Optional<UserModel> user =  userService.getUserById(userId);
		System.out.println("User id : " +userId+" : user : " + user);
		return user;
		
	}
	
	@PostMapping("/user")
	public String saveUser(@RequestBody UserModel user) {
		userService.saveUser(user);
		return "user saved successfully.";
	}
	
	@PutMapping("/user/{userId}")
	public String updateUser(@RequestBody UserModel user,@PathVariable Long userId) {
		user.setId(userId);
		userService.updateUser(user,userId);
		return "user updated successfully.";
	}
	
	@DeleteMapping("/user/{userId}")
	public String deleteUserById(@PathVariable Long userId) {
		userService.deleteUserById(userId);
		return "user deleted successfully.";
	}
	

}
