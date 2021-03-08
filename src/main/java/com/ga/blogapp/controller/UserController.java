package com.ga.blogapp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ga.blogapp.config.JwtUtil;
import com.ga.blogapp.dao.UserDao;
import com.ga.blogapp.model.JwtResponse;
import com.ga.blogapp.model.User;

@RestController
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	HttpServletRequest request;
	
	// Routes 
	
	// To load the registration form
//	   @GetMapping("/user/registration")
//	   public ModelAndView registration() {
//		   
//		   ModelAndView mv = new ModelAndView();
//		   mv.setViewName("user/registration");
//		   
//		   HomeController hc = new HomeController();
//		   hc.setAppName(mv, env);
//		   
//		   return mv;
//	   }
	
	// To post the registration form
	 @PostMapping("/user/registration")
	 public HashMap<String, String> registration(@RequestBody User user) {

		 
//		 ModelAndView mv = new ModelAndView();
//		 mv.setViewName("home/index");
//		 
//		 HomeController hc = new HomeController();
//		 hc.setAppName(mv, env);
		 
		 HashMap<String, String> response = new HashMap<String, String>();
		 
		 // Check to user is already registered or not
		 
		 var it = dao.findAll();
		 
		 for(User dbUser : it) {
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {
//				 mv.addObject("message", "User already exists");
//				 return mv;
				 
				 response.put("message", "User already exists");
				 return response;
				 
			 }
		 }
		 
		 
		 // Password Encryption
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(newPassword);
		 
		 dao.save(user);
//		 mv.addObject("message", "User registered successfully");
//		 return mv;
		 
		 response.put("message", "User registered successfully");
		 return response;
		 
	 }
	 
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	 @Autowired
	 JwtUtil jwtUtil;
	 
	 @Autowired
	 UserDetailsService userDetailsService;
	 
	 @PostMapping("/user/authenticate")
	 public ResponseEntity<?> authenticate(@RequestBody User user) {
		 
		 try {
			 authenticationManager.authenticate(
					 new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword())
					 );
		 }
		 catch(BadCredentialsException e) {
			 String res = "Incorrect username or password";
			 return ResponseEntity.ok(res);
		 }
		 
		// Conitnue
		 
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailAddress());
		
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println(jwtToken);
		return ResponseEntity.ok(new JwtResponse(jwtToken));
		 
	 }
	
//	// To load the login form
//		@GetMapping("/login")
//		public ModelAndView login() {
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("user/login");
//			
//			HomeController hc = new HomeController();
//			hc.setAppName(mv, env);
//			
//			return mv;
//		}
	
//	// To post the login form
//	 @PostMapping("/user/login")
//	 public String login(User user) {
//		 
//		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		 
//		 String emailAddress = user.getEmailAddress();
//		 String password = user.getPassword();
//		 
//		 User matchedUser = dao.findByEmailAddress(emailAddress);
//		 HttpSession session = request.getSession();
//
//		 if(matchedUser != null) {
//			 if(bCrypt.matches(password, matchedUser.getPassword())) {
//				 
//				 // Session
//				  session.setAttribute("user", matchedUser);
//				  session.setAttribute("userRole", matchedUser.getUserRole());
//				  
//				  session.setAttribute("message", "you are logged in successfully");
//				  
//				  return "redirect:/";
//				 
//			 }
//		 }
//		 
//		 session.setAttribute("message", "Username or password is incorrect");
//		 return "redirect:/user/login";
//	 }
//	
//	// To invalidate the current user session
//	 @GetMapping("/user/logout")
//	 public String logout() {
//		 HttpSession session = request.getSession();
//		 session.invalidate();
//		 
//		 return "redirect:/user/login";
//	 }
//	
//	// TO check the user is logged in or not
//	 public boolean isUserLoggedIn() {
//		 
//		 HttpSession session = request.getSession();
//		 if(session.getAttribute("user") == null) {
//			 return false;
//		 }
//		 else
//		 {
//			 return true;
//		 }
//	 }
//	 
//	
//	// Load user profile
//	// @GetMapping("/user/profile")

}
