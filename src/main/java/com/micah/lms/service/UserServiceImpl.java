package com.micah.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micah.lms.entity.User;
import com.micah.lms.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public void addUser(User user) {
		UserRepository.save(user);
	}

	@Override
	public User getUser(Integer userId) {
		return UserRepository.findOne(userId);
	}

	@Override
	public User updateUser(Integer userId, User user) {
		return UserRepository.save(user);
	}
	
	@Override
	public void deleteUser(Integer userId) {
		UserRepository.delete(userId);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=new ArrayList<>();
		UserRepository.findAll().forEach(users::add);
		return users;
	}

}
