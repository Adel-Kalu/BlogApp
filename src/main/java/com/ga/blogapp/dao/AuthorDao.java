package com.ga.blogapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.blogapp.model.Author;

public interface AuthorDao extends CrudRepository<Author, Integer> {
	public Author findById(int id);
}
