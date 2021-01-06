package com.aadhar.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.aadhar.app.model.User;

public interface LoginService extends UserDetailsService {

	List<User> getUsersByEmail(String username);
	void save(User theUser);
}
