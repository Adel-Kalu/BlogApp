package com.ga.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ga.blogapp.dao.AuthorDao;
import com.ga.blogapp.model.Author;

@Controller
public class AuthorController {
	

	//CRUD OPERATIONS
	// C -> Create = done
	// R -> Select = done
	// U -> Update = done
	// D -> Delete =  done
	
	@Autowired 
	private Environment env;
	
	// HTTP GET REQUEST - Author Add
	@GetMapping("/author/add")
	public ModelAndView addAuthor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("author/add");
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
	}
	
	@Autowired
	private AuthorDao dao;
	
	// HTTP POST REQUEST - Author Add
	@PostMapping("/author/add")
	public String addAuthor(Author author) {
		dao.save(author);
		
		return "redirect:/author/index";
	}
	
	// HTTP GET REQUEST - Author Index
	@GetMapping("/author/index")
	public ModelAndView getAuthor() {
		var it = dao.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("author/index");
		mv.addObject("authors", it);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
	}
	
	// HTTP GET REQUEST - Author Detail
	@GetMapping("/author/detail")
	public ModelAndView authorDetails(@RequestParam int id) {
		System.out.println(id);
		
		Author author = dao.findById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("author/detail");
		mv.addObject("author", author);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
		
	}
	
	// HTTP GET REQUEST - Author Edit
	@GetMapping("/author/edit")
	public ModelAndView editAuthor(@RequestParam int id) {
		Author author = dao.findById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("author/edit");
		mv.addObject("author", author);
		
		HomeController hc = new HomeController();
		hc.setAppName(mv, env);
		
		return mv;
	}
	
	// HTTP GET REQUEST - Author Delete
	@GetMapping("/author/delete")
	public String deleteAuthor(@RequestParam int id) {
		dao.deleteById(id);
		return "redirect:/author/index";
	}
	
	
}
