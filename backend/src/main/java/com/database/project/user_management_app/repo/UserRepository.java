package com.database.project.user_management_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.project.user_management_app.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
	
}
