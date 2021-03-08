package com.ga.blogapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.blogapp.model.Article;

public interface ArticleDao extends CrudRepository<Article, Integer> {
	public Article findById(int id);
}
