package com.database.project.user_management_app.service;

import java.util.List;
import java.util.Optional;

import com.database.project.user_management_app.dto.UserDto;

public interface UserService {

    UserDto updateUser(UserDto user, Long userId);

    List<UserDto> getAllUser();

    Optional<UserDto> getUserById(Long userId);

    UserDto saveUser(UserDto user);

    void deleteUserById(Long userId);
}

//public interface UserService {
//
//	UserDto updateUser(UserDto user, Long userId);
//
//	List<UserModel> getAllUser();
//
//	Optional<UserDto> getUserById(Long userId);
//
//	UserDto saveUser(UserDto user);
//
//	void deleteUserById(Long userId);
//
//}
