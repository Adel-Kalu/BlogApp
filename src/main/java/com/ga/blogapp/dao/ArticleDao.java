package com.ga.blogapp.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ga.blogapp.model.Article;

public interface ArticleDao extends CrudRepository<Article, Integer> {
	public Article findById(int id);

//	@Modifying
//	@Query(value= "DELETE from table author_articles where author_id =:author_Id"
//			+"and article_id =:article_Id", nativeQuery  = true)
//	public boolean deletefromAuthorArticlesByID(@Param("author_Id") int authorId, @Param("article_Id") int articleId);
	
	
//	public boolean deletefromCartByID(int MealId, int UserId);
}
