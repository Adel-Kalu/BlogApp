package com.ga.blogapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.blogapp.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
	public User findByEmailAddress(String emailAddress);
}
