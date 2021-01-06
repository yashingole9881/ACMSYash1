package com.aadhar.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aadhar.app.model.User;

public interface LoginRepository extends JpaRepository<User, Long>{
	
	List<User> findByEmail(String email);

}
