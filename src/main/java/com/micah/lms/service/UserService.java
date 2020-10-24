package com.micah.lms.service;

import java.util.List;

import com.micah.lms.entity.User;


public interface UserService {
	
	public void addUser(User user);	
	
	public User getUser(Integer userId);
	
	public User updateUser(Integer userId, User user);
	
	public void deleteUser(Integer userId);
			
	public List<User> getAllUsers();
}
