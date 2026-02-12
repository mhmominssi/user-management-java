package com.database.project.user_management_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.database.project.user_management_app.model.UserModel;
import com.database.project.user_management_app.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUser() {
        List<UserModel> users = userService.getAllUser();
        return ResponseEntity.ok(users); // 200 OK
    }

    // ✅ GET USER BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long userId) {

        Optional<UserModel> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ✅ CREATE USER
    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {

        UserModel savedUser = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // 201 Created
    }

    // ✅ UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(
            @RequestBody UserModel user,
            @PathVariable Long userId) {

        Optional<UserModel> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            user.setId(userId);
            UserModel updatedUser = userService.updateUser(user, userId);
            return ResponseEntity.ok(updatedUser); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    // ✅ DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {

        Optional<UserModel> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully."); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id: " + userId); // 404
        }
    }
}
