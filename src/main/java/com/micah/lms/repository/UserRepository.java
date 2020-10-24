package com.micah.lms.repository;

import org.springframework.data.repository.CrudRepository;

import com.micah.lms.entity.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}