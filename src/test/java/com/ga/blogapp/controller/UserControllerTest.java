package com.ga.blogapp.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // A ResultMatcher matches the result of an executed request against some expectation.

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ga.blogapp.dao.UserDao;
import com.ga.blogapp.model.User;

import ch.qos.logback.core.net.ObjectWriter;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
//@RunWith(SpringRunner.class) annotation tells JUnit to run using Spring's testing support. 
//SpringRunner provides support for loading Spring ApplicationContext, autowiring beans into the test class.
//@WebMvcTest Annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
	/** Main entry point for server-side Spring MVC test support. **/
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserDao dao;

//	@Test
//	void hello_ReturningString_Success() throws Exception {
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders //Mock implementation of the HttpServletRequest interface.
//
//				                        .get("/hello")
//				                        .accept(MediaType.APPLICATION_JSON);//adds support for quality parameters as defined in the HTTP specification.
//		
//		mockMvc.perform(requestBuilder)
//        .andExpect(status().isOk())
//        .andExpect(forwardedUrl("/views/hi.jsp"));
//		
//	}
//	
//	@Test
//	void hello_ReturningString_Success() throws Exception {
//
//		UserController userController = new UserController();
//		String real = userController.hello();
//		System.out.println(real);
//		String expected = "hi";
//		Assert.assertEquals(real, expected);
//	}

//	@Test
//	public void login_ReturnsOk200_Success() throws Exception{
//       
//        Mockito.when(dao.findByEmailAddress("a@b")).thenReturn(new User());
//
//	
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/user/login").param("emailAddress", "a@b")
//			    .param("password", "123");
//				
//		        
//
//		 mockMvc.perform(requestBuilder)
//		 .andExpect(status().isOk());
//              
//              
//	}
//	@Test
//	public void login_Success() throws Exception {
//		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		String newPassword = bCrypt.encode("12345");
//		Mockito.when(dao.findByEmailAddress("a@b")).thenReturn(new User(newPassword));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login").param("emailAddress", "a@b")
//				.param("password", "12345").contentType(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(requestBuilder).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
//		;
//
//	}

	@Test
	public void register_notRegistered() throws Exception { // test when trying to register a user that exists
		Iterable<User> iterable = Arrays.asList(new User("12345", "a@b"), new User("123", "a@c"));
		Mockito.when(dao.findAll()).thenReturn(iterable);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/registration").param("emailAddress", "a@b")
				.param("firstName", "samar").param("lastName", "fares").param("userRole", "admin")
				.param("password", "12345").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(model().attribute("message", "User already exists"));

		;

	}

}
