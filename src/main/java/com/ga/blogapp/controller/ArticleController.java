package com.ga.blogapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ga.blogapp.dao.ArticleDao;
import com.ga.blogapp.dao.AuthorDao;
import com.ga.blogapp.model.Article;

@Controller
public class ArticleController {
	

	//CRUD OPERATIONS
	// C -> Create = Done
	// R -> Select = Done
	// U -> Update = Done
	// D -> Delete = Done
	
	@Autowired 
	private Environment env;
	
	@Autowired
	private AuthorDao authordao;
	
	@Autowired
	private UserController uc;
	
	@Autowired
	HttpServletRequest request;
	
	// HTTP GET REQUEST - Article Add
	@GetMapping("/article/add")
	public ModelAndView addArticle() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("article/add");
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		var it = authordao.findAll();
		mv.addObject("authors", it);
		
		
		if(!uc.isUserLoggedIn())
		{
			mv.setViewName("home/index");
		}
		
		return mv;
	}
	
	@Autowired
	private ArticleDao dao;
	
	// HTTP POST REQUEST - Article Add
	@PostMapping("/article/add")
	public String addArticle(Article article) {
		dao.save(article);
		
		return "redirect:/article/index";
	}
	
	// HTTP GET REQUEST - Article Index
	@GetMapping("/article/index")
	public ModelAndView getArticle() {
		var it = dao.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("article/index");
		mv.addObject("articles", it);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
	}
	
	// HTTP GET REQUEST - Article Detail
	@GetMapping("/article/detail")
	public ModelAndView articleDetails(@RequestParam int id) {
		System.out.println(id);
		
		Article article = dao.findById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("article/detail");
		mv.addObject("article", article);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
		
	}
	
	// HTTP GET REQUEST - Article Edit
	@GetMapping("/article/edit")
	public ModelAndView editArticle(@RequestParam int id) {
		Article article = dao.findById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("article/edit");
		mv.addObject("article", article);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		var it = authordao.findAll();
		mv.addObject("authors", it);
		
		if(!uc.isUserLoggedIn())
		{
			mv.setViewName("home/index");
		}
		
		
		return mv;
	}
	
	// HTTP GET REQUEST - Article Delete
	@GetMapping("/article/delete")
	public String deleteArticle(@RequestParam int id) {
		
		HttpSession session = request.getSession();

		if(!uc.isUserLoggedIn())
		{
			return "redirect:/";
		}
		else if(session.getAttribute("userRole").equals("user"))
		{
			return "redirect:/article/index";
		}
		
		dao.deleteById(id);
		return "redirect:/article/index";
	}
	
	
}
