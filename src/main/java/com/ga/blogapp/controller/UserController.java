
package com.ga.blogapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ga.blogapp.dao.UserDao;
import com.ga.blogapp.model.User;

@Controller
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	HttpServletRequest request;
	
	
	// Routes 
	 
	 @GetMapping("/hello")
	 public String hello() {
		 if(isUserLoggedInStub())
		 return "hi";
		 return "by";
	 }

	// To load the registration form
	   @GetMapping("/user/registration")
	   public ModelAndView registration() {
		   
		   ModelAndView mv = new ModelAndView();
		   mv.setViewName("user/registration");
		   
		   HomeController hc = new HomeController();
		   hc.setAppName(mv, env);
		   
		   return mv;
	   }
	
	// To post the registration form
	 @PostMapping("/user/registration")
	 public ModelAndView registration(User user) {
		

		 
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("user/login");
		 
		 HomeController hc = new HomeController();
		 hc.setAppName(mv, env);
		 
		 // Check to user is already registered or not
		 
		 var it = dao.findAll();
		 System.out.println(it);
		 
		 for(User dbUser : it) {
			 System.out.println(dbUser.getEmailAddress());
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {
			
				 mv.addObject("message", "User already exists");
				 return mv;
			 }
		 }
		 
		 
		 // Password Encryption
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(newPassword);
		 
		 dao.save(user);
		 mv.addObject("message", "User registered successfully");
		 
		 return mv;
		 
	 }
	
	private ArrayList<User> findAllStub() {
		// TODO Auto-generated method stub
		ArrayList<User> retList = new ArrayList<User>();
 	    retList.add(new User("1234", "a@b"));
	    retList.add(new User("123", "s@a")); 
		return retList;
	}

	// To load the login form
		@GetMapping("/user/login")
		public ModelAndView login() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("user/login");
			
			HomeController hc = new HomeController();
			hc.setAppName(mv, env);
			
			return mv;
		}
	
	// To post the login form
	 @PostMapping("/user/login")
	 public String login(User user) {
		 
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 
		 String emailAddress = user.getEmailAddress();
		 String password = user.getPassword();
		 
		 User matchedUser = dao.findByEmailAddress(emailAddress);
		 HttpSession session = request.getSession();
		 session.setAttribute("email", password);
		 session.setAttribute("out", "user " + matchedUser.getPassword() );
		 if(matchedUser != null) {
			 if(bCrypt.matches(password, matchedUser.getPassword())) {
				  session.setAttribute("in", "password error");
				 // Session
				  session.setAttribute("user", matchedUser);
				  session.setAttribute("userRole", matchedUser.getUserRole());
				  
				  session.setAttribute("message", "you are logged in successfully");
				  
				  return "redirect:/";
				 
			 }
		 }
		 
		 session.setAttribute("message", "emailAddress or password is incorrect");
		 return "redirect:/user/login";
	 }
	
	// To invalidate the current user session
	 @GetMapping("/user/logout")
	 public String logout() {
		 HttpSession session = request.getSession();
		 session.invalidate();
		 
		 return "redirect:/user/login";
	 }
	
	// TO check the user is logged in or not
	 public boolean isUserLoggedIn() {
		 System.out.print("in");
		 HttpSession session = request.getSession();
		 if(session.getAttribute("user") == null) {
			 return false;
		 }
		 else
		 {
			 return true;
		 }}
		 public boolean isUserLoggedInStub() {
			 return true;
	 }
	 
	
	// Load user profile
	// @GetMapping("/user/profile")

}