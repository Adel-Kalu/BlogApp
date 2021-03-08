package com.ga.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ga.blogapp.dao.ArticleDao;
import com.ga.blogapp.dao.AuthorDao;
import com.ga.blogapp.model.Article;




@Controller
public class HomeController {

	@Autowired 
	private Environment env;
	
	@Autowired
	ArticleDao articleDao;
	
	// HTTP GET REQUEST - Root Route
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("home/index");
		
		setAppName(mv, env);
		
//		articleDao.deletefromAuthorArticlesByID(4,18);
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (!(authentication instanceof AnonymousAuthenticationToken)) {
//		    String currentUserName = authentication.getName();
//		    System.out.println(currentUserName);
//		}
		
		return mv;
	}
	
	// Setting an App name (Other common properties)
	public ModelAndView setAppName(ModelAndView mv, @Autowired Environment env) {
		String appName = env.getProperty("app.name");
		mv.addObject("appName", appName);
		mv.addObject("Welcome", "Welcome to our Blog App");

		return mv;
	}
	
	
	
}
