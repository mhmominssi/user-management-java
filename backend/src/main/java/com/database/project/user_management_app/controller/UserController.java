package com.database.project.user_management_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.project.user_management_app.dto.UserDto;
import com.database.project.user_management_app.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ✅ GET ALL USERS
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUser());
	}

	// ✅ GET USER BY ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {

		Optional<UserDto> user = userService.getUserById(userId);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get()); // 200 OK
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
		}
	}

	// ✅ CREATE USER
	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {

		UserDto savedUser = userService.saveUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // 201 Created
	}

	// ✅ UPDATE USER
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable Long userId) {

		Optional<UserDto> existingUser = userService.getUserById(userId);

		if (existingUser.isPresent()) {
			user.setId(userId);
			UserDto updatedUser = userService.updateUser(user, userId);
			return ResponseEntity.ok(updatedUser); // 200 OK
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
		}
	}

	// ✅ DELETE USER
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {

		Optional<UserDto> existingUser = userService.getUserById(userId);

		if (existingUser.isPresent()) {
			userService.deleteUserById(userId);
			return ResponseEntity.ok("User deleted successfully."); // 200 OK
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + userId); // 404
		}
	}
}
