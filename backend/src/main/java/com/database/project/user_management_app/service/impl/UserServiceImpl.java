package com.database.project.user_management_app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.project.user_management_app.dto.UserDto;
import com.database.project.user_management_app.model.UserModel;
import com.database.project.user_management_app.repo.UserRepository;
import com.database.project.user_management_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto updateUser(UserDto user, Long id) {
		UserModel model = convertDtoToModel(user);
		model.setId(id);
		UserModel updated = userRepository.save(model);

		return convertModelToDto(updated);
	}

	@Override
	public List<UserDto> getAllUser() {
		return userRepository.findAll().stream()
				.map(this::convertModelToDto).toList();
	}

	@Override
	public Optional<UserDto> getUserById(Long userId) {
		return userRepository.findById(userId)
				.map(model -> convertModelToDto(model));

	}

	@Override
	public UserDto saveUser(UserDto user) {
		UserModel model = convertDtoToModel(user);
		UserModel saved = userRepository.save(model);
		return convertModelToDto(saved);
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

	// DTO → Model
	private UserModel convertDtoToModel(UserDto dto) {
		UserModel model = new UserModel();
		model.setId(dto.getId());
		model.setFirstName(dto.getFirstName());
		model.setLastName(dto.getLastName());
		model.setGender(dto.getGender());
		model.setCity(dto.getCity());
		model.setDob(dto.getDob());
		return model;
	}

	// Model → DTO
	private UserDto convertModelToDto(UserModel model) {
		UserDto dto = new UserDto();
		dto.setId(model.getId());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		dto.setGender(model.getGender());
		dto.setCity(model.getCity());
		dto.setDob(model.getDob());
		return dto;
	}
}