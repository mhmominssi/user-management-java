package com.database.project.user_management_app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.project.user_management_app.model.UserModel;
import com.database.project.user_management_app.repo.UserRepository;
import com.database.project.user_management_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel updateUser(UserModel user, Long id) {
		return userRepository.save(user);
	}

	@Override
	public List<UserModel> getAllUser() {
		List<UserModel> users = userRepository.findAll();
		return users;
	}

	@Override
	public Optional<UserModel> getUserById(Long userId) {
		Optional<UserModel> user = userRepository.findById(userId);

		return user;
	}

	@Override
	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

}
