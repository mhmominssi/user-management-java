package com.database.project.user_management_app.service;

import java.util.List;
import java.util.Optional;

import com.database.project.user_management_app.model.UserModel;

public interface UserService {

	void updateUser(UserModel user, Long userId);

	List<UserModel> getAllUser();

	Optional<UserModel> getUserById(Long userId);

	void saveUser(UserModel user);

	void deleteUserById(Long userId);

}
